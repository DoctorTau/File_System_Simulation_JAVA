package com.filesdependencies.Models.Interface;

import com.filesdependencies.Models.ComputerObjects.Folder;

public interface IOutput {
    /**
     * @param message to be printed.
     */
    void print(String message);

    /**
     * Prints an error message in red.
     * 
     * @param message to be printed.
     */
    void printError(String message);

    /**
     * Prints a success message in green.
     * 
     * @param message to be printed.
     */
    void printSuccess(String message);

    /**
     * Prints the tree of the file system.
     * 
     * @param root folder of the file system.
     */
    void printTree(Folder root);

    /**
     * @param folder to print the files content.
     */
    void printFiles(Folder folder);

    /**
     * Prints the file chains.
     * 
     * @param root folder of the file system.
     */
    void printFileChains(Folder root);
}
