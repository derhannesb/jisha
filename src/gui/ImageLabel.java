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
		 final byte[] pixels =  ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		 System.out.println(pixels.length);
	}

}
