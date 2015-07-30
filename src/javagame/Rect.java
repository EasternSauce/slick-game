package javagame;

public class Rect{
	public int x;
	public int y;
	public int w;
	public int h;
	
	public Rect(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public static boolean areColliding(Rect rect1, Rect rect2){

		if(rect2.x >= rect1.x + rect1.w || rect2.x + rect2.w <= rect1.x ||
				rect2.y >= rect1.y + rect1.h || rect2.y + rect2.h <= rect1.y){
			return false;
		}
		return true;
	}
}
