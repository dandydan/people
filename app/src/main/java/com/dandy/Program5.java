package com.dandy;

import com.dandy.core.Person;
import com.dandy.core.Name;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.PersonService;
import com.dandy.core.InputService;
import com.dandy.infra.HibernateUtil;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;

public class Program5 {
    public static void main(String[] args) {
        Scanner        scanner  = new Scanner(System.in);
        Name name = new Name();
        Set<Contact> contacts = new HashSet<Contact>();
        Address address = new Address();
	PersonService personService = new PersonService();
        InputService inputService = new InputService();
        Program5 program5 = new Program5();
	Person person = new Person();
	boolean        run      = true;
	int            choice   = 0;
        do {
            System.out.println("DATABASE OF PERSONS");
            System.out.println("1. Add Person");
            System.out.println("2. Edit Person Info");
            System.out.println("3. Remove Person");
            System.out.println("4. Add Contact Info");
            System.out.println("5. Remove Contact Info");
            System.out.println("6. Sort by Name");
            System.out.println("7. Sort by Birthday");
            System.out.println("8. Sort by GWA");
            System.out.println("9. Exit");
            choice = inputService.integerChecker(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    program5.addPersonInput(scanner, personService, person, name, address, contacts, inputService);
                    break;
                case 2:
                    program5.updatePerson(personService, scanner, inputService);
                    break;
                case 3:
                    program5.removePerson(personService, inputService);
                    break;
                case 4:
                    run=false;
                    break;
		case 5:
		    run=false;
		    break;
		case 6:
		    run=false;
		    break;
		case 7:
		    run=false;
		    break;
		case 8:
		    run=false;
		    break;
		case 9:
		    run=false;
		    break;
		default:
		    System.out.println("Please choose 1 to 9 :");
		    break;
            }
        } while (run);
        HibernateUtil.closeSessionFactory();
    }

    public void addPersonInput(Scanner scanner, PersonService personService, Person person,
                               Name name, Address address, Set<Contact> contacts, InputService inputService) {
        boolean run = true;
        int choice = 0;
        System.out.print("Firstname: ");
        name.setFirstName(scanner.nextLine());
        System.out.print("Middlename: ");
        name.setMiddleName(scanner.nextLine());
        System.out.print("Lastname: ");
        name.setLastName(scanner.nextLine());
        System.out.print("Suffix: ");
        name.setSuffix(scanner.nextLine());
        System.out.print("Title: ");
        name.setTitle(scanner.nextLine());

        System.out.print("Street No.: ");
        address.setStNo(inputService.integerChecker(scanner));
        scanner.nextLine();
        System.out.print("Brgy: ");
        address.setBrgy(scanner.nextLine());       
        System.out.print("Subdivision: ");
        address.setSubdivision(scanner.nextLine());     
        System.out.print("City: ");
        address.setCity(scanner.nextLine());   
        System.out.print("Zipcode: ");
        address.setZipcode(inputService.integerChecker(scanner));
        scanner.nextLine();
        do {
            System.out.println("1. Add a contact");
            System.out.println("2. Exit from adding contact");
            choice = inputService.integerChecker(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Contact contact = new Contact();
                    System.out.print("Contact Description: ");
                    contact.setDescription(scanner.nextLine());
                    System.out.print("Contact Number: ");
                    contact.setNumber(scanner.nextLong());
                    scanner.nextLine();
                    contact.setPerson(person);
                    contacts.add(contact);
                    break;
                case 2:
                    run=false;
                    break;
                default:
		    System.out.println("Please choose 1 or 2 :");
		    break;
             }
        }while(run);
        System.out.print("Birthday in this format MM-DD-YYYY: ");
        person.setBirthday(inputService.dateFormatter(scanner));
        
        System.out.print("Employment status: ");
	person.setEmploymentStatus(scanner.nextLine());
        System.out.print("GWA: ");
	person.setGwa(scanner.nextFloat());
        scanner.nextLine();
        System.out.print("Gender: ");
        run = true;
        do {
            System.out.println("1. Male");
            System.out.println("2. Female");
            choice = inputService.integerChecker(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
           	    person.setGender("Male");
                    run = false;
                    break;
                case 2:
           	    person.setGender("Female");
                    run = false;
                    break;
                default:
		    System.out.println("Please choose 1 or 2 :");
		    break;
            }
        }while(run);

        personService.addPerson(person, name, address, contacts);
    }

    public void updatePerson(PersonService personService, Scanner scanner, InputService inputService) {
        String firstName = "dsa";
        String lastName = "dsa";
        String middleName = "dsa";

        Person person = personService.getPerson(firstName, middleName, lastName);
        System.out.println("Current Firstname: "+person.getName().getFirstName());
        System.out.print("New Firstname: ");
        person.getName().setFirstName(scanner.nextLine());
        System.out.println("Current Middlename: "+person.getName().getMiddleName());
        System.out.print("New middlename: ");
        person.getName().setMiddleName(scanner.nextLine());
        System.out.println("Current Lastname: "+person.getName().getLastName());
        System.out.print("New lastname: ");
        person.getName().setLastName(scanner.nextLine());
        System.out.println("Current Suffix: "+person.getName().getSuffix());
        System.out.print("New suffix: ");
        person.getName().setSuffix(scanner.nextLine());
        System.out.println("Current Title: "+person.getName().getTitle());
        System.out.print("New title: ");
        person.getName().setTitle(scanner.nextLine());

        System.out.println("Current Street No.: "+person.getAddress().getStNo());
        System.out.print("Street No.: ");
        person.getAddress().setStNo(inputService.integerChecker(scanner));
        scanner.nextLine();
        System.out.println("Current Brgy: "+person.getAddress().getBrgy());
        System.out.print("Brgy: ");
        person.getAddress().setBrgy(scanner.nextLine());       
        System.out.println("Current Subdivision: "+person.getAddress().getSubdivision());
        System.out.print("Subdivision: ");
        person.getAddress().setSubdivision(scanner.nextLine());     
        System.out.println("Current City: "+person.getAddress().getCity());
        System.out.print("City: ");
        person.getAddress().setCity(scanner.nextLine());   
        System.out.println("Current Zipcode: "+person.getAddress().getZipcode());
        System.out.print("Zipcode: ");
        person.getAddress().setZipcode(inputService.integerChecker(scanner));
        scanner.nextLine();
        System.out.println("Current Birthday: "+person.getBirthday());
        System.out.print("Birthday: ");
        person.setBirthday(inputService.dateFormatter(scanner));
        System.out.println("Current Employment Status: "+person.getEmploymentStatus());
        System.out.print("Employment status: ");
	person.setEmploymentStatus(scanner.nextLine());
        System.out.println("Current GWA: "+person.getGwa());
        System.out.print("GWA: ");
	person.setGwa(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Current Gender: "+person.getGender());
        System.out.print("Gender: ");
	person.setGender(scanner.nextLine());
        personService.updatePerson(person);
    }

    public void removePerson(PersonService personService, InputService inputService) {
        String firstName = "dsa";
        String lastName = "";
        String middleName = "dsa";
        Person person = personService.getPerson(firstName, middleName, lastName);
        personService.removePerson(person);
    }



}
