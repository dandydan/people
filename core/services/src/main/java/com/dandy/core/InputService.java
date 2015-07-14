package com.dandy.core;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.validator.routines.DateValidator;

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
            System.out.println(date + "****" + valid);
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
            }
        }while(!valid);
	return sql;
    }

    public float floatChecker(Scanner scanner) {

    return (float)1.0;
    }




        
}
