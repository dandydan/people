package com.dandy.core;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    public java.sql.Date dateFormatter(String date) throws ParseException {
		java.sql.Date sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(date);
		sql = new java.sql.Date(parsed.getTime());
		return sql;
    }
}
