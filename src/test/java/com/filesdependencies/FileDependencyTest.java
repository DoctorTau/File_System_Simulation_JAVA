package com.filesdependencies;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.ComputerObjects.Folder;

public class FileDependencyTest {
    @Test
    public void testFileDependency() {
        Folder root = new Folder("root", null);
        File file = new File("file1", root);
        File file2 = new File("file2", root);
        File file3 = new File("file3", root);
        ArrayList<File> expected = new ArrayList<File>();
        expected.add(file2);
        expected.add(file3);
        file.setContent("Hello word! require 'file2' require 'file3'\n");

        ArrayList<File> actual = file.getDependencies();
        assertEquals(expected, actual);
    }

    @Test
    public void testFileDependenciesWithInnerFiles() {
        Folder root = new Folder("root", null);
        Folder folder1 = new Folder("folder1", root);
        File file = new File("file1", root);
        File file2 = new File("file2", folder1);
        File file3 = new File("file3", root);
        ArrayList<File> expected = new ArrayList<File>();
        expected.add(file2);
        expected.add(file3);
        file.setContent("Hello word! require 'folder1\\file2' require 'file3'");

        ArrayList<File> actual = file.getDependencies();
        assertEquals(expected, actual);
    }
}
