package org.example;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class Main {
    public static void main(@NotNull String[] args) {
        var m = FileReader.read(Path.of(args[0]), args[1]);

        for ( var f: m.entrySet()) {
            System.out.println(f.getKey());
            System.out.println(f.getValue());
            System.out.println();
        }
    }
}