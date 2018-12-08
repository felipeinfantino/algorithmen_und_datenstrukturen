import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestConIm {
	
	public static void main(String[] args) throws IOException {
		
		
		Picture x = new Picture("C:\\Users\\Felipe\\Desktop\\prueba2.jpg");
                
                
               	x.rot90DegRight();
                BufferedImage y  = x.getImage();
                
                File ejemplo = new File("C:\\Users\\Felipe\\Desktop\\neuesBild.jpg");
                ImageIO.write(y, "jpg", ejemplo);
		
         
		
		
	}
}
