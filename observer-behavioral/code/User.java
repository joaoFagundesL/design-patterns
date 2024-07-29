import java.util.ArrayList;
import java.util.List;

public class User implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String name;
    private String email;
    private boolean isLoggedIn;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
        notifyObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

     public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

