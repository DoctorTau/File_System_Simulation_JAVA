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
        return scanner.nextLine();
    }
}
