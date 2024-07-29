public class Singleton {
    // volatile = handle multithread
    private static volatile Singleton instance;

    // data of your class
    private String data;

    public String getData() {
        return data;
    }

    private Singleton() {
        // logic here
        // also possible to pass the data as parameter private Singleton(String data), but in getInstace you'd have to do the same
        this.data = "FOO";
    }

    public static Singleton getInstance() {
        Singleton result = instance;
        // handle multithread accessing at the same time
        if (result == null) {
            synchronized (Singleton.class) {
                result = instance;
                if (result == null) {
                    System.out.println("Creating new instance of Singleton");
                    instance = result = new Singleton();
                }
            }
        }
        return result;
    }
}
