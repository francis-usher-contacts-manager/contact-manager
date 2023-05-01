package src;

import util.Input;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Files.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

        List<String> lines;

        switch(initialInput) {
            case 1:
                System.out.println("Reading data file BEFORE writing new lines");
                for(int i = 0; i < data.size(); i++) {
                    System.out.println(data.get(i));
                }
                break;
            case 2:
                System.out.println("Enter Contact Name: ");
                String contactNameInput = input.getString();

                lines = Arrays.asList(contactNameInput);

                Files.write(filePath, lines, StandardOpenOption.APPEND);

                for(int i = 0; i < data.size(); i++) {
                    System.out.println((i + 1) + data.get(i));
                }
                break;
        }

    }

}
//jlklklxklx