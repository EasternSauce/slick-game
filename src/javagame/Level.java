package javagame;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;

public class Level{
	private ArrayList<Tile> tiles;
	private int width;
	private int height;
	private ArrayList<Rect> solidObjects;
	
	public Level(String[][] areaMap, HashMap<String, MapElement> props){
		tiles = new ArrayList<Tile>();
		solidObjects = new ArrayList<Rect>();
		
		width = areaMap[0].length;
		height = areaMap.length;

		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				Tile tile = new Tile(props.get(areaMap[i][j]).getImage(), j, i);
				if(!props.get(areaMap[i][j]).isPassable()){
					Rect boundBox = props.get(areaMap[i][j]).getBoundBox();
					solidObjects.add(new Rect(j * Game.TILE_SIZE + boundBox.x,
							i * Game.TILE_SIZE + boundBox.y, boundBox.w, boundBox.h));
				}
				tiles.add(tile);
			}
		}
	}
	
	public void draw(Graphics g, float camX, float camY){
		for(Tile tile : tiles){
			tile.draw(g, camX, camY);
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public ArrayList<Rect> getSolidObjects(){
		return solidObjects;
	}
	
}
