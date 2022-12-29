package com.filesdependencies.Models.Interface.ConsoleInterface;

import java.util.Scanner;

import com.filesdependencies.Models.ComputerObjects.FileSystemObject;
import com.filesdependencies.Models.ComputerObjects.Folder;
import com.filesdependencies.Models.Interface.IOutput;

public class ConsoleOutput implements IOutput {
    protected Scanner scanner;

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String message) {
        // Set the color to red and print the message, then reset the color
        System.out.print("\033[0;31m");
        System.out.println(message);
        System.out.print("\033[0m");
        System.out.flush();
    }

    @Override
    public void printSuccess(String message) {
        // Set the color to green and print the message, then reset the color
        System.out.print("\033[0;32m");
        System.out.println(message);
        System.out.print("\033[0m");
        System.out.flush();

    }

    public void clearConsole() {
        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void printTree(Folder root) {
        // Print the tree
        FileSystemObject.printTree(root, "");
    }
}
