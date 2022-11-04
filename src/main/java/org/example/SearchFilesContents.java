package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SearchFilesContents {

    public static Map<String, String> analyze(Map<String, String> m, String regex) {
        var filtered = new HashMap<String, String>();
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        for (var el: m.entrySet()) {
            var matcher = p.matcher(el.getValue());
            StringBuilder s = new StringBuilder();
            while (matcher.find())
                s.append(matcher.group()).append('\n');
            filtered.put(el.getKey(), s.toString());
        }
        return filtered;
    }
}
