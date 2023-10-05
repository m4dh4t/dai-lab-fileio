// TODO change this to your own package instead of jehrensb ***
package ch.heig.dai.lab.fileio.JustinFerrara14;

import ch.heig.dai.lab.fileio.JustinFerrara14.FileExplorer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileExplorerTests {

    @Test
    @Disabled
    public void getNewFilesTest() throws IOException {
        // Create a temporary directory under the default temp directory using java.io.File

        File tempDir = new File(System.getProperty("java.io.tmpdir"), Long.toString(System.nanoTime()));
        tempDir.mkdir();
        tempDir.deleteOnExit();

        // Create a FileExplorer for that directory
        FileExplorer explorer = new FileExplorer(tempDir.toString());

        // Check that no files are found
        assertEquals(explorer.getNewFile(), null);
        
        // Create some files in the directory and check that they are found
        Set<File> expectedFiles = Set.of(
            new File(tempDir, "file1.txt"),
            new File(tempDir, "file2.txt"),
            new File(tempDir, "file3.txt")
        );
        for (File file : expectedFiles) { file.createNewFile(); }
        Set<File> actualFiles = Set.of(
            explorer.getNewFile(),
            explorer.getNewFile(),
            explorer.getNewFile()
        );
        assertEquals(expectedFiles, actualFiles);

        // Check that no files are found
        assertEquals(explorer.getNewFile(), null);

        // Create some files in the directory and check that they are found
        expectedFiles = Set.of(
            new File(tempDir, "file4.txt"),
            new File(tempDir, "file5.txt")
        );
        for (File file : expectedFiles) { file.createNewFile(); }
        actualFiles = Set.of(
            explorer.getNewFile(),
            explorer.getNewFile()
        );
        assertEquals(expectedFiles, actualFiles);
    }
}