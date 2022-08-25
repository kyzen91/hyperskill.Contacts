package contacts;

import java.util.Scanner;

@Deprecated
interface DetailRecordFactory {
    Record createRecord();
}

@Deprecated
class PersonRecordCreator implements DetailRecordFactory {
    @Override
    public Record createRecord() {
        Scanner keyboard = new Scanner(System.in);
        Validator validator = new Validator();
        Person person = new Person();

        System.out.print("Enter the name: ");
        String name = keyboard.nextLine();
        person.setName(name);

        System.out.print("Enter the surname: ");
        String surname = keyboard.nextLine();
        person.setSurname(surname);

        System.out.print("Enter the birth date: ");
        String date = keyboard.nextLine();
        person.setBirthDate(date);

        System.out.print("Enter the gender (M, F): ");
        String gender = keyboard.nextLine();
        person.setGender(gender);

        System.out.print("Enter the number: ");
        String phoneNumber = keyboard.nextLine();
        person.setPhoneNumber(phoneNumber);

        return person;
    }
}

@Deprecated
class OrganizationRecordCreator implements DetailRecordFactory {
    @Override
    public Record createRecord() {
        Scanner keyboard = new Scanner(System.in);
        Validator validator = new Validator();
        Organization organization = new Organization();

        System.out.print("Enter the organization name: ");
        String name = keyboard.nextLine();
        organization.setName(name);

        System.out.print("Enter the address: ");
        String address = keyboard.nextLine();
        organization.setAddress(address);

        System.out.print("Enter the number: ");
        String phoneNumber = keyboard.nextLine();
        organization.setPhoneNumber(phoneNumber);

        return organization;
    }
}
