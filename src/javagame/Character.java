package javagame;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Character extends Movable{
	private Direction facing = Direction.DOWN;
	private boolean walking = false;
	private Image bulletImage;
	private Rect bulletBoundBox;
	private long lastShotTime = 0;
	private Animation currentAnim;
	private CharSprite sprite;
	private CharControls controls;
	private int health;
	private int maxHealth;
	private boolean dead = false;
	private long timeOfDeath = 0;
	private int startX;
	private int startY;
	
	public Character(int x, int y, Rect boundBox, CharSprite sprite, Image bulletImage,
			Rect bulletBoundBox, CharControls controls) throws SlickException{
		super(x, y, boundBox);
		this.startX = x;
		this.startY = y;
		this.sprite = sprite;
		
		currentAnim = sprite.getWalkDownAnim();
		currentAnim.stop();
		
		this.bulletImage = bulletImage;
		this.bulletBoundBox = bulletBoundBox;
		
		this.controls = controls;
		
		this.health = 100;
		this.maxHealth = 100;
	}
	
	@Override
	public void draw(Graphics g, float cameraX, float cameraY){
		if(!this.dead){
			g.drawAnimation(currentAnim, x - cameraX, y - cameraY);
			g.setColor(Color.green);
			g.fillRect(x + 16 - cameraX, y - cameraY, (float)(Game.TILE_SIZE / 2) * (float)health / (float)maxHealth, 3);
		}
	}

	public void update(Input input, int d, Level level, ArrayList<Character> characters, ArrayList<Projectile> bullets, long runningTime){
		if(!this.dead){
			float delta = Game.CHAR_SPEED * (float)d;
			boolean newWalking = false;
			Direction newFacing = facing;
	
			if(input.isKeyDown(controls.getGoLeft())){
				newFacing = Direction.LEFT;
				currentAnim = sprite.getWalkLeftAnim();
				newWalking = true;	
				this.goLeft(level, characters, delta);
			}
			if(input.isKeyDown(controls.getGoRight())){
				newFacing = Direction.RIGHT;
				currentAnim = sprite.getWalkRightAnim();
				newWalking = true;
				this.goRight(level, characters, delta);
			}
			if(input.isKeyDown(controls.getGoUp())){
				newFacing = Direction.UP;
				currentAnim = sprite.getWalkUpAnim();
				newWalking = true;
				this.goUp(level, characters, delta);
			}
			if(input.isKeyDown(controls.getGoDown())){	
				newFacing = Direction.DOWN;
				currentAnim = sprite.getWalkDownAnim();
				newWalking = true;
				this.goDown(level, characters, delta);
			}
			if(input.isKeyDown(controls.getShoot())){
				shoot(bullets);
			}
			
			if(this.walking && !newWalking){ //if stops walking
				currentAnim.restart();
				currentAnim.stop();
			}	
			else if(!this.walking && newWalking){ //if starts walking
				currentAnim.restart();
				currentAnim.start();
			}
			if(newWalking && this.facing != newFacing){ //start anim if changes direction mid-walk
				currentAnim.restart();
				currentAnim.start();
			}
			
			this.walking = newWalking;
			this.facing = newFacing;
			
			if(this.health == 0){
				this.dead = true;
				this.timeOfDeath = runningTime;
			}
		}
		else{
			if(runningTime > this.timeOfDeath + Game.DEATH_TIMER){
				this.dead = false;
				this.health = 100;
				this.x = this.startX;
				this.y = this.startY;
			}
		}
	}
	
	private void shoot(ArrayList<Projectile> bullets){
		long currentTime = Play.getTime();
		final int shootDelay = 500;
		if(currentTime > lastShotTime + shootDelay){
			lastShotTime = currentTime;
			Projectile bullet = new Projectile(x, y, bulletBoundBox, bulletImage, 10, facing, this);
			bullets.add(bullet);
		}
	}

	public float getCenteredCameraX(){
		return x - (Game.WIDTH - Game.TILE_SIZE)/2;
	}
	
	public float getCenteredCameraY(){
		return y - (Game.HEIGHT - Game.TILE_SIZE)/2;
	}
	
	public void onBeingShot(Character shooter){
		//System.out.println(this.toString() + ": " + shooter.toString() + " shot at me!");
		this.health -= 10;
		if(this.health <= 0) this.health = 0;
		//System.out.println("I have " + this.health + " hp left");
	}
	
	public boolean collisionCheck(ArrayList<Rect> solidObjects, ArrayList<Character> characters, float newX, float newY){
		Rect movableRect = new Rect((int)(newX + boundBox.x), (int)(newY + boundBox.y), boundBox.w, boundBox.h);
		for(int i = 0; i < solidObjects.size(); i++){
			Rect rect = solidObjects.get(i);
			if(Rect.areColliding(rect, movableRect)) return false;
		}
		
		for(Iterator<Character> it = characters.iterator(); it.hasNext();){
			Character character = it.next();
			if(character == this) continue;
			if(character.isColliding(movableRect)) return false;
		}
		return true;
	}
	
	public boolean isColliding(Rect rect){
		return Rect.areColliding(rect, new Rect((int)(x + boundBox.x),
				(int)(y + boundBox.y), boundBox.w, boundBox.h));
	}
}
