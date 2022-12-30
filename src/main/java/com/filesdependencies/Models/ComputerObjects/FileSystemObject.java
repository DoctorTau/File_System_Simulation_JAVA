package com.filesdependencies.Models.ComputerObjects;

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
        if (fileSystemObject instanceof File) {
            File file = (File) fileSystemObject;
            for (FileSystemObject dependency : file.getDependencies()) {
                result += '\n' + getFilesContent(dependency);
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
