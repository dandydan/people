package com.dandy.core;

import java.util.Scanner;
import java.util.InputMismatchException;

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
}
