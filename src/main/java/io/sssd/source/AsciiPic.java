package io.sssd.source;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AsciiPic {

    /**
     * @param path
     *            图片路径
     */
    public static void createAsciiPic(final String path) {
        final String base = "魔娜御罩鲜真骑眼莎姐美画如安补杜耳血兵田本戈子之工ライダー";// 字符串由复杂到简单
        try {
            final BufferedImage image = ImageIO.read(new File(path));

            final BufferedImage newImage;

            /* 原始图像的宽度和高度 */
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println(width + height);
            //压缩计算
            /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/
            float resizeTimes = 0.17f;

            /* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);

            /* 新生成结果图片 */
            newImage = new BufferedImage(toWidth, toHeight,
                    BufferedImage.TYPE_INT_RGB);

            newImage.getGraphics().drawImage(
                    image.getScaledInstance(toWidth, toHeight,
                            java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            for (int y = 0; y < newImage.getHeight(); y += 2) {
                for (int x = 0; x < newImage.getWidth(); x += 2) {
                    final int pixel = newImage.getRGB(x, y);
                    final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                    final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                    final int index = Math.round(gray * (base.length() + 1) / 255);
                    System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
                }
                System.out.println();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(final String[] args) {
        AsciiPic.createAsciiPic("D:\\15.jpg");
    }
}
