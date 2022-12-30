package com.filesdependencies.Models.Interface;

import com.filesdependencies.Models.ComputerObjects.Folder;

/*
 * Abstract class for the file system interface.
 * 
 */
public abstract class FileSystemInterface {
    /*
     * Root folder of the file system.
     */
    protected Folder root;
    /*
     * Input interface.
     */
    protected IInput input;
    /*
     * Output interface.
     */
    protected IOutput output;

    /**
     * Constructor.
     * 
     * @param root   set root folder of the file system.
     * @param input  set input interface.
     * @param output set output interface.
     */
    public FileSystemInterface(Folder root, IInput input, IOutput output) {
        this.root = root;
        this.input = input;
        this.output = output;
    }

    /**
     * Handle commands provided by the user.
     */
    public abstract void handleCommands();
}