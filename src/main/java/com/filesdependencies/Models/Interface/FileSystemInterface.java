package com.filesdependencies.Models.Interface;

import com.filesdependencies.Models.ComputerObjects.Folder;

public abstract class FileSystemInterface {
    protected Folder root;
    protected IInput input;
    protected IOutput output;

    public FileSystemInterface(Folder root, IInput input, IOutput output) {
        this.root = root;
        this.input = input;
        this.output = output;
    }

    public abstract void handleCommands();
}