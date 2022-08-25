package contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataBase {
    final private List<Record> records;
    DataBase() {
        this.records = new ArrayList<>();
    }

    Record getRecord(int index) {
        return records.get(index);
    }

    void loadRecords(Record[] records) {
        this.records.addAll(Arrays.asList(records));
    }

    Record[] getRecords() {
        return records.toArray(new Record[getNumberOfRecords()]);
    }

    void addRecord(Record record) {
        if (record != null) {
            records.add(record);
            System.out.println("The record added.");
        } else {
            System.out.println("The record could not have been added.");
        }
    }

    void removeRecord(Record record) {
        for (int i = 0; i < records.size(); i++) {
            if (record == records.get(i)){
                records.remove(i);
            }
        }

    }

    int getNumberOfRecords() {
        return records.size();
    }

}
