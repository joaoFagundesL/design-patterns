public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton anotherSingleton = Singleton.getInstance();

        System.out.println(singleton.getData());
        System.out.println(anotherSingleton.getData());
    }
}