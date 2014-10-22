package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {

	private static final long serialVersionUID = 44234291373717736L;
	private ImageIcon imageIcon = null;
	private BufferedImage bufferedImage = null;

	public ImageLabel() {
		super();
		imageIcon = new ImageIcon("gfx/logo.png");
		setIcon(imageIcon);
		try {
			bufferedImage = ImageIO.read(new File("gfx/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBounds(0, 0, 640, 480);
		setPreferredSize(new Dimension(640,480));
		setBackground(new Color(255,255,255));
		setOpaque(true);
		revalidate();
		updateUI();
		pixeltest();
		
	}
	
	public void pixeltest()
	{
		 byte[] pixels =  ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		 boolean hasAlphaChannel = bufferedImage.getAlphaRaster() != null;
		 int width = bufferedImage.getWidth();
	     int height = bufferedImage.getHeight();
	     
		 if (hasAlphaChannel) {
	         int pixelLength = 4;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	            
	            //result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               System.out.println(argb);
	               row++;
	            }
	         }
	      } else {
	         int pixelLength = 3;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += -16777216; // 255 alpha
	            argb += ((int) pixels[pixel] & 0xff); // blue
	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	            //result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      }
	}

}
