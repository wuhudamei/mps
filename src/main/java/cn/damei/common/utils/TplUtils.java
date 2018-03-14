package cn.damei.common.utils;

import cn.damei.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class TplUtils {

    public static List<String> tplTrim(List<String> list, String prefix, String include, String... excludes) {
        List<String> result = new ArrayList<String>();
        if (!StringUtils.isBlank(include) && !list.contains(include)) {
            if (!tplContain(excludes, include)) {
                int start = include.lastIndexOf("/");
                int end = include.lastIndexOf(".");
                if (start == -1 || end == -1) {
                    throw new RuntimeException("include not contain '/' or '.':" + include);
                }
                result.add(include.substring(start + 1, end));
            }
        }
        for (String t : list) {
            if (!tplContain(excludes, t)) {
                int start = t.lastIndexOf("/");
                int end = t.lastIndexOf(".");
                if (start == -1 || end == -1) {
                    throw new RuntimeException("name not contain '/' or '.':" + t);
                }
                t = t.substring(start + 1, end);
                if(t.contains(prefix)){
                    result.add(t);
                }
            }
        }
        return result;
    }


    private static boolean tplContain(String[] excludes, String tpl) {
        int start = tpl.lastIndexOf("/");
        int end = tpl.lastIndexOf(".");
        if (start == -1 || end == -1) {
            throw new RuntimeException("tpl not contain '/' or '.':" + tpl);
        }
        String name = tpl.substring(start + 1, end);
        for (String e : excludes) {
            if (e.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
