package org.example.editor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KeywordStyles {

    public static final Map<String, String> KEYWORD_STYLES = Collections.unmodifiableMap(new HashMap<String, String>() {{
        put("var", "keyword-var");
        put("map", "keyword-func");
        put("reduce", "keyword-func");
        put("print", "keyword-command");
        put("out", "keyword-command");
    }});
}
