public class RandomClass implements Observer {
    @Override
    // this will be called every time the log in status change. 
    // instead of calling performOperation i can just create a field loggedIn and check in order to avoid calling this function every time it changes
    // example:  this.loggedIn = user.isLoggedIn(); i did nothing just updated the state
    public void update(User user) {
        if (user.isLoggedIn()) {
            System.out.println("User = " + user.getName());
            performLoggedInOperations();
        } else {
            performLoggedOutOperations();
        }
    }

    private void performLoggedInOperations() {
        System.out.println("Performing operations for logged-in user.");
    }

    private void performLoggedOutOperations() {
        System.out.println("Performing operations for logged-out user.");
    }
}
