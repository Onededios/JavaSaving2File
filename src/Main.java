/**

 This program allows the user to create, modify, list, and delete files in the "files" directory.
 The user is prompted to choose an action: add text to a file, list files, or delete a file.
 If the user chooses to add text to a file, they will be prompted to enter the name of the file, the text to add,
 and whether to overwrite or append the text.
 If the user chooses to list files, the program will display the names of the files in the "files" directory.
 The user may then choose to view the contents of a file.
 If the user chooses to delete a file, they will be prompted to enter the name of the file to delete.
 The program will then delete the file if it exists.
 */
package src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Main method of the program.
 * @throws IOException if an I/O error occurs when creating or writing to a file
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Scanner to read user input
        Scanner usrInp = new Scanner(System.in);

        // List files in the "files" directory
        File[] files = new File("files").listFiles();

        // Prompt user to choose an action
        System.out.print("What do you want to do?\n     a. Add text to a file\n     b. List files\n     c. Delete file\nYour answer: ");
        char option = usrInp.next().charAt(0);
        usrInp.nextLine();

        // Switch statement to handle user's choice
        switch (option) {

            // Add text to a file
            case 'a':
                System.out.print("Tell the name of the file to create: ");
                String fileName = "files/"+usrInp.nextLine();
                File file = new File(fileName);

                // Check if the file name is valid
                if (fileName.equals("files/none")) {
                    System.out.println("The name of the file cannot be 'none'.");
                }
                else {
                    // Create the file if it does not exist
                    if (file.createNewFile()) {
                        System.out.println("The file " + file.getName() + " has been created.");
                    } else {
                        System.out.println("Error: File already exists.");
                    }

                    // Prompt user for text to add
                    System.out.println("Tell me the text that do you want to add to the file:");
                    String text = usrInp.nextLine();

                    // Prompt user to overwrite or append text
                    System.out.println("And what do you want to do with the text?\n    1. Write to the file\n    2. Append to the file");
                    System.out.print("Your selection: ");
                    int choice = usrInp.nextInt();
                    switch (choice) {

                        // Overwrite text
                        case 1:
                            FileWriter myWriter = new FileWriter(file);
                            myWriter.write(text);
                            myWriter.close();
                            break;

                        // Append text
                        case 2:
                            FileWriter myAppend = new FileWriter(file, true);
                            myAppend.write("\n"+text);
                            myAppend.close();
                            break;
                    }
                    System.out.println("Done! Bye.");
                }
                break;

            // List files in the "files" directory
            case 'b':

                // Display the names of the files in the directory
                System.out.println("Those are the names of the files inside the directory:");
                for (File value : files) {System.out.print(value.getName() + " ");}

                // Ask the user if they want to see the content of a file
                System.out.println("\nDo you want to see the content of a file? y/n");
                System.out.print("Your selection: ");
                char choice2 = usrInp.next().charAt(0);
                usrInp.nextLine();
                if (choice2 == 'y') {// Ask the user for the name of the file they want to see
                    System.out.print("Tell me the name of the file that do you want to see: ");
                    String name = "files/" + usrInp.nextLine();

                    // Create a new File object with the specified name
                    File myObj = new File(name);

                    // Create a Scanner object to read the file
                    Scanner fileReader = new Scanner(myObj);

                    // Print out the contents of the file
                    while (fileReader.hasNextLine()) {
                        System.out.println(fileReader.nextLine());
                    }

                    // Close the Scanner object
                    fileReader.close();
                } else {// If the user doesn't want to see the contents of a file, print "Ok."
                    System.out.println("Ok.");
                }
                break;
            case 'c':

                // Display the names of the files in the directory
                System.out.println("Those are the names of the files inside the directory:");
                for (File value : files) {System.out.print(value.getName() + " ");}

                // Ask the user which file they want to delete
                System.out.println("\nWhich one do you want to delete? file_name/none");
                System.out.print("Your answer: ");
                String name = "files/"+usrInp.nextLine();

                // Create a new File object with the specified name
                File myObj = new File(name);
                if (name.equals("files/none")) {

                    // If the user doesn't want to delete any files, print "Ok."
                    System.out.println("Ok.");
                }
                else {

                    // Attempt to delete the file
                    if (myObj.delete()) {System.out.println("File "+myObj.getName()+" deleted.");}
                    else {

                        // If the file doesn't exist, print an error message
                        System.out.println("Error: File does not exist.");}
                }
                break;
            default:

                // If the user enters an invalid choice, print "Bye."
                System.out.println("Bye.");
                break;
        }
    }
}
