public class Main {
    public static void main(String[] args) {

        int key = 2;

        switch (key) {
            case 1:
                System.out.println("ConsoleApp in progress");
                ConsoleApp consoleApp = new ConsoleApp();
                break;

            case 2:
                System.out.println("SwingAppUI in progress");
                SwingAppUI swingAppUI = new SwingAppUI();
                break;
        
            default:
                break;
        }


    }
}
