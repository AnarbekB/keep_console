package ru.anarbek.helper;

final public class Util {

    /**
     * Function for delete leading symbols
     * example: -c => c, --create => create
     */
    public static String stripLeadingHyphens(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("--")) {
            return str.substring(2);
        }
        else if (str.startsWith("-")) {
            return str.substring(1);
        }

        return str;
    }

    /**
     * detect option in args[]
     */
    public static boolean isOption(String arg) {
        if (arg == null) {
            return false;
        }

        return arg.startsWith("--") || arg.startsWith("-");
    }
}
