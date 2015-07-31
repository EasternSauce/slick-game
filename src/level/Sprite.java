package level;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Sprite{
	private Image image;
	private Rectangle boundBox;
	
	public Sprite(Image image, Rectangle boundBox){
		this.image = image;
		this.boundBox = boundBox;
	}

	public Image getImage(){
		return image;
	}

	public Rectangle getBoundBox(){
		return boundBox;
	}
}
