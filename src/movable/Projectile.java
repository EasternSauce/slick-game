package movable;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import game.Game;
import level.Level;
import level.Sprite;
import utility.Direction;

public class Projectile extends Movable{
	private int damage;
	private Sprite sprite;
	private boolean isOut;
	private Character shooter;
	
	public Projectile(float x, float y, Sprite sprite, int damage, Direction dir, Character shooter){
		super(x, y, sprite.getBoundBox());
		this.dir = dir;
		
		this.damage = damage;
		this.sprite = sprite;
		isOut = false;
		this.shooter = shooter;
	}
	
	@Override
	public void draw(Graphics g, float camX, float camY){
		g.drawImage(sprite.getImage(), x - camX, y - camY);
	}
	
	public void update(int d, Level level, ArrayList<Character> characters){
		float delta = Game.BULLET_SPEED * (float)d;
		if(dir == Direction.LEFT){
			if(!goLeft(level, characters, delta)) isOut = true;
		}
		if(dir == Direction.RIGHT){
			if(!goRight(level, characters, delta)) isOut = true;
		}
		if(dir == Direction.UP){
			if(!goUp(level, characters, delta)) isOut = true;
		}
		if(dir == Direction.DOWN){
			if(!goDown(level, characters, delta)) isOut = true;
		}
	}
	
	public boolean isOut(Level level){
		return isOut;
	}
	
	public boolean collisionCheck(ArrayList<Rectangle> solidObjects, ArrayList<Character> characters, float newX, float newY){
		Rectangle movableRect = new Rectangle((int)(newX + boundBox.getX()), (int)(newY + boundBox.getY()), boundBox.getWidth(), boundBox.getHeight());
		for(int i = 0; i < solidObjects.size(); i++){
			Rectangle rect = solidObjects.get(i);
			if(rect.intersects(movableRect)) return false;
		}
		
		for(Iterator<Character> it = characters.iterator(); it.hasNext();){
			Character character = it.next();
			if(character == shooter || character.isDead()) continue;
			if(character.isColliding(movableRect)){
				character.onBeingShot(shooter, this);
				return false;
			}
		}
		return true;
	}

	public int getDamage(){
		return damage;
	}
}
