package com.filesdependencies.Models.ComputerObjects;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class FileSystemObject {
    /**
     * Get root folder of file system object.
     * 
     * @param root   folder of the file system.
     * @param prefix prefix of the tree should be spaces.
     * @return String of the tree.
     */
    public static String getTree(FileSystemObject root, String prefix) {
        String result = prefix + root.getName() + "\n";
        if (root instanceof Folder) {
            for (FileSystemObject file : ((Folder) root).getFiles()) {
                result += getTree(file, prefix + "  ");
            }
        }
        return result;
    }

    /**
     * Gets the content of all files in the folder.
     * 
     * @param fileSystemObjects files to get the content.
     * @return String of the content.
     */
    public static String getFilesContent(FileSystemObject fileSystemObject) {
        String result = "";
        ArrayList<File> addedFiles = new ArrayList<File>();
        if (fileSystemObject instanceof File) {
            File file = (File) fileSystemObject;
            for (FileSystemObject dependency : file.getDependencies()) {
                if (!addedFiles.contains(dependency)) {
                    result += '\n' + getFilesContent(dependency);
                    addedFiles.add((File) dependency);
                }
            }
            result += file.getContent();
            return result;
        }
        if (fileSystemObject instanceof Folder) {
            for (FileSystemObject file : ((Folder) fileSystemObject).getFiles()) {
                if (!"".equals(result))
                    result += '\n';
                result += getFilesContent(file);
            }
        }
        return result;
    }

    public static ArrayList<File> getAllFiles(Folder folder) {
        ArrayList<File> files = new ArrayList<File>();
        for (FileSystemObject fileSystemObject : folder.getFiles()) {
            if (fileSystemObject instanceof File) {
                files.add((File) fileSystemObject);
            } else {
                getAllFiles(folder, files);
            }
        }
        return files;
    }

    private static ArrayList<File> getAllFiles(Folder folder, ArrayList<File> files) {
        for (FileSystemObject file : folder.getFiles()) {
            if (file instanceof File) {
                files.add((File) file);
            } else {
                getAllFiles((Folder) file, files);
            }
        }
        return files;
    }

    protected String name;

    protected FileSystemObject parent = null;

    /**
     * Constructor of FileSystemObject.
     * 
     * @param name   name of the file system object.
     * @param parent parent of the file system object.
     */
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

    /**
     * @return full name with the path of the file system object.
     */
    public String getFullName() {
        if (parent == null) {
            return name;
        } else {
            return parent.getFullName() + "\\" + name;
        }
    }

    /**
     * Get root folder of file system object.
     * 
     * @return root folder of file system object.
     */
    protected Folder getRootAsFolder() {
        FileSystemObject root = findRoot();
        if (!(root instanceof Folder)) {
            throw new RuntimeException("Root is not a folder");
        }
        return (Folder) root;
    }

    /**
     * Find root of file system object.
     * 
     * @return root of file system object.
     */
    private FileSystemObject findRoot() {
        if (parent == null) {
            return this;
        } else {
            return parent.findRoot();
        }
    }

}
