package com.example.javasamplesspringboot.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtil {

    /**
     * スタックトレースをStringに変換する
     */
    public static String convertStackTraceToString (Throwable e) {
        StringWriter s = new StringWriter();
        PrintWriter p = new PrintWriter(s);
        e.printStackTrace(p);
        p.flush();
        return s.toString();
    }
}
