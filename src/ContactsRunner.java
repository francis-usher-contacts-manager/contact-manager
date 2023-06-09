import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class ContactsRunner {

    
    public static void main(String[] args) throws IOException {
        Boolean confirm;


        do {

            System.out.println("  ______   ______   .__   __. .___________.    ___       ______ .___________.    _______.   .___  ___.      ___      .__   __.      ___       _______  _______ .______      \n" +
                    " /      | /  __  \\  |  \\ |  | |           |   /   \\     /      ||           |   /       |   |   \\/   |     /   \\     |  \\ |  |     /   \\     /  _____||   ____||   _  \\     \n" +
                    "|  ,----'|  |  |  | |   \\|  | `---|  |----`  /  ^  \\   |  ,----'`---|  |----`  |   (----`   |  \\  /  |    /  ^  \\    |   \\|  |    /  ^  \\   |  |  __  |  |__   |  |_)  |    \n" +
                    "|  |     |  |  |  | |  . `  |     |  |      /  /_\\  \\  |  |         |  |        \\   \\       |  |\\/|  |   /  /_\\  \\   |  . `  |   /  /_\\  \\  |  | |_ | |   __|  |      /     \n" +
                    "|  `----.|  `--'  | |  |\\   |     |  |     /  _____  \\ |  `----.    |  |    .----)   |      |  |  |  |  /  _____  \\  |  |\\   |  /  _____  \\ |  |__| | |  |____ |  |\\  \\----.\n" +
                    " \\______| \\______/  |__| \\__|     |__|    /__/     \\__\\ \\______|    |__|    |_______/       |__|  |__| /__/     \\__\\ |__| \\__| /__/     \\__\\ \\______| |_______|| _| `._____|\n" +
                    "                                                                                                                                                                            ");
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

            Input input = new Input();
            int initialInput = input.getInt();

            List<String> data = Files.readAllLines(filePath);
            List<String> newData = Files.readAllLines((filePath));

            List<String> lines;

            switch (initialInput) {
                case 1:
                    System.out.println("  ______   ______   .__   __. .___________.    ___       ______ .___________.    _______.    __       __       _______.___________.\n" +
                            " /      | /  __  \\  |  \\ |  | |           |   /   \\     /      ||           |   /       |   |  |     |  |     /       |           |\n" +
                            "|  ,----'|  |  |  | |   \\|  | `---|  |----`  /  ^  \\   |  ,----'`---|  |----`  |   (----`   |  |     |  |    |   (----`---|  |----`\n" +
                            "|  |     |  |  |  | |  . `  |     |  |      /  /_\\  \\  |  |         |  |        \\   \\       |  |     |  |     \\   \\       |  |     \n" +
                            "|  `----.|  `--'  | |  |\\   |     |  |     /  _____  \\ |  `----.    |  |    .----)   |      |  `----.|  | .----)   |      |  |     \n" +
                            " \\______| \\______/  |__| \\__|     |__|    /__/     \\__\\ \\______|    |__|    |_______/       |_______||__| |_______/       |__|     \n" +
                            "                                                                                                                                   ");
                    System.out.printf("\n%-15s | %-12s |\n", "Name", "Phone number");
                    System.out.println("--------------------------------");
                    for (long i = 0; i < data.size(); i++) {
                        System.out.println(data.get((int) i));
                    }
                    break;

                case 2:
                    System.out.println("Enter Contact First Name: ");
                    String contactFirstNameInput = input.getString();


                    System.out.println("Enter Contact Last Name: ");
                    String contactLastNameInput = input.getString();


                    String formattedNames = contactFirstNameInput + " " + contactLastNameInput;

                    // Check if there's already a contact with the same name
                    boolean contactExists = false;
                    for (String line : Files.readAllLines(filePath)) {
                        if (line.startsWith(formattedNames)) {
                            contactExists = true;
                            break;
                        }
                    }

                    if (contactExists) {
                        System.out.println("There's already a contact named " + formattedNames + ". Do you want to add the new contact anyway? (Y/N)");
                        String answer = input.getString();
                        if (answer.equalsIgnoreCase("Y")) {
                            System.out.println("Contact not saved.");
                            break;
                        }
                    }

                    System.out.println("Enter Contact Phone Number");
                    String contactPhoneNumber = String.valueOf(input.getLong());

                    Contact newContact = new Contact(contactFirstNameInput, contactLastNameInput, contactPhoneNumber);
                    String firstName = newContact.getFirstName();
                    String lastName = newContact.getLastName();
                    String number = newContact.getNumber();
                    
                    String formattedName = firstName + " " + lastName;
                    String formattedPhoneNumber = null;

                    if(number.length() == 10) {
                        formattedPhoneNumber = number.substring(0, 3) + "-"  + number.substring(3, 6) + "-" + number.substring(6);
                    } else if(number.length() == 7) {
                        formattedPhoneNumber = number.substring(0, 3) + "-"  + number.substring(3);
                    }else{
                        System.out.println("Please enter a 7 to 10 digit phone number");
                    }


                    // Pad the name and phone number strings with spaces to align them under the headers
                    long namePadding = 15 - formattedName.length();
                    long phonePadding = 12 - formattedPhoneNumber.length();
                    formattedName += " ".repeat((int) Math.max(0, namePadding));
                    formattedPhoneNumber = " ".repeat((int) Math.max(0, phonePadding)) + formattedPhoneNumber;

                    // Format the contact details in a tabular format
                    String formattedLine = String.format("%s | %s |", formattedName, formattedPhoneNumber);
                    lines = Arrays.asList(formattedLine);

                    System.out.println("  ______   ______   .__   __. .___________.    ___       ______ .___________.        ___       _______   _______   _______  _______  \n" +
                            " /      | /  __  \\  |  \\ |  | |           |   /   \\     /      ||           |       /   \\     |       \\ |       \\ |   ____||       \\ \n" +
                            "|  ,----'|  |  |  | |   \\|  | `---|  |----`  /  ^  \\   |  ,----'`---|  |----`      /  ^  \\    |  .--.  ||  .--.  ||  |__   |  .--.  |\n" +
                            "|  |     |  |  |  | |  . `  |     |  |      /  /_\\  \\  |  |         |  |          /  /_\\  \\   |  |  |  ||  |  |  ||   __|  |  |  |  |\n" +
                            "|  `----.|  `--'  | |  |\\   |     |  |     /  _____  \\ |  `----.    |  |         /  _____  \\  |  '--'  ||  '--'  ||  |____ |  '--'  |\n" +
                            " \\______| \\______/  |__| \\__|     |__|    /__/     \\__\\ \\______|    |__|        /__/     \\__\\ |_______/ |_______/ |_______||_______/ \n" +
                            "                                                                                                                                     ");
                    System.out.println("You entered: " + formattedLine);

                    Files.write(filePath, lines, StandardOpenOption.APPEND);
                    break;

                case 3:
                    System.out.println("Enter the Contact Name: ");
                    String searchParameter = input.getString();
                    String correctname = null;

                    for (String name : data) {
                        if (name.toLowerCase().contains(searchParameter.toLowerCase())) {
                            correctname = name;
                        }
                        else {
                            correctname = "No Contact Found.";
                        }
                    }

                    System.out.println(correctname);
                    break;

                case 4:
                    System.out.println("Enter the Name of the Contact You want to DELETE: ");
                    String deleteParameter = input.getString();

                    for (String name : data) {
                        if (name.toLowerCase().contains(deleteParameter.toLowerCase())) {
                            newData.remove(name);
                            System.out.println("  ______   ______   .__   __. .___________.    ___       ______ .___________.    _______   _______  __       _______ .___________. _______  _______  \n" +
                                    " /      | /  __  \\  |  \\ |  | |           |   /   \\     /      ||           |   |       \\ |   ____||  |     |   ____||           ||   ____||       \\ \n" +
                                    "|  ,----'|  |  |  | |   \\|  | `---|  |----`  /  ^  \\   |  ,----'`---|  |----`   |  .--.  ||  |__   |  |     |  |__   `---|  |----`|  |__   |  .--.  |\n" +
                                    "|  |     |  |  |  | |  . `  |     |  |      /  /_\\  \\  |  |         |  |        |  |  |  ||   __|  |  |     |   __|      |  |     |   __|  |  |  |  |\n" +
                                    "|  `----.|  `--'  | |  |\\   |     |  |     /  _____  \\ |  `----.    |  |        |  '--'  ||  |____ |  `----.|  |____     |  |     |  |____ |  '--'  |\n" +
                                    " \\______| \\______/  |__| \\__|     |__|    /__/     \\__\\ \\______|    |__|        |_______/ |_______||_______||_______|    |__|     |_______||_______/ \n" +
                                    "                                                                                                                                                     ");
                            System.out.println("You deleted: " + name);
                        }
                    }

                    Files.write(filePath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
                    Files.write(filePath, newData, StandardOpenOption.APPEND);
                    break;

                case 5:
                    break;

            }
            confirm = input.yesNo();
        } while(confirm);
        System.out.println("     _______. _______  _______    ____    ____  ______    __    __     .__   __.  __________   ___ .___________.   .___________. __  .___  ___.  _______  __  \n" +
                "    /       ||   ____||   ____|   \\   \\  /   / /  __  \\  |  |  |  |    |  \\ |  | |   ____\\  \\ /  / |           |   |           ||  | |   \\/   | |   ____||  | \n" +
                "   |   (----`|  |__   |  |__       \\   \\/   / |  |  |  | |  |  |  |    |   \\|  | |  |__   \\  V  /  `---|  |----`   `---|  |----`|  | |  \\  /  | |  |__   |  | \n" +
                "    \\   \\    |   __|  |   __|       \\_    _/  |  |  |  | |  |  |  |    |  . `  | |   __|   >   <       |  |            |  |     |  | |  |\\/|  | |   __|  |  | \n" +
                ".----)   |   |  |____ |  |____        |  |    |  `--'  | |  `--'  |    |  |\\   | |  |____ /  .  \\      |  |            |  |     |  | |  |  |  | |  |____ |__| \n" +
                "|_______/    |_______||_______|       |__|     \\______/   \\______/     |__| \\__| |_______/__/ \\__\\     |__|            |__|     |__| |__|  |__| |_______|(__) \n" +
                "                                                                                                                                                              ");
    }

}
