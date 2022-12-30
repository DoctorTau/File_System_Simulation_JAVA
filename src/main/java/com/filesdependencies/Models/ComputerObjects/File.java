package com.filesdependencies.Models.ComputerObjects;

import java.util.ArrayList;

public class File extends FileSystemObject {

    private String content = "";
    private ArrayList<File> children = new ArrayList<File>();

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

    public ArrayList<File> getDependencies() {
        findReferences();
        return children;
    }

    private String[] getWords(String text) {
        // Split the text by spaces and new lines
        return text.split(" |\\r?\\n");
    }

    private void findReferences() {
        // Go through the content word by word and find the word "require"
        try {
            String[] words = getWords(content);
            for (int i = 0; i < words.length; i++) {
                if ("require".equals(words[i])) {
                    if (words[i + 1].startsWith("'")) {
                        String word = words[i + 1];
                        int j = i + 1;
                        while (!word.endsWith("'")) {
                            j++;
                            word += " " + words[j];
                        }
                        // trim the quotes
                        word = word.substring(1, word.length() - 1);
                        // find the file
                        FileSystemObject file = getRootAsFolder().getFileByFullName(word);
                        if (file != null && file instanceof File) {
                            children.add((File) file);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing file " + getName());
        }
    }
}
