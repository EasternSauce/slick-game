package javagame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
	private Character hero;
	private Character antihero;
	private Image sheet;
	private Input input;
	private Music music;
	private boolean goToMenu;
	private Level firstArea;
	private HashMap<String, MapElement> mapElements;
	private ArrayList<Projectile> bullets;
	private static long runningTime = 0;
	private ArrayList<Character> characters;
	
	public Play(int state){}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		input = new Input(Game.HEIGHT);
		goToMenu = false;
		bullets = new ArrayList<Projectile>();
		characters = new ArrayList<Character>();
		
		//some slick2ds settings
		this.setInput(input);
		input.disableKeyRepeat();
		input.addListener(this);
		
		//load sheet
		sheet = new Image("res/sheet.png");
		sheet.setFilter(Image.FILTER_NEAREST);
		
		//load stuff from the sheet...
		
		//load bullets
		Image bulletImage = Loader.loadImage(sheet, 4, 4);
		Rect bulletBoundBox = new Rect(30, 30, 4, 4);
		
		
		//load characters
		Rect charBoundBox = new Rect(26, 6, 13, 53);
		
		CharControls heroControls = new CharControls(Input.KEY_A, Input.KEY_D,
				Input.KEY_W, Input.KEY_S, Input.KEY_SPACE);
		CharSprite heroSprite = new CharSprite(sheet, 0, 0);
		hero = new Character(0, 0, charBoundBox, heroSprite, bulletImage, charBoundBox, heroControls);
		characters.add(hero);
		
		CharControls antiheroControls = new CharControls(Input.KEY_LEFT, Input.KEY_RIGHT,
				Input.KEY_UP, Input.KEY_DOWN, Input.KEY_RSHIFT);
		CharSprite antiheroSprite = heroSprite.copy();
		antihero = new Character(600, 100, charBoundBox, antiheroSprite, bulletImage, bulletBoundBox, antiheroControls);
		characters.add(antihero);
		
		//load map elements
		mapElements = new HashMap<String, MapElement>();
		mapElements.put("grass", new MapElement(sheet, 0, 4, new Rect(0,0,0,0), true));
		mapElements.put("tree", new MapElement(sheet, 1, 4, new Rect(17,9,26,38), false));
		mapElements.put("coin", new MapElement(sheet, 2, 4, new Rect(1,1,1,1), false));
		mapElements.put("door", new MapElement(sheet, 3, 4, new Rect(1,1,1,1), true));
		
		//create first level
		String[][] areaMap = {
				{"grass", "grass", "tree", "grass", "grass", "grass", "grass", "grass", "grass", "grass", "grass"},
				{"grass", "grass", "tree", "grass", "grass", "grass", "grass", "grass", "grass", "grass", "grass"},
				{"grass", "grass", "grass", "grass", "grass", "grass", "grass", "grass", "grass", "grass", "grass"},
				{"grass", "grass", "grass", "grass", "grass", "grass", "grass", "grass", "tree", "grass", "grass"},
				{"grass", "grass", "tree", "grass", "grass", "grass", "grass", "grass", "tree", "grass", "grass"}
		};
		firstArea = new Level(areaMap, mapElements);
		
		//load and start music
		music = new Music("res/music.wav");
		music.loop();
		music.pause();
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException{
		float camX = hero.getCenteredCameraX();
		float camY = hero.getCenteredCameraY();
	
		firstArea.draw(g, camX, camY);
		
		for(Projectile bullet : bullets){
			bullet.draw(g, camX, camY);
		}
		
		for(Iterator<Character> it = characters.iterator(); it.hasNext();){
			Character character = it.next();
			character.draw(g, camX, camY);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int d)
			throws SlickException{
		runningTime += d;
		
		for(Iterator<Projectile> it = bullets.iterator(); it.hasNext();){
			Projectile bullet = it.next();
			bullet.update(d, firstArea, characters);
			if(bullet.isOut(firstArea)) it.remove();
		}
		for(Iterator<Character> it = characters.iterator(); it.hasNext();){
			Character character = it.next();
			character.update(input, d, firstArea, characters, bullets, runningTime);
		}
		if(goToMenu){
			goToMenu = false;
			sbg.enterState(Game.MENU);
		}
	}

	@Override
	public int getID(){
		return Game.PLAY;
	}
	
	@Override
	public void keyPressed(int key, char c){
		if(key == Input.KEY_ESCAPE){
			goToMenu = true;
		}
	}
	
	public static long getTime(){
		return runningTime;
	}
	
	
}
