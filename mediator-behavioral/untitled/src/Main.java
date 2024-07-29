public class Main {
    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        dialog.enterUsername("Joao");
        dialog.enterPassword("Fagundes");

        dialog.simulateLoginClicked();

        dialog.simulateForgotPassClicked();    }
}