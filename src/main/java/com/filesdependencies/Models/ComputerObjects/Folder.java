package com.filesdependencies.Models.ComputerObjects;

import java.util.ArrayList;
import java.util.Arrays;

public class Folder extends FileSystemObject {
    private ArrayList<FileSystemObject> files = new ArrayList<FileSystemObject>();

    public Folder(String name, FileSystemObject parent) {
        super(name, parent);
    }

    public ArrayList<FileSystemObject> getFiles() {
        return files;
    }

    public void addFile(FileSystemObject file) {
        files.add(file);
    }

    public void removeFile(FileSystemObject file) {
        files.remove(file);
    }

    public FileSystemObject getFile(String name) {
        for (FileSystemObject file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    public FileSystemObject getFileByFullName(String fullName) {
        ArrayList<String> pathParts = new ArrayList<String>(Arrays.asList(fullName.split("\\\\")));
        Folder root = getRootAsFolder();
        for (String string : pathParts) {
            FileSystemObject file = root.getFile(string);
            if (file == null) {
                return null;
            }
            if (file instanceof Folder) {
                root = (Folder) file;
            }
        }
        return root;
    }
}
