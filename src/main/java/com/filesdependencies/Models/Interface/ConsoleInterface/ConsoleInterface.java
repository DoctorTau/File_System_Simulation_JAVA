package com.filesdependencies.Models.Interface.ConsoleInterface;

import java.util.Scanner;

import com.filesdependencies.Models.ComputerObjects.FileSystemObject;
import com.filesdependencies.Models.ComputerObjects.Folder;
import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.Interface.FileSystemInterface;

public class ConsoleInterface extends FileSystemInterface {
    public ConsoleInterface(Folder root) {
        super(root, new ConsoleInput(), new ConsoleOutput());
        Scanner scanner = new Scanner(System.in);
        ((ConsoleInput) input).setScanner(scanner);
        ((ConsoleOutput) output).setScanner(scanner);
    }

    @Override
    public void handleCommands() {
        Boolean running = true;
        while (running) {
            try {
                output.print("Enter a command: ");
                String command = input.getCommand();
                switch (command) {
                    case "exit":
                        running = false;
                        break;
                    case "add":
                        String filepath = input.getFilepath();
                        root.addFile(filepath);
                        output.printSuccess("File added successfully");
                        output.print("Enter the text for the file: ");
                        String text = input.getText();
                        FileSystemObject file = root.getFileByFullName(filepath);
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
                    case "printf":
                        output.printFiles(root);
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
