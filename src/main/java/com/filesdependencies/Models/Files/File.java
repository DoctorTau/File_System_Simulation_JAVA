package com.filesdependencies.Models.Files;

public abstract class File {
    private String name;
    private File parent = null;

    public File(String name, File parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public File getParent() {
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
