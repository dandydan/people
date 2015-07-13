package com.dandy;

import com.dandy.core.Person;
import com.dandy.core.Name;
import com.dandy.core.Address;
import com.dandy.core.PersonService;
import com.dandy.core.InputService;
import com.dandy.infra.HibernateUtil;
import java.text.ParseException;
import java.util.Scanner;

public class Program5 {
    public static void main(String[] args) {
        Scanner        scanner  = new Scanner(System.in);
        Name name = new Name();
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
                    program5.addPersonInput(scanner, personService, person, name, address);
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

    public void addPersonInput(Scanner scanner, PersonService personService, Person person, Name name, Address address) {
        System.out.print("Enter your birthday: ");
        try {
            person.setBirthday(personService.dateFormatter("2011-02-02"));
        } catch (ParseException e) {
  	}
        

        address.setStNo(1);
        address.setBrgy("San Jose");
        address.setSubdivision("Unknown");
        address.setCity("Antipolo");
        address.setZipcode(1870);
       // System.out.print("Enter your gwa: ");
	person.setGwa((float)3.2);
       // System.out.print("Enter your gender: ");
	person.setGender("male");
        //System.out.print("Enter your employment status: ");
	person.setEmploymentStatus("none");
	name.setFirstName("Dandy");
        name.setLastName("Bertuldo");
        name.setMiddleName("Cantor");
        name.setSuffix("ph");
        name.setTitle("Mr.");
        personService.addPerson(person, name, address);
    }
}
