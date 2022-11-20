import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    
    ForkJoinPool fjPool = new ForkJoinPool();
    
    int[] listing = new int[10000];
    
    for (int i = 0; i < listing.length; i++) {
      int mr = (int) (Math.random() * 33333);
      listing[i] = mr;
    }
    
    ForkJoinQuicksortTask forkJoinQuicksortTask = new ForkJoinQuicksortTask(listing,
        0, listing.length - 1);
    
    long start = System.nanoTime();
    fjPool.invoke(forkJoinQuicksortTask);
    
    System.out.println("Time: " + (System.nanoTime() - start));
  }
}

class ForkJoinQuicksortTask extends RecursiveAction {
  int[] listing;
  int a;
  int b;

  public ForkJoinQuicksortTask(int[] listing) {
    this(listing, 0, listing.length - 1);
  }

  public ForkJoinQuicksortTask(int[] listing, int a, int b) {
    this.listing = listing;
    this.a = a;
    this.b = b;
  }

  @Override
  protected void compute() {
    if (serialThresholdMet()) {
      Arrays.sort(listing, a, b + 1);
    } else {
      int index = partition(listing, a, b);
      ForkJoinQuicksortTask t1 = new ForkJoinQuicksortTask(listing, a,
          index - 1);
      ForkJoinQuicksortTask t2 = new ForkJoinQuicksortTask(listing, index + 1,
          b);
      t1.fork();
      t2.compute();
      t1.join();
    }
  }

  int partition(int[] listing, int c, int d) {
    int m = c - 1;
    int n = listing[d];
    for (int f = c; f < d; f++) {
      if (listing[f] < n) {
        m++;
        swap(listing, m, n);
      }
    }
    m++;
    swap(listing, m, d);
    return m;
  }

  void swap(int[] listing, int p, int r) {
    int g = listing[p];
    listing[p] = listing[r];
    listing[r] = g;
  }

  private boolean serialThresholdMet() {
    return b - a < 100000000;
  }
}