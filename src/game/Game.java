package game;

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
	public static final boolean SPLITSCREEN = false;
	public static final String SHEET_PATH = "res/sheet.png";
	public static final String WINDOW_TITLE = "HEHEHEHE";
	public static final String LEVEL_PATH = "res/level0.txt";
	public static final String MUSIC_PATH = "res/music.wav";
	public static final String LEVEL_PROPS_PATH = "res/level0_props.txt";
	public static final int BULLET_DAMAGE = 20;
	public static final String SPRITES_PATH = "res/sprites.txt";
	
	public Game(String title){
		super(title);
		addState(new Menu(MENU));
		addState(new Play(PLAY));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException{
		getState(MENU).init(gc, this);
		getState(PLAY).init(gc, this);
		enterState(MENU);
	}
	
	public static void main(String[] args){
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(WINDOW_TITLE));
			if(Game.SPLITSCREEN) appgc.setDisplayMode(2 * Game.WIDTH, Game.HEIGHT, false);
			else appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setShowFPS(false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}
