package contacts;

import java.util.Scanner;

interface RecordFactory {
    Record createRecord(String type);
}

class RecordGenerator implements RecordFactory {

    public Record createRecord(String type) {
        Record record;
        switch (type) {
            case "person":
                record = new Person();
                break;
            case "organization":
                record = new Organization();
                break;
            default:
                System.out.println("No such record type available");
                return null;
        }
        Scanner keyboard = new Scanner(System.in);
        String value;
        String[] fields = record.getFields();
        for (String field : fields) {
            System.out.printf("Enter %s: ", field);
            value = keyboard.nextLine();
            record.editField(field, value);
        }
        return record;
    }
}
