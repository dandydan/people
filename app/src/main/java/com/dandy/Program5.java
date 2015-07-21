package com.dandy;

import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.PersonService;
import com.dandy.core.InputService;
import com.dandy.infra.HibernateUtil;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.List;

public class Program5 {
    public static void main(String[] args) {
	boolean                 run = true;
	int                  choice = 0;
        Program5           program5 = new Program5();
        Scanner             scanner = new Scanner(System.in);
	PersonService personService = new PersonService();
        InputService   inputService = new InputService();

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
                    program5.addPersonInput(scanner, personService, inputService);
                    break;
                case 2:
                    program5.searchPersonInput(personService, scanner, inputService, choice);
                    break;
                case 3:
                    program5.searchPersonInput(personService, scanner, inputService, choice);
                    break;
                case 4:
                    program5.updatePersonContactInput(personService, scanner, inputService);
                    break;
		case 5:
		    program5.deleteContactsInput(personService, scanner, inputService);
		    break;
		case 6:
		    program5.displayPersonSortByName(personService);
		    break;
		case 7:
		    program5.displayPersonSortByBirthday(personService);
		    break;
		case 8:
		    program5.displayPersons(personService);
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

    public void addPersonInput(Scanner scanner, PersonService personService, InputService inputService) {
	Person               person = new Person();
        Set<Contact>       contacts = new HashSet<Contact>();
        Address             address = new Address();
        boolean run = true;
        int  choice = 0;

        System.out.print("Title: ");
        person.setTitle(scanner.nextLine());
        System.out.print("Firstname: ");
        person.setFirstName(scanner.nextLine());
        System.out.print("Middlename: ");
        person.setMiddleName(scanner.nextLine());
        System.out.print("Lastname: ");
        person.setLastName(scanner.nextLine());
        System.out.print("Suffix: ");
        person.setSuffix(scanner.nextLine());

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

                    System.out.println("Contact Description: ");
                    contact.setDescription(inputService.contactDescriptor(scanner));
                    System.out.print("Contact Number: ");
                    contact.setNumber(inputService.longChecker(scanner));
                    scanner.nextLine();
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
        System.out.print("Employed: ");
	person.setEmploymentStatus(inputService.employmentProcess(scanner));
        System.out.print("GWA: ");
	person.setGwa(inputService.gwaChecker(scanner));
        scanner.nextLine();
        System.out.print("Gender: ");
        person.setGender(inputService.genderProcess(scanner));

        personService.addPerson(person, address, contacts);
    }

    public void searchPersonInput(PersonService personService, Scanner scanner, InputService inputService, int choice) {
        String firstName;
        String middleName;
        String lastName;
        System.out.print("Firstname: ");
        firstName = scanner.nextLine();
        System.out.print("Middlename: ");
        middleName = scanner.nextLine();
        System.out.print("Lastname: ");
        lastName = scanner.nextLine();
        Person person = personService.getPerson(firstName, middleName, lastName);
        if (person.getPersonId() == 0) {
            choice = 1;
        }
        switch (choice) {
            case 1:
                System.out.println("Person not found");
                break;
            case 2:
                updatePersonInput(personService, scanner, inputService, person);
               break;
            case 3:
                removePersonInput(personService, person);
            break;
        }
    }

    public void updatePersonInput(PersonService personService, Scanner scanner, InputService inputService, Person person) {

        System.out.println("Current Firstname: "+person.getFirstName());
        System.out.print("New Firstname: ");
        person.setFirstName(scanner.nextLine());
        System.out.println("Current Middlename: "+person.getMiddleName());
        System.out.print("New middlename: ");
        person.setMiddleName(scanner.nextLine());
        System.out.println("Current Lastname: "+person.getLastName());
        System.out.print("New lastname: ");
        person.setLastName(scanner.nextLine());
        System.out.println("Current Suffix: "+person.getSuffix());
        System.out.print("New suffix: ");
        person.setSuffix(scanner.nextLine());
        System.out.println("Current Title: "+person.getTitle());
        System.out.print("New title: ");
        person.setTitle(scanner.nextLine());

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
        System.out.println("Current Birthday: YYYY-MM-DD "+ person.getBirthday());
        System.out.print("Birthday: MM-DD-YYYY ");
        person.setBirthday(inputService.dateFormatter(scanner));
        System.out.println("Current Employment Status: "+person.getEmploymentStatus());
        System.out.print("Employed: 1. Yes \t 2. No");
	person.setEmploymentStatus(inputService.employmentProcess(scanner));
        System.out.println("Current GWA: "+person.getGwa());
        System.out.print("GWA: ");
	person.setGwa(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Current Gender: "+person.getGender());
        System.out.print("Gender: 1. Male \t 2. Female");
	person.setGender(scanner.nextLine());
        personService.updatePerson(person);
    }

    public void removePersonInput(PersonService personService, Person person) {
        personService.removePerson(person);
    }

    public void updatePersonContactInput(PersonService personService, Scanner scanner, InputService inputService) {
        String firstName;
        String middleName;
        String lastName;
        int choice = 0;
        boolean run = true;;

        System.out.print("Firstname: ");
        firstName = scanner.nextLine();
        System.out.print("Middlename: ");
        middleName = scanner.nextLine();
        System.out.print("Lastname: ");
        lastName = scanner.nextLine();

        Person person = personService.getPerson(firstName, middleName, lastName);
        if (person.getPersonId() != 0) {
            do {
                 System.out.println("1. Add a contact");
                 System.out.println("2. Exit from adding contact");
                 choice = inputService.integerChecker(scanner);
                 scanner.nextLine();
                 switch (choice) {
                    case 1:
                        Contact contact = new Contact();
                        System.out.println("Contact Description: ");
                        contact.setDescription(inputService.contactDescriptor(scanner));
                        System.out.print("Contact Number: ");
                        contact.setNumber(inputService.longChecker(scanner));
                        scanner.nextLine();
                        person.getContacts().add(contact);
                        break;
                    case 2:
                        run=false;
                        break;
                    default:
		        System.out.println("Please choose 1 or 2 :");
		        break;
                 }
            }while(run);
            personService.addContacts(person);
        } else {
            System.out.println("Person not found!");
        }
    }

    public void deleteContactsInput(PersonService personService, Scanner scanner, InputService inputService) {
        String firstName;
        String middleName;
        String lastName;
        System.out.print("Firstname: ");
        firstName = scanner.nextLine();
        System.out.print("Middlename: ");
        middleName = scanner.nextLine();
        System.out.print("Lastname: ");
        lastName = scanner.nextLine();
        Person person = personService.getPerson(firstName, middleName, lastName);
        if (person.getPersonId() != 0) {
            personService.removeContacts(person);
        } else {
            System.out.println("Person not found!");
        }
    }
    
    public void displayPersonSortByName(PersonService personService) {
        display(personService.getPersonSortedByName());
    }

    public void displayPersonSortByBirthday(PersonService personService) {
        display(personService.getPersonSortedByBirthday());
    }

    public void displayPersons(PersonService personService) {
        display(personService.getPersons());
    }

    public void display(List<Person> persons) {
        for(Person person : persons) {
            System.out.print("ID: " + person.getPersonId());
            System.out.print("\tName: " + person.getLastName() + " "
                               + person.getFirstName() + " " + person.getMiddleName());
            System.out.print("\t\tBirthday: " + person.getBirthday());
            System.out.println("\tGWA: " + person.getGwa());
        } 
    }
}
