package org.example;

import me.tongfei.progressbar.ProgressBar;

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
            ProgressBar pb = new ProgressBar("Analyzing " + el.getKey(), el.getValue().length());

            while (matcher.find()) {
                var tmp = matcher.group();
                pb.stepTo(matcher.end());
                s.append(tmp).append('\n');
                // uncomment to see the progress bar in action
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            pb.stepTo(pb.getMax());
            pb.close();

            filtered.put(el.getKey(), s.toString());
        }
        return filtered;
    }
}
