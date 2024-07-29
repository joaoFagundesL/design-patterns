public class Main {
  public static void main(String[] args) {
    User user = new User("Alice", "alice@example.com");
    User user2 = new User("Joao", "joao@example.com");

    Observer class1 = new RandomClass();

    user.attach(class1);
    user2.attach(class1);

    user.setLoggedIn(true);
    user.setLoggedIn(false);

    user2.setLoggedIn(true);
  }
}
