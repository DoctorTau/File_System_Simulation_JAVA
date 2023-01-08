package com.filesdependencies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.ComputerObjects.FileSystemObject;
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
        file.setContent("Hello word!\nrequire 'folder1\\file2' require 'file3'\n");

        ArrayList<File> actual = file.getDependencies();
        assertEquals(expected, actual);
    }

    @Test
    public void testContentWithOneDependency() {
        Folder root = new Folder("root", null);
        File file = new File("file1", root);
        File file2 = new File("file2", root);
        file.setContent("Hello word! require 'file2'");
        file2.setContent("Goodbye word!");

        String expected = "Goodbye word!\nHello word! require 'file2'\n";
        String actual = FileSystemObject.getFilesContent(root);

        assertEquals(expected, actual);
    }

    @Test
    public void testContentWithTwoDependency() {
        Folder root = new Folder("root", null);
        Folder folder1 = new Folder("folder1", root);
        File file = new File("file1", root);
        File file2 = new File("file2", root);
        File file3 = new File("file3", folder1);
        file.setContent("Hello word! require 'file2'");
        file2.setContent("require 'folder1\\file3' Goodbye word!");
        file3.setContent("kekis");

        String expected = "kekis\nrequire 'folder1\\file3' Goodbye word!\nHello word! require 'file2'\n";
        String actual = FileSystemObject.getFilesContent(root);

        assertEquals(expected, actual);
    }

    @Test
    public void testCycleTwoFilesCatch() {
        Folder root = new Folder("root", null);
        File file = new File("file1", root);
        File file2 = new File("file2", root);
        file.setContent("Hello word! require 'file2'\n");
        file2.setContent("require 'file1' Goodbye word!\n");
        try {
            FileSystemObject.getFilesContent(root);
            fail("Cycle not detected");
        } catch (RuntimeException expectedException) {
            assertTrue(expectedException.getMessage().contains("There is a cycle in the file system."));
        }
    }

    @Test
    public void testCycleFourFilesCatch() {
        Folder root = new Folder("root", null);
        File file = new File("file1", root);
        File file2 = new File("file2", root);
        File file3 = new File("file3", root);
        File file4 = new File("file4", root);
        file.setContent("Hello word! require 'file2'\n");
        file2.setContent("require 'file3' Goodbye word!\n");
        file3.setContent("require 'file4' Goodbye word!\n");
        file4.setContent("require 'file1' Goodbye word!\n");
        try {
            FileSystemObject.getFilesContent(root);
            fail("My method didn't throw when I expected it to");
        } catch (RuntimeException expectedException) {
            assertTrue(expectedException.getMessage().contains("There is a cycle in the file system."));
        }
    }
}
