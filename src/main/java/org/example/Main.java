package org.example;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(@NotNull String[] args) {
        String filesRegex = ".+\\.log";
        String fileContentsRegex = "^.*Warning.*$";
        var m = FileReader.read(Path.of(args[0]), filesRegex);
        var p = SearchFilesContents.analyze(m, fileContentsRegex);

        try {
            FileWriter myWriter = new FileWriter("logInfo.txt");
            for (var f : p.entrySet()) {
                myWriter.write(f.getKey() + '\n');
                myWriter.write(f.getValue() + '\n');
                myWriter.write('\n');
            }
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}