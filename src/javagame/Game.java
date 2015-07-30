package javagame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int TILE_SIZE = 64;
	public static final float CHAR_SPEED = 0.2f;
	public static final float BULLET_SPEED = 0.6f;
	public static final int CHAR_SPRITE_SHEET_W = 5;
	public static final int CHAR_SPRITE_SHEET_H = 5;
	public static final int CHAR_ANIM_DURATION = 200;
	public static final int DEATH_TIMER = 5000;
	
	public Game(String title){
		super(title);
		this.addState(new Menu(MENU));
		this.addState(new Play(PLAY));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(MENU).init(gc, this);
		this.getState(PLAY).init(gc, this);
		this.enterState(MENU);
	}
	
	public static void main(String[] args){
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game("HEHEHE"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setShowFPS(false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}
