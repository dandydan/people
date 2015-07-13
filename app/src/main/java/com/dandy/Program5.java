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
                    break;
                case 3:
                    break;
                case 4:
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

    public void addPersonInput(Scanner scanner, PersonService personService, Person person, Name name, Address address, Set<Contact> contacts, InputService inputService) {
        System.out.print("Your firstname: ");
        name.setFirstName(scanner.nextLine());
        System.out.print("Your middlename: ");
        name.setMiddleName(scanner.nextLine());
        System.out.print("Your lastname: ");
        name.setLastName(scanner.nextLine());
        System.out.print("Your suffix: ");
        name.setSuffix(scanner.nextLine());
        System.out.print("Your title: ");
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
        //standby for loop of sets
        Contact contact = new Contact();
        System.out.print("Contact Description: ");
        contact.setDescription(scanner.nextLine());
        System.out.print("Contact Number: ");
        contact.setNumber(scanner.nextLong());
        scanner.nextLine();
        contact.setPerson(person);
        contacts.add(contact);        
        //end of loops
        System.out.print("Your birthday: ");
        try {
            person.setBirthday(inputService.dateFormatter("2011-02-02"));
        } catch (ParseException e) {
  	}
        
        System.out.print("Your employment status: ");
	person.setEmploymentStatus(scanner.nextLine());
        System.out.print("Your GWA: ");
	person.setGwa(scanner.nextFloat());
        scanner.nextLine();
        System.out.print("Your gender: ");
	person.setGender(scanner.nextLine());
        personService.addPerson(person, name, address, contacts);
    }
}
