package com.filesdependencies.Models.ComputerObjects;

import java.util.ArrayList;

public class File extends FileSystemObject {

    private String content = "";
    private ArrayList<FileSystemObject> children = new ArrayList<FileSystemObject>();

    public File(String name, FileSystemObject parent) {
        super(name, parent);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        findReferences();
    }

    public ArrayList<FileSystemObject> getDependencies() {
        return children;
    }

    private void findReferences() {
        // TODO: find references
    }
}
