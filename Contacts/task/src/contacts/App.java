package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class App {
    private boolean isRunning;
    private String fileName;
    final Scanner keyboard;
    private DataBase contacts;
    private Validator validator;
    private RecordFactory recordGenerator;

    App() {
        this.keyboard = new Scanner(System.in);
        this.contacts = new DataBase();
        this.validator = new Validator();
        this.recordGenerator = new RecordGenerator(); // record factory
    }

    /**
     * The app is started.
     * The condition isRunning is set to true.
     * If a filename is stored and the file exists, the start method loads all serialized records from the file
     */
    void start() {
        this.isRunning = true;
        if (fileName != null) {
            File file = new File(this.fileName);
            if (file.exists()) {    // if file in field file exists,
                loadRecords();      // load serialized objects
            }
        }

        System.out.println("open phonebook.db");
        main();
    }

    /**
     * Main menu of the app.
     * While the condition isRunning of the app is true, the main menu is running
     */
    void main() {
        while (isRunning) {
            System.out.println();
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String action = keyboard.nextLine();
            switch (action) {
                case "add":
                    addContact();
                    break;
                case "list":
                    list();
                    break;
                case "search":
                    search();
                    break;
                case "count":
                    count();
                    break;
                case "exit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("This is not a valid action");
            }
        }
    }

    /**
     * The app provides this method to define a filename before the app is started
     * @param fileName file path
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sub-menu for adding contacts
     */
    void addContact() {
        System.out.print("Enter the type (person, organization): ");
        String type = keyboard.nextLine();
        Record record = recordGenerator.createRecord(type);
        contacts.addRecord(record);
        saveRecords();
    }

    /**
     * Sub-menu to list all contacts in db
     */
    void list() {
        Record[] records = contacts.getRecords();
        listRecords(records);

        System.out.println();
        System.out.print("[list] Enter action ([number], back): ");
        String action = keyboard.nextLine();

        if (action.equals("back")) {
            main();
        } else if (validator.isNaturalNumber(action)) {
            int index = Integer.parseInt(action);
            Record record = records[index - 1];

            System.out.println(record.toString());
            recordHandling(record);
        }
    }

    /**
     * Sub-menu to search for specific records in db
     */
    void search() {

            System.out.print("Enter search query: ");
            String queryString = keyboard.nextLine();
            List<Record> matches = query(queryString);
            listRecords(matches);

            System.out.println();
            System.out.print("[search] Enter action ([number], back, again): ");
            String action = keyboard.nextLine();

            if (action.equals("again")) {
                search();
            } else if (action.equals("back")) {
                main();
            } else if (validator.isNaturalNumber(action)) {
                int index = Integer.parseInt(action);
                Record record = matches.get(index - 1);

                System.out.println(record.toString());
                recordHandling(record);
            }
    }

    void count() {
        System.out.printf("The Phone Book has %d records.\n", contacts.getNumberOfRecords());
    }

    void recordHandling(Record record) {
        System.out.println();
        System.out.print("[record] Enter action (edit, delete, menu): ");
        String action = keyboard.nextLine();
        switch(action) {
            case "edit":
                edit(record);
                break;
            case "delete":
                contacts.removeRecord(record);
                main();
                break;
            case "menu":
                main();
        }
    }

    void edit(Record record) {
        System.out.printf("Select a field (%s): ", listFields(record));
        String field = keyboard.nextLine();
        System.out.printf("Enter %s: ", field);
        String value = keyboard.nextLine();
        if (record.editField(field, value)) {
            record.updateDateEdited();
            saveRecords();
            System.out.println("Saved");
        }
    }

    /**
     *
     * @param queryString
     * @return
     */
    List<Record> query(String queryString) {
        Record[] records = contacts.getRecords();
        List<Record> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(queryString, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        StringBuilder searchString;
        String value;

        for (Record record : records) {
            searchString = new StringBuilder();
            for (String field : record.getFields()) {
                value = record.getFieldValue(field);
                if (value != null) {
                    searchString.append(record.getFieldValue(field));
                }
            }
            matcher = pattern.matcher(searchString);

            if (matcher.find()) {
                matches.add(record);
            }
        }
        return matches;
    }

    String listFields(Record record) {
       String[] fields = record.getFields();
       StringBuilder fieldList = new StringBuilder();
        for (String field : fields) {
            fieldList.append(field).append(", ");
        }
        fieldList.deleteCharAt(fieldList.length() - 1).deleteCharAt(fieldList.length() - 1);
        return fieldList.toString();
    }

    void listRecords(List<Record> records) {
        for (int i = 0; i < records.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, records.get(i).getHead());
        }
    }

    void listRecords(Record[] records) {
        for (int i = 0; i < records.length; i++) {
            System.out.printf("%d. %s\n", i + 1, records[i].getHead());
        }
    }

    private void saveRecords() {
        if (fileName != null) {
            SerializationUtils.serialize(fileName, contacts.getRecords());
        }
    }

    private void loadRecords()  {
        if (fileName != null) {
            Record[] records = (Record[]) SerializationUtils.deserialize(fileName);
            if (records != null) {
                contacts.loadRecords(records);
            }
        }
    }
}
