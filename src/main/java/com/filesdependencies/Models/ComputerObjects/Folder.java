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
        FileSystemObject result = null;
        for (String string : pathParts) {
            FileSystemObject file = root.getFile(string);
            if (file == null) {
                return null;
            }
            if (file instanceof Folder) {
                root = (Folder) file;
            } else if (file instanceof File) {
                result = (File) file;
            }
        }
        return result;
    }

    public void addFile(String filepath) {
        ArrayList<String> pathParts = new ArrayList<String>(Arrays.asList(filepath.split("\\\\")));
        Folder root = getRootAsFolder();
        for (int i = 0; i < pathParts.size(); i++) {
            String string = pathParts.get(i);
            FileSystemObject file = root.getFile(string);
            if (file == null) {
                if (i == pathParts.size() - 1) {
                    new File(string, root);
                } else {
                    root = new Folder(string, root);
                }
            }
            if (file instanceof Folder) {
                root = (Folder) file;
            }
        }
    }
}
