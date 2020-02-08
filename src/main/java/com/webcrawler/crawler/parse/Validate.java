package com.webcrawler.crawler.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    private String regx ="(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\"+
            ".[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?"+
            ":\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";

    Boolean validateUrl(String url){
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(url);
        boolean b = m.matches();
        return b;
    }


}
