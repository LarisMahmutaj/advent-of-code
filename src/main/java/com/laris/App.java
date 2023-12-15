package com.laris;

import com.laris.interfaces.Day;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter year ('Q' to quit): ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                System.out.print("Quitting..");
                System.exit(0);
            }
            int year;
            try {
                year = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input!");
                continue;
            }

            System.out.print("Enter day number: ");
            int dayNumber = sc.nextInt();

            Day day;
            try {
                Class<?> clazz = Class.forName(
                        "Y" + year + ".days.Day" + String.format("%02d", dayNumber) + ".Day" + String.format("%02d",
                                dayNumber));
                day = (Day) clazz.getConstructor().newInstance();

                System.out.print("Enter part to be executed: ");
                int part = sc.nextInt();
                System.out.println("Calculating..");
                switch (part) {
                    case 1 -> day.partOne();
                    case 2 -> day.partTwo();
                    default -> System.out.println("Invalid part value!");
                }
            } catch (ClassNotFoundException e) {
                System.out.println(
                        "ERROR: Day" + String.format("%02d", dayNumber) + " of year " + year + " was not found.");
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
