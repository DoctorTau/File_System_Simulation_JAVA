package com.filesdependencies.Models.Interface.ConsoleInterface;

import java.util.Scanner;

import com.filesdependencies.Models.ComputerObjects.FileSystemObject;
import com.filesdependencies.Models.ComputerObjects.Folder;
import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.Interface.FileSystemInterface;

public class ConsoleInterface extends FileSystemInterface {

    /**
     * @param root set root folder of the file system.
     */
    public ConsoleInterface(Folder root) {
        super(root, new ConsoleInput(), new ConsoleOutput());
        Scanner scanner = new Scanner(System.in);
        ((ConsoleInput) input).setScanner(scanner);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.filesdependencies.Models.Interface.FileSystemInterface#handleCommands()
     */
    @Override
    public void handleCommands() {
        Boolean running = true;
        while (running) {
            try {
                // Print the prompt.
                output.print("Enter a command: ");
                String command = input.getCommand();
                switch (command) {
                    case "exit":
                        running = false;
                        break;
                    case "add":
                        // 1. Get the file path from user input
                        String filepath = input.getFilepath();
                        // 2. Add the file to the root directory
                        root.addFile(filepath);
                        // 3. Print a success message
                        output.printSuccess("File added successfully");
                        // 4. Print a prompt message to tell user to enter the text for the file
                        output.print("Enter the text for the file: ");
                        // 5. Get the text content from the user input
                        String text = input.getText();
                        // 6. Get the file from the root directory
                        FileSystemObject file = root.getFileByFullName(filepath);
                        // 7. If the file is not null, set the content
                        if (file != null) {
                            ((File) file).setContent(text);
                        }
                        break;
                    case "clear":
                        ((ConsoleOutput) output).clearConsole();
                        break;
                    case "print":
                        output.printTree(root);
                        break;
                    case "printFC":
                        output.printFileChains(root);
                        break;
                    case "printC":
                        output.printFiles(root);
                        break;
                    case "remove":
                        // 1. Get the file path from user input
                        String filepathToRemove = input.getFilepath();
                        // 2. Remove the file from the root directory
                        root.removeFile(root.getFileByFullName(filepathToRemove));
                        // 3. Print a success message
                        output.printSuccess("File removed successfully");
                        break;
                    default:
                        output.printError("Command not found");
                        break;
                }
            } catch (Exception e) {
                output.printError(e.getMessage());
            }
        }
    }

}
