package com.filesdependencies.Models.ComputerObjects;

public abstract class FileSystemObject {
    protected String name;
    protected FileSystemObject parent = null;

    public FileSystemObject(String name, FileSystemObject parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public FileSystemObject getParent() {
        return parent;
    }

    public String getFullName() {
        if (parent == null) {
            return name;
        } else {
            return parent.getFullName() + "\\" + name;
        }
    }
}
