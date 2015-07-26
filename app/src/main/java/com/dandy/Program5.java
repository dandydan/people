package com.dandy;

import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.Role;
import com.dandy.core.ResultModel;
import com.dandy.core.ResultComparator;
import com.dandy.core.PersonService;
import com.dandy.infra.HibernateUtil;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Program5 {
    PersonService  personService  = new PersonService();
    InputValidator inputValidator = new InputValidator();

    public static void main(String[] args) {
        int           choice = 0;
        boolean          run = true;
        Program5    program5 = new Program5();

        do {
            System.out.println("DATABASE OF PERSONS");
            System.out.println("1. Add Person");
            System.out.println("2. Edit Person Info");
            System.out.println("3. Edit Address");
            System.out.println("4. Remove Person");
            System.out.println("5. Add Contact Info");
            System.out.println("6. Remove Contact Info");
            System.out.println("7. Add Role");
            System.out.println("8. Remove Role");
            System.out.println("9. Search by LastName");
            System.out.println("10. Search by Role");
            System.out.println("11. Sort by Lastame");
            System.out.println("12. Sort by Birthday");
            System.out.println("13. Sort by GWA");
            System.out.println("14. Exit");

            choice = program5.inputValidator.integerChecker();
            if(choice==1) {
                program5.addPersonInput();
            } else if (choice<=8) {
                program5.updatePersonInput(choice);
            } else if (choice<=13) {
		program5.displayPersons(choice);
            } else if (choice==14) {
                run = false;
            } else {
                System.out.println("Please choose 1 to 14 :");
            }
        } while (run);
        HibernateUtil.closeSessionFactory();
    }

    public void updatePersonInput(int choice) {
        System.out.print("Enter ID number: ");
        int personId = inputValidator.integerChecker();
        Person person = personService.getPersonById(personId);

        if(person.getPersonId() == 0) {
            System.out.println("Person not found!");
        } else {
            switch(choice) {
                case 2:
                  personService.updatePerson(editPerson(person));
                  break;
                case 3:
                  personService.updatePerson(editAddress(person));
                  break;
                case 4:
                  personService.removePerson(person);
                  break;
                case 5:
                  personService.updatePerson(addContacts(person));
                  break;
                case 6:
                  personService.removeContacts(person);
                  break;
                case 7:
                  updatePersonRoleInput(person);
                  break;
                case 8:
                  removePersonRoleInput(person);
                  break;
            }
        }
    }

    public Person editPerson(Person person) {
        String edit = "";

        if(person.getPersonId()!=0) {
            edit = "New ";
            System.out.println("Current Firstname: " + person.getFirstName());
            System.out.println("Current Middlename: " + person.getMiddleName());
            System.out.println("Current Lastname: " + person.getLastName());
            System.out.println("Current Suffix: " + person.getSuffix());
            System.out.println("Current Title: " + person.getTitle());
            System.out.println("Current Birthday: YYYY-MM-DD " + person.getBirthday());
            System.out.println("Current Employment Status: " + person.getEmployed());
            System.out.println("Current GWA: " + person.getGwa());
            System.out.println("Current Gender: " + person.getGender());
        } 
        System.out.print(edit + "Title: ");
        person.setTitle(inputValidator.simpleString());
        System.out.print(edit + "Firstname: ");
        person.setFirstName(inputValidator.simpleString());
        System.out.print(edit + "Middlename: ");
        person.setMiddleName(inputValidator.simpleString());
        System.out.print(edit + "Lastname: ");
        person.setLastName(inputValidator.simpleString());
        System.out.print(edit + "Suffix: ");
        person.setSuffix(inputValidator.simpleString());
        System.out.print(edit + "Birthday in this format MM-DD-YYYY: ");
        person.setBirthday(inputValidator.dateFormatter());
        System.out.print(edit + "Employed: ");
	person.setEmployed(inputValidator.employmentProcess());
        System.out.print(edit + "GWA: ");
	person.setGwa(inputValidator.gwaChecker());
        System.out.print(edit + "Gender: ");
        person.setGender(inputValidator.genderProcess());

        return person;
    }

    public Person editAddress(Person person) {
        String edit = "";

        if(person.getPersonId()!=0) {
            edit = "New ";
            System.out.println("Current Street No.: " + person.getAddress().getStNo());
            System.out.println("Current Brgy: " + person.getAddress().getBrgy());
            System.out.println("Current Subdivision: " + person.getAddress().getSubdivision());
            System.out.println("Current City: " + person.getAddress().getCity());
            System.out.println("Current Zipcode: " + person.getAddress().getZipcode());
        }
        System.out.print(edit + "Street No.: ");
        person.getAddress().setStNo(inputValidator.integerChecker());
        System.out.print(edit + "Brgy: ");
        person.getAddress().setBrgy(inputValidator.simpleString());
        System.out.print(edit + "Subdivision: ");
        person.getAddress().setSubdivision(inputValidator.simpleString());
        System.out.print(edit + "City: ");
        person.getAddress().setCity(inputValidator.simpleString()); 
        System.out.print(edit + "Zipcode: ");
        person.getAddress().setZipcode(inputValidator.integerChecker());

        return person;
    }

    public Person addContacts(Person person) {
        int choice = 0;
        boolean run = true;
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
            } while(run);
        return person;
    }

    public void addPersonInput() {
        int  choice = 0;
        boolean run = true;
	Person         person = new Person();
        Address       address = new Address();
        Set<Contact> contacts = new HashSet<Contact>();

        person = editPerson(person);
        person.setAddress(address);
        person = editAddress(person);
        person.setContacts(contacts);
        person = addContacts(person);
        address.setPerson(person);
        personService.addPerson(person);
    }

    public void updatePersonRoleInput(Person person) {
        int choice = 0;
        boolean run = true;
        List<Role> roles = personService.getRoles();
        do {
            for(Role role: roles) {
                System.out.println(role.getRoleId() + ". " + role.getPos());
            }
            System.out.println("6. Done");
            choice = inputValidator.integerChecker();
            if(choice==6){
                run = false;
            } else if (choice>6) {
                System.out.println("choose 1 to 6");
            } else {
                personService.addRoles(person.getPersonId(), choice);
            }
        }while(run);
    }

    public void removePersonRoleInput(Person person) {
        boolean run = true;
        do {
             int choice = 0;

             List<Role> roles = personService.getRolesById(person.getPersonId());
             for(Role role: roles) {
                 System.out.println((++choice) + ". " + role.getPos());
             }
             System.out.println((++choice) + ". Done");
             choice = inputValidator.integerChecker();
             if(choice==(roles.size()+1)){
                 run = false;
             } else if (choice>roles.size()) {
                 System.out.println("choose 1 to " + (roles.size()+1));
             } else {
                  personService.removeRole(person.getPersonId(), roles.get(--choice).getRoleId());
             }
        }while(run);
    }

    public void displayPersons(int conditionVar) {
        String stringToSearch = "";

        if(conditionVar==9 || conditionVar == 10) {
            System.out.print("Enter a string: ");
            stringToSearch = inputValidator.simpleString();
        }
        List<ResultModel> result = personService.getPersons(conditionVar, stringToSearch);
        if(conditionVar==13) {
             Collections.sort(result, new ResultComparator());
        }
        for(ResultModel resultModel : result) {
            System.out.print("ID: " + resultModel.getPersonId());
            System.out.print("\tFirstname: " + resultModel.getFirstName());
            System.out.print(" Lastname: " + resultModel.getLastName());
            System.out.print("\tGWA: " + resultModel.getGwa());
            System.out.print("\tZipcode: " + resultModel.getZipcode());
            System.out.print("\tNumber:");
            if(resultModel.getNumber()!= null) {
                for(Long number : resultModel.getNumber()) {
                    System.out.print(" " + number);
                }
            }
            System.out.print("\tRole: ");
            if(resultModel.getPos()!= null) {
                for(String pos : resultModel.getPos()) {
                    System.out.print(" " + pos);
                }
            }
            System.out.println();
        }
    }
}
