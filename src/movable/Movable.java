package movable;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import game.Game;
import level.Level;
import utility.Direction;

public abstract class Movable{
	protected float x;
	protected float y;
	protected Direction dir;
	protected Rectangle boundBox;
	
	public Movable(float x, float y, Rectangle boundBox){
		this.x = x;
		this.y = y;
		this.dir = Direction.DOWN;
		this.boundBox = boundBox;
	}
	
	public abstract boolean collisionCheck(ArrayList<Rectangle> solidObjects,
			ArrayList<Character> characters, float newX, float newY);
	
	public boolean goLeft(Level level, ArrayList<Character> characters, float delta){
		boolean able = false;
		float newX = x - delta;
		float newY = y;
		if(newX + boundBox.getX() >=0){
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
		if(newX + boundBox.getX() + boundBox.getWidth() < level.getWidth() * Game.TILE_SIZE){
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
		if(newY + boundBox.getY() >=0){
			if(collisionCheck(level.getSolidObjects(), characters, newX, newY)){
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
		if(newY + boundBox.getY() + boundBox.getHeight() < level.getHeight() * Game.TILE_SIZE){
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
