package org.example;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class Main {
    public static void main(@NotNull String[] args) {
        String filesRegex = ".+\\.log";
        String fileContentsRegex = "^.*Warning.*$";
        var m = FileReader.read(Path.of(args[0]), filesRegex);
        var p = SearchFilesContents.analyze(m, fileContentsRegex);

        for ( var f: p.entrySet()) {
            System.out.println(f.getKey());
            System.out.println(f.getValue());
            System.out.println();
        }
    }
}