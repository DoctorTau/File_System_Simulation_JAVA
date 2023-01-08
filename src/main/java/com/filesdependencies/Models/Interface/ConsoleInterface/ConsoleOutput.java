package com.filesdependencies.Models.Interface.ConsoleInterface;

import java.util.ArrayList;
import java.util.LinkedList;

import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.ComputerObjects.FileSystemObject;
import com.filesdependencies.Models.ComputerObjects.Folder;
import com.filesdependencies.Models.Interface.IOutput;

public class ConsoleOutput implements IOutput {

    /*
     * (non-Javadoc)
     * 
     * @see com.filesdependencies.Models.Interface.IOutput#print(java.lang.String)
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.filesdependencies.Models.Interface.IOutput#printError(java.lang.String)
     */
    @Override
    public void printError(String message) {
        // Set the color to red and print the message, then reset the color
        System.out.print("\033[0;31m");
        System.out.println(message);
        System.out.print("\033[0m");
        System.out.flush();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.filesdependencies.Models.Interface.IOutput#printSuccess(java.lang.String)
     */
    @Override
    public void printSuccess(String message) {
        // Set the color to green and print the message, then reset the color
        System.out.print("\033[0;32m");
        System.out.println(message);
        System.out.print("\033[0m");
        System.out.flush();

    }

    /**
     * Clears the console.
     */
    public void clearConsole() {
        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.filesdependencies.Models.Interface.IOutput#printTree(com.
     * filesdependencies.Models.ComputerObjects.Folder)
     */
    @Override
    public void printTree(Folder root) {
        // Print the tree
        System.out.println(FileSystemObject.getTree(root, ""));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.filesdependencies.Models.Interface.IOutput#printFiles(com.
     * filesdependencies.Models.ComputerObjects.Folder)
     */
    @Override
    public void printFiles(Folder folder) {
        System.out.println(FileSystemObject.getFilesContent(folder));
    }

    @Override
    public void printFileChains(Folder root) {
        LinkedList<ArrayList<File>> chains = root.getFileChains();
        for (ArrayList<File> chain : chains) {
            for (int i = chain.size() - 1; i >= 0; i--) {
                System.out.println(chain.get(i).getFullName());
            }
            System.out.println("-----------------");
        }
    }
}
