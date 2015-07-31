package level;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Level{
	private int width;
	private int height;
	private ArrayList<Rectangle> solidObjects;
	private ArrayList<Tile> tiles;
	private ArrayList<Prop> props;
	
	
	public Level(String[][] terrainMap, HashMap<String, Sprite> sprites, ArrayList<Prop> props){
		width = terrainMap[0].length;
		height = terrainMap.length;
		tiles = new ArrayList<Tile>();
		solidObjects = new ArrayList<Rectangle>();
		this.props = props;

		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				String terrain = null;
				if(terrainMap[i][j].equals("o")) terrain = "grass";
				if(terrainMap[i][j].equals("t")) terrain = "tree";
				
				Tile tile = new Tile(sprites.get(terrain).getImage(), j, i);
				

				tiles.add(tile);
			}
		}
		
		for(Prop prop : props){
			if(!prop.isPassable()){
				Rectangle boundBox = prop.getSprite().getBoundBox();
				solidObjects.add(new Rectangle(prop.getX() + boundBox.getX(), prop.getY() + boundBox.getY(),
						boundBox.getWidth(), boundBox.getHeight()));
			}
			
		}
	}
	
	public void draw(Graphics g, float camX, float camY){
		for(Tile tile : tiles){
			tile.draw(g, camX, camY);
		}
		
		for(Prop prop : props){
			prop.draw(g, camX, camY);
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public ArrayList<Rectangle> getSolidObjects(){
		return solidObjects;
	}
	
}
