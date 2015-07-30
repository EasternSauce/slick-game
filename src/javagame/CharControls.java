package javagame;

public class CharControls{
	private int goLeft;
	private int goRight;
	private int goUp;
	private int goDown;
	private int shoot;
	
	public CharControls(int goLeft, int goRight, int goUp, int goDown, int shoot){
		this.goLeft = goLeft;
		this.goRight = goRight;
		this.goUp = goUp;
		this.goDown = goDown;
		this.shoot = shoot;
	}

	public int getGoLeft(){
		return goLeft;
	}

	public int getGoRight(){
		return goRight;
	}

	public int getGoUp(){
		return goUp;
	}

	public int getGoDown(){
		return goDown;
	}

	public int getShoot(){
		return shoot;
	}
}
