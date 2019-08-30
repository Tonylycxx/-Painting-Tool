package Draw;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public class drawPictureCanvas extends Canvas {
	
	private Image image = null;

	public void setImage(Image image) {
		this.image = image;
	}// the end of setImage

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}// the end of paint

	public void update(Graphics g) {
		paint(g);
	}// the end of update

}// the end of drawPictureCancas
