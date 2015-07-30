package javagame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class CharSprite{
	private Image sheet;
	private int x;
	private int y;
	private Animation walkLeftAnim;
	private Animation walkRightAnim;
	private Animation walkUpAnim;
	private Animation walkDownAnim;
	
	public CharSprite(Image sheet, int x, int y){
		this.sheet = sheet;
		this.x = x;
		this.y = y;

		Image[] walkLeft = new Image[Game.CHAR_SPRITE_SHEET_W];
		Image[] walkRight = new Image[Game.CHAR_SPRITE_SHEET_W];
		Image[] walkUp = new Image[Game.CHAR_SPRITE_SHEET_W];
		Image[] walkDown = new Image[Game.CHAR_SPRITE_SHEET_W];
		
		for(int i = 0; i < 5; i++){
			walkLeft[i] = Loader.loadImage(sheet, i+x, 0+y);
			walkRight[i] = Loader.loadImage(sheet, i+x, 1+y);
			walkUp[i] = Loader.loadImage(sheet, i+x, 2+y);
			walkDown[i] = Loader.loadImage(sheet, i+x, 3+y);
		}
		
		final int duration = Game.CHAR_ANIM_DURATION;
		walkLeftAnim = new Animation(walkLeft, duration);
		walkRightAnim = new Animation(walkRight, duration);
		walkUpAnim = new Animation(walkUp, duration);
		walkDownAnim = new Animation(walkDown, duration);
	}

	public Animation getWalkLeftAnim(){
		return walkLeftAnim;
	}

	public Animation getWalkRightAnim(){
		return walkRightAnim;
	}

	public Animation getWalkUpAnim(){
		return walkUpAnim;
	}

	public Animation getWalkDownAnim(){
		return walkDownAnim;
	}
	
	public CharSprite copy(){
		return new CharSprite(sheet, x, y);
	}
	
	
}
