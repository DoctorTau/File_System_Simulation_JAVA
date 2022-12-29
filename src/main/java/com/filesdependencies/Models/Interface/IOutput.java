package com.filesdependencies.Models.Interface;

import com.filesdependencies.Models.ComputerObjects.Folder;

public interface IOutput {
    void print(String message);

    void printError(String message);

    void printSuccess(String message);

    void printTree(Folder root);

    void printFiles(Folder folder);
}
