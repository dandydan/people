package com.dandy.core;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.FloatValidator;

public class InputService {

    public int integerChecker(Scanner scanner) {
        int number = -1;
        do {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Must be a number");
                scanner.nextLine();
            }
        } while (number < 0);
        return number;
    }
    
    
    public java.sql.Date dateFormatter(Scanner scanner) {
        DateValidator validator = DateValidator.getInstance();
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean valid = false;
        Date javaDate = null;
        java.sql.Date sql = null;
        do {
            String date = scanner.nextLine();
            valid = validator.isValid(date, "MM-dd-yyyy");
            if (valid) {
                try {
	            Date parsed = inputFormat.parse(date);
                    String sqlString = new SimpleDateFormat("yyyy-MM-dd").format(parsed);
                    Date sqlDate = sqlFormat.parse(sqlString);
		    sql = new java.sql.Date(sqlDate.getTime());
                    int compare = validator.compareYears(parsed, new Date(), null);
                    if (compare > -1) {
                        System.out.println("Must be lower than this year");
                        valid = false;
                    }
                } catch (ParseException e) {
                    valid = false;          
                }
            } else {
                System.out.println("Wrong format");
            }
        }while(!valid);
	return sql;
    }

    public float gwaChecker(Scanner scanner) {
        FloatValidator floatValidator = new FloatValidator();
        boolean valid = false;
        float input = (float)0.0;
        do {
            try {
                input = scanner.nextFloat();
                valid = floatValidator.isInRange(input, 1.0, 5.0);
            } catch (InputMismatchException e) {
                System.out.println("1.0 to 5.0 only");
                scanner.nextLine();
            }
        } while (!valid);
        return input;
    }

    public Gender genderProcess(Scanner scanner) {
        Gender gender = Gender.Male;
        int choice;
        boolean run = true;
        do {
            System.out.print("1. Male \t2. Female \t3. Undecided");
            choice = integerChecker(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
           	    gender = Gender.Male;
                    run = false;
                    break;
                case 2:
           	    gender = Gender.Female;
                    run = false;
                    break;
                case 3:
           	    gender = Gender.Undecided;
                    run = false;
                    break;
                default:
		    System.out.println("Please choose 1, 2 or 3 :");
		    break;
            }
        }while(run);
        return gender;
    }


    public String employmentProcess(Scanner scanner) {
        String employment="";
        int choice;
        boolean run = true;
        do {
            System.out.print("1. Yes");
            System.out.println("\t2. No");
            choice = integerChecker(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
           	    employment = "Yes";
                    run = false;
                    break;
                case 2:
           	    employment = "No";
                    run = false;
                    break;
                default:
		    System.out.println("Please choose 1 or 2 :");
		    break;
            }
        }while(run);
        return employment;
    }

    public String contactDescriptor(Scanner scanner) {
        String contactDescription="";
        int choice;
        boolean run = true;
        do {
            System.out.print("1. Telephone");
            System.out.println("\t2. Cellphone");
            choice = integerChecker(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
           	    contactDescription = "Telephone";
                    run = false;
                    break;
                case 2:
           	    contactDescription = "Cellphone";
                    run = false;
                    break;
                default:
		    System.out.println("Please choose 1 or 2 :");
		    break;
            }
        }while(run);
        return contactDescription;
    }

    public long longChecker(Scanner scanner) {
        long number = -1;
        do {
            try {
                number = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
        } while (number < 0);
        return number;
    }
}
