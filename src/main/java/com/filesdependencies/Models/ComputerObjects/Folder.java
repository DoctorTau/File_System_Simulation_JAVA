package com.filesdependencies.Models.ComputerObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.filesdependencies.Models.Pair;

public class Folder extends FileSystemObject {
    private ArrayList<FileSystemObject> files = new ArrayList<FileSystemObject>();

    /**
     * Constructor for the root folder.
     * 
     * @param name   name of the folder.
     * @param parent parent of the folder.
     */
    public Folder(String name, FileSystemObject parent) {
        super(name, parent);
    }

    /**
     * @return the files that are in the folder.
     */
    public ArrayList<FileSystemObject> getFiles() {
        return files;
    }

    /**
     * Adds a file to the folder.
     * 
     * @param file to be added to the folder.
     */
    public void addFile(FileSystemObject file) {
        files.add(file);
    }

    /**
     * Removes a file from the folder.
     * 
     * @param file to be removed from the folder.
     */
    public void removeFile(FileSystemObject file) {
        files.remove(file);
    }

    /**
     * Gets the root folder of the file system.
     * 
     * @param name name of the file.
     * @return file with the name, null if not found.
     */
    public FileSystemObject getFile(String name) {
        for (FileSystemObject file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    /**
     * @param fullName
     * @return
     */
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

    /**
     * Adds a file by the full filepath
     * Creates folders if they don't exist.
     * 
     * @param filepath full path of the file.
     */
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

    public LinkedList<ArrayList<File>> getFileChains() {
        LinkedList<ArrayList<File>> result = new LinkedList<ArrayList<File>>();
        ArrayList<File> allFiles = getAllFiles(this), usedFile = new ArrayList<>();
        for (File file : allFiles) {
            if (!usedFile.contains(file)) {
                ArrayList<File> chain = new ArrayList<>();
                addFileToChain(file, chain);
                usedFile.addAll(chain);
                result.add(chain);
            }
        }

        return result;
    }

    private void addFileToChain(File file, ArrayList<File> chain) {
        if (chain.contains(file)) {
            throw new RuntimeException("There is a cycle in the file system. File: " + file.getFullName());
        }
        for (File dependent : file.getDependencies()) {
            addFileToChain(dependent, chain);
        }
        chain.add(file);
    }
}
