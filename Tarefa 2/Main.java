import java.util.LinkedList;

public class Main {
  public static void main(String[] args)
      throws InterruptedException {
    final PC pc = new PC();

    // Cria thread produtor
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          pc.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    // Cria thread consumidor
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          pc.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    // Inicia as threads
    t1.start();
    t2.start();

    // t1 finaliza antes e t2 dps
    t1.join();
    t2.join();
  }

  // Classe contém lista na qual produtor adiciona itens
  // e consumidor remove itens
  public static class PC {

    // Lista compartilhada de produtor e consumidor
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 4;


    public void produce() throws InterruptedException {
      int value = 0;
      while (true) {
        synchronized (this) {
          // thread produtor espera c a lista cheia
          while (list.size() == capacity)
            wait();

          System.out.println("Producer produced-"
              + value);

          list.add(value++);

          // notificação para consumo 
          notify();

          Thread.sleep(300);
        }
      }
    }

    public void consume() throws InterruptedException {
      while (true) {
        synchronized (this) {
          // thread consumidor espera c lista vazia
          while (list.size() == 0)
            wait();

          int val = list.removeFirst();

          System.out.println("Consumer consumed-"
              + val);

          // notifica para produzir
          notify();

          Thread.sleep(300);
        }
      }
    }
  }
}