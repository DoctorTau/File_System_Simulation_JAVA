package com.filesdependencies;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.filesdependencies.Models.ComputerObjects.File;
import com.filesdependencies.Models.ComputerObjects.Folder;

public class FolderTest {

    @Test
    public void folderCreatingTest() {
        Folder root = new Folder("root", null);
        Folder folder1 = new Folder("folder1", root);
        Folder folder2 = new Folder("folder2", root);
        Folder folder3 = new Folder("folder3", folder1);
        Folder folder4 = new Folder("folder4", folder1);
        Folder folder5 = new Folder("folder5", folder2);
        Folder folder6 = new Folder("folder6", folder2);
        Folder folder7 = new Folder("folder7", folder3);
        Folder folder8 = new Folder("folder8", folder3);
        Folder folder9 = new Folder("folder9", folder4);
        Folder folder10 = new Folder("folder10", folder4);
        Folder folder11 = new Folder("folder11", folder5);
        Folder folder12 = new Folder("folder12", folder5);
        new Folder("folder13", folder6);
        new Folder("folder14", folder6);
        new Folder("folder15", folder7);
        new Folder("folder16", folder7);
        new Folder("folder17", folder8);

        // check if folders are created correctly
        assertEquals(2, root.getFiles().size());
        assertEquals(2, folder1.getFiles().size());
        assertEquals(2, folder2.getFiles().size());
        assertEquals(2, folder3.getFiles().size());
        assertEquals(2, folder4.getFiles().size());
        assertEquals(2, folder5.getFiles().size());
        assertEquals(2, folder6.getFiles().size());
        assertEquals(2, folder7.getFiles().size());
        assertEquals(1, folder8.getFiles().size());
        assertEquals(0, folder9.getFiles().size());
        assertEquals(0, folder10.getFiles().size());
        assertEquals(0, folder11.getFiles().size());
        assertEquals(0, folder12.getFiles().size());
    }

    @Test
    public void foldersAndFilesCreating() {
        Folder root = new Folder("root", null);
        Folder folder1 = new Folder("folder1", root);
        Folder folder2 = new Folder("folder2", root);
        Folder folder3 = new Folder("folder3", folder1);
        Folder folder4 = new Folder("folder4", folder1);
        Folder folder5 = new Folder("folder5", folder2);
        Folder folder6 = new Folder("folder6", folder2);
        Folder folder7 = new Folder("folder7", folder3);
        new File("file1", folder4);
        new File("file2", folder4);
        new File("file3", folder5);
        new File("file4", folder5);
        new File("file5", folder6);
        new File("file6", folder6);
        new File("file7", folder7);

        // check if folders and files are created correctly
        assertEquals(2, root.getFiles().size());
        assertEquals(2, folder1.getFiles().size());
        assertEquals(2, folder2.getFiles().size());
        assertEquals(1, folder3.getFiles().size());
        assertEquals(2, folder4.getFiles().size());
        assertEquals(2, folder5.getFiles().size());
        assertEquals(2, folder6.getFiles().size());
        assertEquals(1, folder7.getFiles().size());
    }
}
