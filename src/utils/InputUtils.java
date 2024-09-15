package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);



    public static int readInt(String prompt) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : La valeur doit être un nombre entier.");
                scanner.next();
            }
        }
        return value;
    }

    public static String readString(String prompt) {
        String value = "";
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            value = scanner.nextLine();
            if (!value.trim().isEmpty()) {
                valid = true;
            } else {
                System.out.println("Erreur : Veuillez entrer un texte valide.");
            }
        }
        return value;
    }



    public static Double readDouble(String prompt) {
        double value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = scanner.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : La valeur doit être un nombre double.");
                scanner.next();
            }
        }
        return value;
    }



    public static LocalDate readDate(String prompt) {
        LocalDate value = null;
        boolean valid = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (!valid) {
            System.out.print(prompt);
            try {
                value = LocalDate.parse(scanner.nextLine().trim(), formatter);
                valid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide. Veuillez entrer une date au format JJ/MM/AAAA.");
            }
        }
        return value;
    }



    public static LocalDate readEndDateAfterStart(String prompt, LocalDate startDate) {
        LocalDate endDate;
        do {
            endDate = readDate(prompt);
            if (!endDate.isAfter(startDate)) {
                System.out.println("La date de fin doit être après la date de début. Veuillez entrer une date valide.");
            }
        } while (!endDate.isAfter(startDate));
        return endDate;
    }

}