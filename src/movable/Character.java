package movable;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import game.Game;
import game.Play;
import level.Level;
import level.Sprite;
import utility.Direction;

public class Character extends Movable{
	private Direction facing;
	private boolean walking;
	private long lastShotTime;
	private Animation currentAnim;
	private CharSprite sprite;
	private CharControls controls;
	private int health;
	private int maxHealth;
	private boolean dead;
	private long timeOfDeath;
	private int startX;
	private int startY;
	private Sprite bulletSprite;
	
	public Character(int x, int y, Rectangle boundBox, CharSprite sprite, Sprite bulletSprite, CharControls controls) throws SlickException{
		super(x, y, boundBox);
		facing = Direction.DOWN;
		walking = false;
		lastShotTime = 0;
		currentAnim = sprite.getWalkDownAnim();
		this.sprite = sprite;
		this.controls = controls;
		health = 100;
		maxHealth = 100;
		dead = false;
		timeOfDeath = 0;
		startX = x;
		startY = y;
		this.bulletSprite = bulletSprite;
		
		currentAnim.stop();
	}
	
	@Override
	public void draw(Graphics g, float cameraX, float cameraY){
		if(!dead){
			g.drawAnimation(currentAnim, x - cameraX, y - cameraY);
			g.setColor(Color.green);
			g.fillRect(x + Game.TILE_SIZE / 4 - cameraX, y - cameraY, (float)(Game.TILE_SIZE / 2) * (float)health / (float)maxHealth, 3);
		}
	}

	public void update(Input input, int d, Level level, ArrayList<Character> characters, ArrayList<Projectile> bullets, long runningTime){
		if(!dead){
			float delta = Game.CHAR_SPEED * (float)d;
			boolean newWalking = false;
			Direction newFacing = facing;
	
			if(input.isKeyDown(controls.getGoLeft())){
				newFacing = Direction.LEFT;
				currentAnim = sprite.getWalkLeftAnim();
				newWalking = true;	
				goLeft(level, characters, delta);
			}
			if(input.isKeyDown(controls.getGoRight())){
				newFacing = Direction.RIGHT;
				currentAnim = sprite.getWalkRightAnim();
				newWalking = true;
				goRight(level, characters, delta);
			}
			if(input.isKeyDown(controls.getGoUp())){
				newFacing = Direction.UP;
				currentAnim = sprite.getWalkUpAnim();
				newWalking = true;
				goUp(level, characters, delta);
			}
			if(input.isKeyDown(controls.getGoDown())){	
				newFacing = Direction.DOWN;
				currentAnim = sprite.getWalkDownAnim();
				newWalking = true;
				goDown(level, characters, delta);
			}
			if(input.isKeyDown(controls.getShoot())){
				shoot(bullets);
			}
			
			if(walking && !newWalking){ //if stops walking
				currentAnim.restart();
				currentAnim.stop();
			}	
			else if(!walking && newWalking){ //if starts walking
				currentAnim.restart();
				currentAnim.start();
			}
			if(newWalking && facing != newFacing){ //start anim if changes direction mid-walk
				currentAnim.restart();
				currentAnim.start();
			}
			
			walking = newWalking;
			facing = newFacing;
			
			if(health == 0){
				dead = true;
				timeOfDeath = runningTime;
			}
		}
		else{
			if(runningTime > timeOfDeath + Game.DEATH_TIMER){
				dead = false;
				health = 100;
				x = startX;
				y = startY;
			}
		}
	}
	
	private void shoot(ArrayList<Projectile> bullets){
		long currentTime = Play.getTime();
		final int shootDelay = 500;
		if(currentTime > lastShotTime + shootDelay){
			lastShotTime = currentTime;
			Projectile bullet = new Projectile(x, y, bulletSprite, Game.BULLET_DAMAGE, facing, this);
			bullets.add(bullet);
		}
	}

	public float getCenteredCameraX(){
		return x - (Game.WIDTH - Game.TILE_SIZE)/2;
	}
	
	public float getCenteredCameraY(){
		return y - (Game.HEIGHT - Game.TILE_SIZE)/2;
	}
	
	public void onBeingShot(Character shooter, Projectile bullet){
		health -= bullet.getDamage();
		if(health <= 0) health = 0;
	}
	
	public boolean collisionCheck(ArrayList<Rectangle> solidObjects,
			ArrayList<Character> characters, float newX, float newY){
		Rectangle movableRect = new Rectangle((int)(newX + boundBox.getX()),
				(int)(newY + boundBox.getY()), boundBox.getWidth(), boundBox.getHeight());
		for(int i = 0; i < solidObjects.size(); i++){
			Rectangle rect = solidObjects.get(i);
			if(rect.intersects(movableRect)) return false;
		}
		
		for(Iterator<Character> it = characters.iterator(); it.hasNext();){
			Character character = it.next();
			if(character == this || character.isDead()) continue;
			if(character.isColliding(movableRect)) return false;
		}
		return true;
	}
	
	public boolean isDead(){
		return dead;
	}

	public boolean isColliding(Rectangle rect){
		return rect.intersects(new Rectangle((int)(x + boundBox.getX()),
				(int)(y + boundBox.getY()), boundBox.getWidth(), boundBox.getHeight()));
	}
}
