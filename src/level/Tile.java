package level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import game.Game;

public class Tile{
	private Image image;
	private int x; //0,1,2,3,... indices, not pixels
	private int y;

	public Tile(Image image, int x, int y){
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g, float camX, float camY){
		g.drawImage(image, Game.TILE_SIZE * x - camX, Game.TILE_SIZE * y - camY);
	}
	
}
