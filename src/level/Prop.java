package level;

import org.newdawn.slick.Graphics;

public class Prop{
	private Sprite sprite;
	private float x;
	private float y;
	private boolean passable;

	
	public Prop(Sprite sprite, float x, float y){
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		passable = false;
	}
	
	public Prop(Sprite sprite, float x, float y, boolean passable){
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.passable = passable;
	}
	
	public void draw(Graphics g, float camX, float camY){
		g.drawImage(sprite.getImage(), x - camX, y - camY);
	}
	
	public boolean isPassable(){
		return passable;
	}
	
	public Sprite getSprite(){
		return sprite;
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
}
