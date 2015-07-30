package javagame;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class Movable{
	protected float x;
	protected float y;
	protected Direction dir;
	protected Rect boundBox;
	
	public Movable(float x, float y, Rect boundBox){
		this.x = x;
		this.y = y;
		this.boundBox = boundBox;
		this.dir = Direction.DOWN;
	}
	
	public abstract boolean collisionCheck(ArrayList<Rect> solidObjects, ArrayList<Character> characters, float newX, float newY);
	
	public boolean goLeft(Level level, ArrayList<Character> characters, float delta){
		boolean able = false;
		float newX = x - delta;
		float newY = y;
		if(newX + boundBox.x >=0){
			if(collisionCheck(level.getSolidObjects(), characters, newX, newY)){
				able = true;
				x = newX;
				y = newY;
			}
		}
		return able;
	}
	
	public boolean goRight(Level level, ArrayList<Character> characters, float delta){
		boolean able = false;
		float newX = x + delta;
		float newY = y;
		if(newX + boundBox.x + boundBox.w < level.getWidth() * Game.TILE_SIZE){
			if(collisionCheck(level.getSolidObjects(), characters, newX, newY)){
				able = true;
				x = newX;
				y = newY;
			}
		}
		return able;
	}
	
	public boolean goUp(Level level, ArrayList<Character> characters, float delta){
		boolean able = false;
		float newX = x;
		float newY = y - delta;
		if(newY + boundBox.y >=0){
			if(this.collisionCheck(level.getSolidObjects(), characters, newX, newY)){
				able = true;
				x = newX;
				y = newY;
			}
		}
		return able;
	}
	
	public boolean goDown(Level level, ArrayList<Character> characters, float delta){
		boolean able = false;
		float newX = x;
		float newY = y + delta;
		if(newY + boundBox.y + boundBox.h < level.getHeight() * Game.TILE_SIZE){
			if(collisionCheck(level.getSolidObjects(), characters, newX, newY)){
				able = true;
				x = newX;
				y = newY;
			}
		}
		return able;
	}

	public abstract void draw(Graphics g, float cameraX, float cameraY);


}
