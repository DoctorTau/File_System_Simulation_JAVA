package com.filesdependencies;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.ComputerObjects.FileSystemObject;
import com.filesdependencies.Models.ComputerObjects.Folder;

public class OutputTest {
    @Test
    public void testGetTreeOfFolder() {
        Folder root = new Folder("root", null);
        Folder folder1 = new Folder("folder1", root);
        Folder folder2 = new Folder("folder2", root);
        Folder folder3 = new Folder("folder3", folder1);
        new File("file1", folder1);
        new File("file2", folder2);
        new File("file3", folder3);
        new File("file4", folder3);
        new File("file5", folder3);
        String expected = "root\n" +
                "  folder1\n" +
                "    folder3\n" +
                "      file3\n" +
                "      file4\n" +
                "      file5\n" +
                "    file1\n" +
                "  folder2\n" +
                "    file2\n";

        String actual = FileSystemObject.getTree(root, "");
        assertEquals(expected, actual);
    }
}
