
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageIOExample {   
	
    public static void main( String[] args ) throws IOException{
    	File imageFile = new File("C://users/xiatu/Desktop/icon1.jpg");
        BufferedImage img = ImageIO.read(imageFile);
System.out.println(img.getHeight());
System.out.println(img.getWidth());
        Graphics2D graph = img.createGraphics();
        graph.drawLine(0, 0, img.getWidth(), 0);
        graph.setColor(Color.WHITE);
        graph.drawString("Hello", 10, 10);
        graph.dispose();

        ImageIO.write(img, "jpg", new File("C://Temp/test.jpg"));
    }
}

