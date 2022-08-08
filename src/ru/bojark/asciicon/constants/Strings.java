package ru.bojark.asciicon.constants;

/**
 * Утилитарный класс:
 * строчные константы для классов ASCII-конвертера.
 */
public final class Strings {

    //цвета текста в консоли:
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String CYAN_BOLD = "\033[1;36m";

    public static final String PURPLE_BOLD = "\033[1;35m";

    //Стринги:

    public static final String VERSION = "build 1.0.080822";
    public static final String GREET = GREEN_BOLD + "Welcome to ASCII Graphics Converter " + VERSION +
            BLACK_BOLD + "\nPaste here the url of the image you want to convert (leave the line blank to"
            + " convert the bald eaglet):" + RESET;
    public static final String POINTER = BLACK_BOLD + ">>" + RESET;
    public static final String SETWIDTH = BLACK_BOLD +
            "Specify the width of the final image (Enter 0 to keep the width):" + RESET;
    public static final String SETHEIGHT = BLACK_BOLD +
            "Specify the height of the final image (Enter 0 to keep the height):" + RESET;
    public static final String SETSYMBOLWEIGHT = BLACK_BOLD +
            "Specify the number of characters per pixel (default is 2):" + RESET;
    public static final String NFError = YELLOW + "Invalid number." + RESET;
    public static final String DONE = GREEN + "Ready. Image saved in " + GREEN_BOLD + "out.txt" + RESET;
    public static final String GOODBYE = CYAN_BOLD + "Thank you for using my converter.\n" + PURPLE_BOLD +
            "Goodbye and godspeed!" + RESET;
    public static String URLError = RED + "Can't find an image by this link. Converting an eaglet." + RESET;

    //url / адреса файлов

    public static final String WEBEAGLE = "https://img.freepik.com/premium-vector/eagle-head-illustration_527939-14.jpg";
    public static final String LOCALEAGLE = "./eagle.png";

}
