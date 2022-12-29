package com.filesdependencies;

import com.filesdependencies.Models.ComputerObjects.Folder;
import com.filesdependencies.Models.Interface.FileSystemInterface;
import com.filesdependencies.Models.Interface.ConsoleInterface.ConsoleInterface;

/**
 * Main class of the application.
 *
 */
public class App {
    public static void main(String[] args) {
        Folder root = new Folder("root", null);
        FileSystemInterface fileSystemInterface = new ConsoleInterface(root);
        fileSystemInterface.handleCommands();
    }
}
