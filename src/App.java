import interfaces.Day;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter year: ");
            int year = sc.nextInt();

            System.out.print("Enter day number: ");
            int dayNumber = sc.nextInt();

            Day day;
            try {
                Class<?> clazz = Class.forName("Y" + year + ".days.Day" + dayNumber + ".Day" + dayNumber);
                day = (Day) clazz.getConstructor().newInstance();

                System.out.print("Enter part to be executed: ");
                int part = sc.nextInt();

                switch (part) {
                    case 1 -> day.partOne();
                    case 2 -> day.partTwo();
                    default -> System.out.println("Invalid part value!");
                }
            } catch (ClassNotFoundException e) {
                System.out.println("ERROR: Day" + dayNumber + " of year " + year + " was not found.");
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
