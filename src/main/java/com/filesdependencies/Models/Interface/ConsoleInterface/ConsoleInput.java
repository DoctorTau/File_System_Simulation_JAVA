package com.filesdependencies.Models.Interface.ConsoleInterface;

import java.util.Scanner;

import com.filesdependencies.Models.Interface.IInput;

public class ConsoleInput implements IInput {
    private Scanner scanner;

    /**
     * Set the scanner to be used by the input.
     * 
     * @param scanner the scanner to be used by the input.
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.filesdependencies.Models.Interface.IInput#getCommand()
     */
    @Override
    public String getCommand() {
        // Read the command from the user.
        return scanner.next();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.filesdependencies.Models.Interface.IInput#getFilepath()
     */
    @Override
    public String getFilepath() {
        // Read the filepath from the user.
        return scanner.nextLine().trim();
    }

    /**
     * Reads the text until the user enters an empty line
     *
     * @return text
     */
    public String getText() {
        String text = "";
        String line = scanner.nextLine();
        while (!line.equals("")) {
            text += line + " ";
            line = scanner.nextLine();
            if ("".equals(line)) {
                break;
            }

            text += "\n";

        }
        return text;
    }
}
