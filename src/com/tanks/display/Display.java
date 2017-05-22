package com.tanks.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {

    /**
     * Создано ли окно
     */
    private static boolean isCreated = false;
    /**
     * Окно игры
     */
    private static JFrame window;
    /**
     *
     */
    private static Canvas content;

    private static BufferedImage buffer;

    /**
     * Массив цветов
     */
    private static int[] bufferData;

    private static Graphics bufferGraphics;

    /**
     * Цвет для обнуления картинки
     */
    private static int clearColor;

    private static float delta = 0;

    /**
     * Создание окна
     * @param width ширина
     * @param height высота
     * @param title название
     */
    public static void create(int width, int height, String title, int _clearColor) {
        if (isCreated) {
            return;
        }
        window = new JFrame((title));
        // Закрытие программы по крестику
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content = new Canvas();
        // установка размеров окна
        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);
        // Цвет
        content.setBackground(Color.black);
        // Запрет на изменение размера окна
        window.setResizable(false);
        window.getContentPane().add(content);
        // Добавление только того что внутри окна
        window.pack();
        // Поставить по центру
        window.setLocationRelativeTo(null);
        // Видимость
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        clearColor = _clearColor;

        isCreated = true;
    }

    /**
     * Очищение экрана
     */
    public static void clear() {
        // Заполнение массива цветов очищающим цветом
        Arrays.fill(bufferData, clearColor);
    }

    public static void render() {
        bufferGraphics.setColor(new Color(0xff0000ff));
        bufferGraphics.fillOval((int) (350 + (Math.sin(delta) * 200)), 250, 100, 100);
        delta += 0.02f;
    }

    public  static void swapBuffers() {
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}
