package com.filesdependencies.Models.ComputerObjects;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class FileSystemObject {
    protected String name;
    protected FileSystemObject parent = null;

    public FileSystemObject(String name, FileSystemObject parent) {
        this.name = name;
        this.parent = parent;
        if (parent != null) {
            if (!(parent instanceof Folder)) {
                throw new RuntimeException("Parent is not a folder");
            }
            ((Folder) parent).addFile(this);
        }
    }

    public String getName() {
        return name;
    }

    public FileSystemObject getParent() {
        return parent;
    }

    public void setParent(FileSystemObject parent) {
        this.parent = parent;
    }

    public String getFullName() {
        if (parent == null) {
            return name;
        } else {
            return parent.getFullName() + "\\" + name;
        }
    }

    protected Boolean isFileExists(String path) {
        ArrayList<String> pathParts = new ArrayList<String>(Arrays.asList(path.split("\\\\")));
        Folder root = getRootAsFolder();
        for (String string : pathParts) {
            FileSystemObject file = root.getFile(string);
            if (file == null) {
                return false;
            }
            if (file instanceof Folder) {
                root = (Folder) file;
            } else {
                return true;
            }
        }
        return false;
    }

    private FileSystemObject findRoot() {
        if (parent == null) {
            return this;
        } else {
            return parent.findRoot();
        }
    }

    protected Folder getRootAsFolder() {
        FileSystemObject root = findRoot();
        if (!(root instanceof Folder)) {
            throw new RuntimeException("Root is not a folder");
        }
        return (Folder) root;
    }
}
