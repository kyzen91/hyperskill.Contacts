package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Record implements Serializable {

    private static final long serialVersionUID = 0L;
    static final Validator validator = new Validator();
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final LocalDateTime timeCreated;
    private LocalDateTime timeEdited;
    private String phoneNumber;


    public Record() {
        this.timeCreated = LocalDateTime.now();
        this.timeEdited = LocalDateTime.now();
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = validator.isValidPhoneNumber(phoneNumber);
    }

    void updateDateEdited() {
        timeEdited = LocalDateTime.now();
    }

    abstract String[] getFields();

    abstract boolean editField(String fieldName, String newValue);

    abstract String getFieldValue(String fieldName);

    abstract String getHead();

    @Override
    public String toString() {
        String str = "";
        if (phoneNumber != null) {
            str += "Number: " + phoneNumber + "\n";
        } else {
            str += "Number: " + "[no data]" + "\n";
        }
        str += "Time created: " + timeCreated.format(formatter) + "\n";
        str += "Time last edit: " + timeEdited.format(formatter);
        return str;
    }
}

class Person extends Record {
    private String name;
    private String surname;
    private LocalDate birth;
    private String gender;

    Person() {
        super();
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    LocalDate getBirth() {
        return birth;
    }

    void setBirthDate(String birthDateString) {
        this.birth = validator.isValidBirthDate(birthDateString);
    }

    String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = validator.isValidGender(gender);
    }

    @Override
    String[] getFields() {
        return new String[] {"name", "surname", "birth", "gender", "number"};
    }

    @Override
    boolean editField(String fieldName, String newValue) {
        switch (fieldName) {
            case "name":
                setName(newValue);
                break;
            case "surname":
                setSurname(newValue);
                break;
            case "birth":
                setBirthDate(newValue);
                break;
            case "gender":
                setGender(newValue);
                break;
            case "number":
                setPhoneNumber(newValue);
                break;
            default:
                System.out.println("This field does not exist");
                return false;
        }
        return true;
    }

    @Override
    String getFieldValue(String fieldName){
            switch (fieldName) {
                case "name":
                    return getName();
                case "surname":
                    return getSurname();
                case "birth":
                    if (getBirth() != null) {
                        return getBirth().format(formatter);
                    } else {
                        return null;
                    }
                case "gender":
                    return getGender();
                case "number":
                    return getPhoneNumber();
                default:
                    System.out.println("This field does not exist");
                    return null;
        }
    }

    String getHead() {
        String str = "";
        str += name;
        str += " " + surname;
        return str;
    }

    @Override
    public String toString() {
        String noData = "[no data]\n";
        String str = "";
        if (name != null) {
            str += "Name: " + name + "\n";
        } else {
            str += "Name: " + noData;
        }
        if (surname != null) {
            str += "Surname: " + surname + "\n";
        } else {
            str += "Surname: " + noData;
        }
        if (birth != null) {
            str += "Birth date: " + birth + "\n";
        } else {
            str += "Birth date: " + noData;
        }
        if (gender != null) {
            str += "Gender: " + gender + "\n";
        } else {
            str += "Gender: " + noData;
        }
        str += super.toString();
        return str;
    }
}

class Organization extends Record {
    private String name;
    private String address;

    Organization() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    String[] getFields() {
        return new String[] {"name", "address", "number"};
    }

    @Override
    boolean editField(String fieldName, String newValue) {
        switch (fieldName) {
            case "name":
                setName(newValue);
            case "address":
                setAddress(newValue);
                break;
            case "number":
                setPhoneNumber(newValue);
                break;
            default:
                System.out.println("This field does not exist");
                return false;
        }
        return true;
    }

    @Override
    String getFieldValue(String fieldName){
        switch (fieldName) {
            case "name":
                return getName();
            case "address":
                return getAddress();
            case "number":
                return getPhoneNumber();
            default:
                System.out.println("This field does not exist");
                return null;
        }
    }

    @Override
    String getHead() {
        return name;
    }

    @Override
    public String toString() {
        String noData = "[no data]\n";
        String str = "";
        if (name != null) {
            str += "Organization name: " + name + "\n";
        } else {
            str += "Organization name: " + noData;
        }
        if (address != null) {
            str += "Address: " + address + "\n";
        } else {
            str += "Address: " + noData;
        }
        str += super.toString();
        return str;
    }
}


