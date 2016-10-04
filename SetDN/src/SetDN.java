import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SetDN{
	private static BufferedImage image;
	
	public static void main(String[] args)
	{
		Game tool = new Game();
		tool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tool.setTitle("SET 2K16");
		tool.pack();
		tool.show();
	}
}
