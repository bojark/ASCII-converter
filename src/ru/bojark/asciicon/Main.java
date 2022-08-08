package ru.bojark.asciicon;

import ru.bojark.asciicon.logic.Converter;
import ru.bojark.asciicon.logic.Schema;
import ru.bojark.asciicon.constants.Strings;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println(Strings.GREET);
        System.out.print(Strings.POINTER);
        String url = sc.nextLine();

        int width = paramInput(sc, Strings.SETWIDTH);
        System.out.println(width);
        int height = paramInput(sc, Strings.SETHEIGHT);
        int symbolWeight = paramInput(sc, Strings.SETSYMBOLWEIGHT);

        sc.close();

        Converter converter = new Converter(new Schema(), height, width, symbolWeight);
        converter.convert(url);
        System.out.println(Strings.GOODBYE);

    }

    /**
     * Метод для ввода пользователем параметров конвертации в консоли.
     *
     * @param sc      экземпляр класса Scanner
     * @param message сообщение пользователю
     */
    private static int paramInput(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            System.out.print(Strings.POINTER);

            int param;
            try {
                param = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Strings.NFError);
                continue;
            }
            return param;
        }
    }
}
