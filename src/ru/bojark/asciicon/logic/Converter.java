package ru.bojark.asciicon.logic;

import ru.bojark.asciicon.constants.Strings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Класс Converter отвечает за конвератцию изображения, переданного по URL, в ASCII-изображение.
 * По умолчанию конвертирует изображение eagle.png в корневой папке программы.
 */
public class Converter {

    private final Schema schema;
    private final int maxHeight;
    private final int maxWidth;
    private final int symbolWeight;
    private final int SW_DEF = 2; //значение symbolWeight по умолчанию на случай неправильного ввода, вывел для удобства

    /**
     * @param schema       схема конвертации, экземпляр класса Schema.
     * @param maxHeight    максимальная высота изображения на выходе.
     * @param maxWidth     максимальная ширина изображения на выходе.
     * @param symbolWeight количество ASCII-символов на пиксель
     */
    public Converter(Schema schema, int maxHeight, int maxWidth, int symbolWeight) {

        this.schema = schema;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        if (symbolWeight <= 0) {
            this.symbolWeight = SW_DEF;
        } else {
            this.symbolWeight = symbolWeight;
        }

    }

    /**
     * Метод, который управляет конвертацией изображения.
     *
     * @param url ссылка на изображение.
     * @return изображение в виде строки.
     * @throws IOException ошибка, связанная с некорректным url или неправильно введёнными данными
     */

    public String convert(String url) throws IOException {
        BufferedImage img;
        if (url.equals("")) {
            img = ImageIO.read(new File(Strings.LOCALEAGLE));
        } else {
            try {
                img = ImageIO.read(new URL(url));
            } catch (IOException e) {
                System.out.println(Strings.URLError);
                img = ImageIO.read(new File(Strings.LOCALEAGLE));
            }

        }

        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();

        //проверка пропорций: высота

        if (maxHeight != 0 && maxHeight < imgHeight) {
            float aspect = (float) maxHeight / (float) imgHeight;
            imgHeight = maxHeight;
            imgWidth = (int) ((float) imgWidth * aspect);
            if (imgWidth == 0) {
                imgWidth = 1;
            }
        }

        //проверка пропорций: ширина
        if (maxWidth != 0 && maxWidth < imgWidth) {
            float aspect = (float) maxWidth / (float) imgWidth;
            imgWidth = maxWidth;
            imgHeight = (int) ((float) imgHeight * aspect);
            if (imgHeight == 0) {
                imgHeight = 1;
            }
        }

        //создаём новую ч/ю картинку и рисоватор

        BufferedImage bwImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();

        //скейлим и чернобелим картинку:
        if (imgHeight < img.getHeight() || imgWidth < img.getWidth()) {
            Image scaledImage = img.getScaledInstance(imgWidth, imgHeight, BufferedImage.SCALE_SMOOTH);
            graphics.drawImage(scaledImage, 0, 0, null);
        } else {
            graphics.drawImage(img, 0, 0, null);
        }

        //сохраняем обработанное изображение (на всякий случай):
        //ImageIO.write(bwImg, "png", new File("out.png"));

        //приступаем к конвертации значений ярковсти в символы
        WritableRaster bwRaster = bwImg.getRaster();

        int[] iArray = new int[3];
        int[][] colorSet = new int[imgWidth][imgHeight];

        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                colorSet[i][j] = bwRaster.getPixel(i, j, iArray)[0];
            }
        }

        //собираем строку

        StringBuilder imgString = new StringBuilder();

        for (int i = 0; i < imgHeight; i++) {
            for (int j = 0; j < imgWidth; j++) {

                for (int k = 0; k < symbolWeight; k++) {
                    imgString.append(schema.convert(colorSet[j][i]));
                }

            }
            imgString.append("\n");
        }

        //сохраняем в файл:

        try (PrintWriter out = new PrintWriter("out.txt")) {
            out.println(imgString);
        }


        System.out.println(Strings.DONE);
        return imgString.toString(); // Возвращаем собранный текст.
    }

    /**
     * Метод для тестовой отрисовки значений яркости пикселя.
     *
     * @param imgHeight высота изображения.
     * @param imgWidth  ширина изображения.
     * @param colorSet  матрица  в виде значений яркости пикселей изображения по ширине и высоте.
     */

    private void intPrint(int imgHeight, int imgWidth, int[][] colorSet) {
        for (int i = 0; i < imgHeight; i++) {
            for (int j = 0; j < imgWidth; j++) {
                System.out.print(colorSet[j][i] + " ");
            }
            System.out.println();
        }
    }

}

