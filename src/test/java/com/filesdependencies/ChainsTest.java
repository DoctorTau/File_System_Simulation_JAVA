package com.filesdependencies;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.ComputerObjects.Folder;

public class ChainsTest {
    @Test
    public void chainTest1() {
        Folder root = new Folder("root", null);
        File file1 = new File("file1", root);
        File file2 = new File("file2", root);
        File file3 = new File("file3", root);
        File file4 = new File("file4", root);
        File file5 = new File("file5", root);

        file1.setContent("require 'file2' require 'file3'\n");
        file2.setContent("require 'file4'\n");

        LinkedList<ArrayList<File>> expected = new LinkedList<ArrayList<File>>();
        expected.add(new ArrayList<File>());
        expected.getLast().add(file4);
        expected.getLast().add(file2);
        expected.getLast().add(file3);
        expected.getLast().add(file1);
        expected.add(new ArrayList<File>());
        expected.getLast().add(file5);

        LinkedList<ArrayList<File>> actual = root.getFileChains();
        assertEquals(expected, actual);
    }
}
