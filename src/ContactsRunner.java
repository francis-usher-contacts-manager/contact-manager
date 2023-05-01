package src;

import util.Input;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Files.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;


import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.write;
public class ContactsRunner {

    // building to file path
    String currentDirectory = System.getProperty("user.dir");
    String directory = currentDirectory + "/src/data";
    String fileName = "contacts.txt";
    Path filePath = Paths.get(directory, fileName);

    Charset charConvert = StandardCharsets.UTF_8;
    public static void main(String[] args) throws IOException {
        Boolean confirm;

        do {
            System.out.println("1. View Contacts");
            System.out.println("2. Add New Contact");
            System.out.println("3. Search a Contact By Name");
            System.out.println("4. Delete Existing Contact");
            System.out.println("5. Exit\n");

            System.out.println("Enter Selection: ");

            // building to file path
            String currentDirectory = System.getProperty("user.dir");
            String directory = currentDirectory + "/src/data";
            String fileName = "contacts.txt";
            Path filePath = Paths.get(directory, fileName);

            Charset charConvert = StandardCharsets.UTF_8;
            Input input = new Input();
            int initialInput = input.getInt();

            List<String> data = Files.readAllLines(filePath);
            List<String> newData = Files.readAllLines((filePath));

            List<String> lines;

            switch (initialInput) {
                case 1:
                    System.out.printf("\n%-15s | %-12s |\n", "Name", "Phone number");
                    System.out.println("--------------------------------");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println(data.get(i));
                    }
                    break;

                case 2:
                    System.out.println("Enter Contact First Name: ");
                    String contactFirstNameInput = input.getString();


                    System.out.println("Enter Contact Last Name: ");
                    String contactLastNameInput = input.getString();

                    System.out.println("Enter Contact Phone Number");
                    String contactPhoneNumber = String.valueOf(input.getInt());

                    String formattedName = contactFirstNameInput + " " + contactLastNameInput;
                    String formattedPhoneNumber = contactPhoneNumber;

                    // Pad the name and phone number strings with spaces to align them under the headers
                    int namePadding = 15 - formattedName.length();
                    int phonePadding = 12 - formattedPhoneNumber.length();
                    formattedName += " ".repeat(Math.max(0, namePadding));
                    formattedPhoneNumber = " ".repeat(Math.max(0, phonePadding)) + formattedPhoneNumber;

                    // Format the contact details in a tabular format
                    String formattedLine = String.format("%s | %s |", formattedName, formattedPhoneNumber);
                    lines = Arrays.asList(formattedLine);

                    System.out.println("You entered: " + formattedLine);

                    Files.write(filePath, lines, StandardOpenOption.APPEND);
                    break;

                case 3:
                    System.out.println("Enter the Contact Name: ");
                    String searchParameter = input.getString();
                    String correctname = null;

                    for (String name : data) {
                        if (name.contains(searchParameter)) {
                            correctname = name;
                        }
                    }

                    System.out.println(correctname);
                    break;

                case 4:
                    System.out.println("Enter the Name of the Contact You want to DELETE: ");
                    String deleteParameter = input.getString();

                    for (String name : data) {
                        if (name.contains(deleteParameter)) {
                            newData.remove(name);
                            System.out.println("You deleted: " + name);
                        }
                    }

                    Files.write(filePath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                    Files.write(filePath, newData, StandardOpenOption.APPEND);
                    break;

                case 5:
                    System.out.println("See you next time!");
                    break;

            }
            confirm = input.yesNo();
        } while(confirm);
    }

}
//jlklklxklx