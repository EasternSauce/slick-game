package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	boolean goToPlay;
	private Input input;
	
	public Menu(int state){}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		goToPlay = false;
		input = new Input(Game.HEIGHT);
		
		this.setInput(input);
		input.disableKeyRepeat();
		input.addListener(this);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException{
		g.setColor(Color.white);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.black);
		g.drawString("Welcome to the GAME!", 200, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int d)
			throws SlickException{
		if(goToPlay){
			goToPlay = false;
			sbg.enterState(Game.PLAY);
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			goToPlay = true;
		}
	}

	@Override
	public int getID(){
		return Game.MENU;
	}
	
	@Override
	public void keyPressed(int key, char c){
		if(key == Input.KEY_ESCAPE){
			goToPlay = true;
		}
	}
	
}
