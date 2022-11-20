
public class Optional {
  public static void main(String[] args) {
    // create an empty optional
    Optional<String> empty = Optional.empty();
    System.out.println("empty = " + empty);
    // create an optional with a value
    Optional<String> string = Optional.of("Hello");
    System.out.println("string = " + string);
    // create an optional with a null value
    Optional<String> nullString = Optional.of(null);
    System.out.println("nullString = " + nullString);
  }
}