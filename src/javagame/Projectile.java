package javagame;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Projectile extends Movable{
	private Image image;
	//private int damage;
	private boolean isOut = false;
	Character shooter;
	
	public Projectile(float x, float y, Rect boundBox, Image image, int damage, Direction dir, Character shooter){
		super(x, y, boundBox);
		this.image = image;
		//this.damage = damage;
		this.dir = dir;
		this.shooter = shooter;
	}
	
	@Override
	public void draw(Graphics g, float camX, float camY){
		g.drawImage(image, x - camX, y - camY);
	}
	
	public void update(int d, Level level, ArrayList<Character> characters){
		float delta = Game.BULLET_SPEED * (float)d;
		if(dir == Direction.LEFT){
			if(!this.goLeft(level, characters, delta)) isOut = true;
		}
		if(dir == Direction.RIGHT){
			if(!this.goRight(level, characters, delta)) isOut = true;
		}
		if(dir == Direction.UP){
			if(!this.goUp(level, characters, delta)) isOut = true;
		}
		if(dir == Direction.DOWN){
			if(!this.goDown(level, characters, delta)) isOut = true;
		}
	}
	
	public boolean isOut(Level level){
		return isOut;
	}
	
	public boolean collisionCheck(ArrayList<Rect> solidObjects, ArrayList<Character> characters, float newX, float newY){
		Rect movableRect = new Rect((int)(newX + boundBox.x), (int)(newY + boundBox.y), boundBox.w, boundBox.h);
		for(int i = 0; i < solidObjects.size(); i++){
			Rect rect = solidObjects.get(i);
			if(Rect.areColliding(rect, movableRect)) return false;
		}
		
		for(Iterator<Character> it = characters.iterator(); it.hasNext();){
			Character character = it.next();
			if(character == shooter) continue;
			if(character.isColliding(movableRect)){
				character.onBeingShot(this.shooter);
				return false;
			}
		}
		return true;
	}
}
