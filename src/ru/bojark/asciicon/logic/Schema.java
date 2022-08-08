package ru.bojark.asciicon.logic;

/**
 * Класс отвечает за попиксельное превращение изображения в ASCII и задаёт соответствие символов конкретному диапазону яркости.
 */
public class Schema {
    private final char[] CHARSET;
    private final int DIA;

    public Schema() {
        this(new char[]{'@', '$', '%', '#', '»', '*', '+', '-', ' '});
    }

    public Schema(char[] charset) {
        this.CHARSET = charset;
        this.DIA = (charset.length - 1) * 4;
    }

    /**
     * Метод, который соотносит яркость и символ из набора.
     * @param color яркость пикселя от 0 до 255
     * @return возвращает символ по прицнипу 255 / (длина массива - 1) * 4.
     */
    public char convert(int color) {
        return CHARSET[color / DIA];
    }

}
