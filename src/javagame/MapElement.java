package javagame;

import org.newdawn.slick.Image;

public class MapElement{
	private Rect boundBox;
	private Image image;
	private boolean passable;
	
	public MapElement(Image sheet, int x, int y, Rect boundBox, boolean passable){
		this.image = sheet.getSubImage(Game.TILE_SIZE * x, Game.TILE_SIZE * y,
				Game.TILE_SIZE, Game.TILE_SIZE);
		this.boundBox = boundBox;
		this.passable = passable;
	}

	public Image getImage(){
		return image;
	}

	public boolean isPassable(){
		return passable;
	}

	public Rect getBoundBox(){
		return boundBox;
	}
}
