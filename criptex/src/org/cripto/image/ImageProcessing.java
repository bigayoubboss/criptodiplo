/*
 * Universidad Nacional de Colombia - Sede Bogotá *
 *
 *      Christian Rodriguez - carodriguezb@unal.edu.co
 *
 * Código liberado bajo licencia Creative Commons 3.0
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
*/

package org.cripto.image;

import java.util.Random;

/**
 *
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class ImageProcessing {

    private static MarvinImage image;

    public static MarvinImage completeSquareImage(MarvinImage imageIncomplete) {
        Random rnd = new Random();

        int m = 0;
        int diferencia = 0;
        if (imageIncomplete.getWidth() > imageIncomplete.getHeight()) {
            diferencia = imageIncomplete.getWidth() - imageIncomplete.getHeight();
            m = imageIncomplete.getWidth();
        } else {
            diferencia = imageIncomplete.getHeight() - imageIncomplete.getWidth();
            m = imageIncomplete.getHeight();
        }
        MarvinImage imageComplete = new MarvinImage(m, m);        
        for (int x = 0; x < imageIncomplete.getWidth(); x++) {
            for (int y = 0; y < imageIncomplete.getHeight(); y++) {
                imageComplete.setRGB(x, y, imageIncomplete.getRed(x, y), imageIncomplete.getGreen(x, y), imageIncomplete.getBlue(x, y));
            }
        }
        if (imageIncomplete.getWidth() > imageIncomplete.getHeight()) {
            for (int x = 0; x < m; x++) {
                for (int y = imageIncomplete.getWidth() - diferencia; y < m; y++) {
                    imageComplete.setRGB(x, y, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                }
            }
        } else {
            for (int x = imageIncomplete.getHeight() - diferencia; x < m; x++) {
                for (int y = 0; y < m; y++) {
                    imageComplete.setRGB(x, y, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                }
            }
        }
        return imageComplete;
    }

    public static int[][][] loadImageToRGB(String path) throws ArrayIndexOutOfBoundsException{
        image = MarvinImageIO.loadImage(path);
        if (image.getWidth() > 1024 || image.getHeight() > 1024){
            throw new ArrayIndexOutOfBoundsException("La imagen excede el tamaño máximo permitido (1024x1024).");
        }
        if (image.getWidth() != image.getHeight()) {
            image = completeSquareImage(image);
        }
        image.update();
        return MarvinImageToRGBImage(image);
    }

    public static void saveImageFromRGB(String path, int[][][] RGB) {
        image = RGBImageToMarvinImage(RGB);
        MarvinImageIO.saveImage(image, path);
    }

    public static int[][][] MarvinImageToRGBImage(MarvinImage image) {
        int h = image.getHeight();
        int w = image.getWidth();
        int[][][] imageRGB = new int[3][h][w];
        for (int fil = 0; fil < h; fil++) {
            for (int col = 0; col < w; col++) {
                imageRGB[0][fil][col] = image.getRed(col, fil);
                imageRGB[1][fil][col] = image.getGreen(col, fil);
                imageRGB[2][fil][col] = image.getBlue(col, fil);
            }
        }
        return imageRGB;
    }

    public static MarvinImage RGBImageToMarvinImage(int[][][] RGB) {
        int h = RGB[0].length;
        int w = RGB[0][0].length;

        image = new MarvinImage(w, h);
        for (int fil = 0; fil < h; fil++) {
            for (int col = 0; col < w; col++) {
                image.setRGB(col, fil, RGB[0][fil][col], RGB[1][fil][col], RGB[2][fil][col]);
            }
        }
        image.update();
        return image;
    }

    public static int[][][] resizeRGB(int[][][] imageRGB, int width, int height) {
        image = RGBImageToMarvinImage(imageRGB);
        image.resize(width, height);
        return MarvinImageToRGBImage(image);
    }
}
