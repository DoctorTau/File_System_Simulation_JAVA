package com.filesdependencies.Models.Interface.ConsoleInterface;

import java.util.Scanner;

import com.filesdependencies.Models.Interface.IInput;

public class ConsoleInput implements IInput {
    private Scanner scanner;

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getCommand() {
        return scanner.next();
    }

    @Override
    public String getFilepath() {
        return scanner.nextLine().trim();
    }

    @Override
    public String getText() {
        // Read the text until the user enters an empty line
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
