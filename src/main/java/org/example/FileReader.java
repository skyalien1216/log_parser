package org.example;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class FileReader {

    public static Map<String,String> read(Path path, String regex)
    {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final File f = new File(path.toUri());
        if(f.isFile())
            if(p.matcher(f.getName()).matches())
                return getFileContent(f);
            else return new HashMap<>();
        else
            return getAllFilesFromDir(f, p);
    }

    private static Map<String,String> getAllFilesFromDir(@NotNull File f, Pattern p) {
        Map<String, String> dict = new HashMap<String, String>();
        var list = f.listFiles();
        if (list == null) return dict;
        var ff = Arrays.stream(list).filter(a -> a.isFile() && p.matcher(a.getName()).matches());
        var files = new ArrayList<File>(ff.toList());
        if (files.size() == 0) return dict;
        for (File d : files) {
            Map.Entry<String, String> ent = getFileMapEntry(d);
            dict.put(ent.getKey(), ent.getValue());
        }
        return dict;
    }

    private static Map<String,String> getFileContent(@NotNull File f) {
        Map<String, String> dict = new HashMap<String, String>();
        Map.Entry<String,String> ent = getFileMapEntry(f);
        dict.put(ent.getKey(), ent.getValue());
        return dict;
    }

    private static Map.Entry<String,String> getFileMapEntry(File f){
        try {
            String data = FileUtils.readFileToString(f, "UTF-8");
            return  new AbstractMap.SimpleEntry<String, String>(f.getName(), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
