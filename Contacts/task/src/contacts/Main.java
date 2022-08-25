package contacts;

public class Main {
    public static void main(String[] args) {

        // create a new app
        App contacts = new App();

        // if file is passed as command-argument, the main method stores the path to the file in the app
        if (args.length > 0) {
            contacts.setFileName(args[0]);
        }

        // the app is started
        contacts.start();
    }
}
