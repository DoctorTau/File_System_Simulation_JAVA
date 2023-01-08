package com.filesdependencies.Models.ComputerObjects;

import java.util.ArrayList;

public class File extends FileSystemObject {

    /*
     * The content of the file.
     */
    private String content = "";
    /*
     * The files that are required by this file.
     */
    private ArrayList<File> children = new ArrayList<File>();

    /**
     * Constructor.
     * 
     * @param name   name of the file.
     * @param parent parent of the file.
     */
    public File(String name, FileSystemObject parent) {
        super(name, parent);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content.trim();
        this.content = content;
    }

    public void updateReferences() {
        findReferences();
    }

    /**
     * Gets the files that are required by this file.
     * 
     * @return the files that are required by this file.
     */
    public ArrayList<File> getDependencies() {
        findReferences();
        return children;
    }

    /**
     * Splits the text by spaces and new lines.
     * 
     * @param text to split.
     * @return the words.
     */
    private String[] getWords(String text) {
        return text.split(" |\\r?\\n");
    }

    /**
     * Finds the files in the content that are required by this file.
     */
    private void findReferences() {
        this.children.clear();
        // Go through the content word by word and find the word "require"
        try {
            // Get a list of words from the file content
            String[] words = getWords(content);
            // Loop through the words in the file
            for (int i = 0; i < words.length; i++) {
                // Check if the word is "require"
                if ("require".equals(words[i])) {
                    // If the word is "require", check if the next word is a string
                    if (words[i + 1].startsWith("'")) {
                        // If the next word is a string, get the string value
                        String word = words[i + 1];
                        // Loop through the words until we find the last word of the string
                        int j = i + 1;
                        while (!word.endsWith("'")) {
                            j++;
                            word += " " + words[j];
                        }
                        // Trim the quotes from the string
                        word = word.substring(1, word.length() - 1);
                        // Find the file
                        FileSystemObject file = getRootAsFolder().getFileByFullName(word);
                        // If the file exists and is not a directory, add it to the children list
                        if (file != null && file instanceof File) {
                            children.add((File) file);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing file " + getFullName());
        }
    }

    public File getPrime(ArrayList<File> files, ArrayList<File> visited) {
        if (visited.contains(this)) {
            throw new RuntimeException("There is a cycle in the file system. File: " + this.getFullName());
        }
        visited.add(this);
        for (File file : files) {
            if (file != this) {
                if (file.getDependencies().contains(this)) {
                    return file.getPrime(files, visited);
                }
            }
        }
        return this;
    }
}
