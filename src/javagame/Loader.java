package javagame;

import org.newdawn.slick.Image;

public class Loader{
	public static Image loadImage(Image sheet, int x, int y){
		return sheet.getSubImage(x * Game.TILE_SIZE, y * Game.TILE_SIZE,
				Game.TILE_SIZE, Game.TILE_SIZE);
	}
}
