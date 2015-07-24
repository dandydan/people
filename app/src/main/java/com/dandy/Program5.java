package com.dandy;

import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.PersonService;
import com.dandy.infra.HibernateUtil;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Program5 {
    PersonService personService = new PersonService();
    InputValidator inputValidator = new InputValidator();

    public static void main(String[] args) {
	int                  choice = 0;
    boolean                 run = true;
        Program5           program5 = new Program5();
        do {
            System.out.println("DATABASE OF PERSONS");
            System.out.println("1. Add Person");
            System.out.println("2. Edit Person Info");
            System.out.println("3. Remove Person");
            System.out.println("4. Add Contact Info");
            System.out.println("5. Remove Contact Info");
            System.out.println("6. Add Role");
            System.out.println("7. Remove Role");
            System.out.println("8. Search by LastName");
            System.out.println("9. Search by Role");
            System.out.println("10. Sort by Name");
            System.out.println("11. Sort by Birthday");
            System.out.println("12. Sort by GWA");
            System.out.println("13. Exit");
            choice = program5.inputValidator.integerChecker();
            switch (choice) {
                case 1:
                    program5.addPersonInput();
                    break;
                case 2:
                    program5.searchPersonInput(choice);
                    break;
                case 3:
                    program5.searchPersonInput(choice);
                    break;
                case 4:
                    program5.updatePersonContactInput();
                    break;
		case 5:
		    program5.deleteContactsInput();
		    break;
		case 6:
                    program5.updatePersonRoleInput();
		    break;
		case 7:
		    program5.displayPersonSortByBirthday();
		    break;
		case 8:
		    program5.displayPersons();
                    //personService.getPersonById(1);

		    break;
		case 9:
		    run=false;
		    break;
		case 10:
		    program5.displayPersonSortByName();
		    break;
		case 11:
		    run=false;
		    break;
		case 12:
		    run=false;
		    break;
		case 13:
		    run=false;
		    break;
		default:
		    System.out.println("Please choose 1 to 9 :");
		    break;
            }
        } while (run);
        HibernateUtil.closeSessionFactory();
    }


    public void updatePersonRoleInput() {
        int choice = 0;
        boolean run = true;
        List<Integer> roleIds = new ArrayList<Integer>();
        System.out.print("Enter ID number: ");
        int personId = inputValidator.integerChecker();
        Person person = personService.getPersonById(personId);
        if (person.getPersonId() != 0) {
            do {
                 System.out.println("1. President");
                 System.out.println("2. Manager");
                 System.out.println("3. Team Leader");
                 System.out.println("4. Senior Developer");
                 System.out.println("5. Junior Developer");
                 System.out.println("6. Done");
                 choice = inputValidator.integerChecker();
                 if(choice==6){
                     run = false;
                 } else if (choice>6) {
                   System.out.println("choose 1 to 6");
                 } else {
                   roleIds.add(choice);
                 }
            }while(run);
            personService.addRoles(personId, roleIds);
        } else {
            System.out.println("Person not found!");
        }
    }


    public void addPersonInput() {
	Person               person = new Person();
        Set<Contact>       contacts = new HashSet<Contact>();
        Address             address = new Address();
        boolean run = true;
        int  choice = 0;

        System.out.print("Title: ");
        person.setTitle(inputValidator.simpleString());
        System.out.print("Firstname: ");
        person.setFirstName(inputValidator.simpleString());
        System.out.print("Middlename: ");
        person.setMiddleName(inputValidator.simpleString());
        System.out.print("Lastname: ");
        person.setLastName(inputValidator.simpleString());
        System.out.print("Suffix: ");
        person.setSuffix(inputValidator.simpleString());

        System.out.print("Street No.: ");
        address.setStNo(inputValidator.integerChecker());
        System.out.print("Brgy: ");
        address.setBrgy(inputValidator.simpleString());       
        System.out.print("Subdivision: ");
        address.setSubdivision(inputValidator.simpleString());     
        System.out.print("City: ");
        address.setCity(inputValidator.simpleString());   
        System.out.print("Zipcode: ");
        address.setZipcode(inputValidator.integerChecker());

        do {
            System.out.println("1. Add a contact");
            System.out.println("2. Exit from adding contact");
            choice = inputValidator.integerChecker();
            switch (choice) {
                case 1:
                    Contact contact = new Contact();

                    System.out.println("Contact Description: ");
                    contact.setDescription(inputValidator.contactDescriptor());
                    System.out.print("Contact Number: ");
                    contact.setNumber(inputValidator.longChecker());
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
        person.setBirthday(inputValidator.dateFormatter());
        System.out.print("Employed: ");
	person.setEmployed(inputValidator.employmentProcess());
        System.out.print("GWA: ");
	person.setGwa(inputValidator.gwaChecker());
        System.out.print("Gender: ");
        person.setGender(inputValidator.genderProcess());

        personService.addPerson(person, address, contacts);
    }

    public void searchPersonInput(int choice) {
        System.out.print("Enter ID number: ");
        int personId = inputValidator.integerChecker();
        Person person = personService.getPersonById(personId);
        if (person.getPersonId() == 0) {
            choice = 1;
        }
        switch (choice) {
            case 1:
                System.out.println("Person not found");
                break;
            case 2:
                updatePersonInput(person);
               break;
            case 3:
                removePersonInput(person);
            break;
        }
    }

    public void updatePersonInput(Person person) {
         
        System.out.println("Current Firstname: "+person.getFirstName());
        System.out.print("New Firstname: ");
        person.setFirstName(inputValidator.simpleString());
        System.out.println("Current Middlename: "+person.getMiddleName());
        System.out.print("New middlename: ");
        person.setMiddleName(inputValidator.simpleString());
        System.out.println("Current Lastname: "+person.getLastName());
        System.out.print("New lastname: ");
        person.setLastName(inputValidator.simpleString());
        System.out.println("Current Suffix: "+person.getSuffix());
        System.out.print("New suffix: ");
        person.setSuffix(inputValidator.simpleString());
        System.out.println("Current Title: "+person.getTitle());
        System.out.print("New title: ");
        person.setTitle(inputValidator.simpleString());

        System.out.println("Current Street No.: "+person.getAddress().getStNo());
        System.out.print("Street No.: ");
        person.getAddress().setStNo(inputValidator.integerChecker());
        System.out.println("Current Brgy: "+person.getAddress().getBrgy());
        System.out.print("Brgy: ");
        person.getAddress().setBrgy(inputValidator.simpleString());       
        System.out.println("Current Subdivision: "+person.getAddress().getSubdivision());
        System.out.print("Subdivision: ");
        person.getAddress().setSubdivision(inputValidator.simpleString());     
        System.out.println("Current City: "+person.getAddress().getCity());
        System.out.print("City: ");
        person.getAddress().setCity(inputValidator.simpleString());   
        System.out.println("Current Zipcode: "+person.getAddress().getZipcode());
        System.out.print("Zipcode: ");
        person.getAddress().setZipcode(inputValidator.integerChecker());
        System.out.println("Current Birthday: YYYY-MM-DD "+ person.getBirthday());
        System.out.print("Birthday: MM-DD-YYYY ");
        person.setBirthday(inputValidator.dateFormatter());
        System.out.println("Current Employment Status: "+person.getEmployed());
        System.out.print("Employed: 1. Yes \t 2. No");
	person.setEmployed(inputValidator.employmentProcess());
        System.out.println("Current GWA: "+person.getGwa());
        System.out.print("GWA: ");
	person.setGwa(inputValidator.gwaChecker());
        System.out.println("Current Gender: "+person.getGender());
	person.setGender(inputValidator.genderProcess());
        personService.updatePerson(person);
    }

    public void removePersonInput(Person person) {
        personService.removePerson(person);
    }

    public void updatePersonContactInput() {
        int choice = 0;
        boolean run = true;

        System.out.print("Enter ID number: ");
        int personId = inputValidator.integerChecker();
        Person person = personService.getPersonById(personId);

        if (person.getPersonId() != 0) {
            do {
                 System.out.println("1. Add a contact");
                 System.out.println("2. Exit from adding contact");
                 choice = inputValidator.integerChecker();
                 switch (choice) {
                    case 1:
                        Contact contact = new Contact();
                        System.out.println("Contact Description: ");
                        contact.setDescription(inputValidator.contactDescriptor());
                        System.out.print("Contact Number: ");
                        contact.setNumber(inputValidator.longChecker());
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

    public void deleteContactsInput() {

        System.out.print("Enter ID number: ");
        int personId = inputValidator.integerChecker();
        Person person = personService.getPersonById(personId);
        if (person.getPersonId() != 0) {
            personService.removeContacts(person);
        } else {
            System.out.println("Person not found!");
        }
    }
    
    public void displayPersonSortByName() {
        display(personService.getPersonSortedByName());
    }

    public void displayPersonSortByBirthday() {
        display(personService.getPersonSortedByBirthday());
    }

    public void displayPersons() {
        display(personService.getPersons());
    }

    public void display(List<Person> persons) {
        for(Person person : persons) {
            System.out.print("ID: " + person.getPersonId());
            System.out.print("\tName: " + person.getLastName() + " "
                               + person.getFirstName() + " " + person.getMiddleName());
            System.out.print("\t\tBirthday: " + person.getBirthday());
            System.out.print("\tGWA: " + person.getGwa());
           // for(Contact contact : person.getContacts()){
             //   System.out.println("***" + contact.getNumber() +"\t**");
           // }
        }
    }
}
