package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import game.Game;
import level.Prop;
import level.Sprite;

public class Loader{
	public static Image loadImage(Image sheet, int x, int y){
		return sheet.getSubImage(x * Game.TILE_SIZE, y * Game.TILE_SIZE,
				Game.TILE_SIZE, Game.TILE_SIZE);
	}
	
	public static String[][] loadTerrainMap(String fileName) throws IOException{
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = reader.readLine()) != null){
			String[] parts = line.split(" ");
			list.add(parts);
		}
		
		String area[][] = new String[list.size()][];
		
		for(int i = 0; i < list.size(); i++){
			area[i] = list.get(i);
		}
		
		reader.close();
		
		return area;
	}
	
	public static ArrayList<Prop> loadProps(String fileName, HashMap<String, Sprite> sprites) throws NumberFormatException, IOException{
		ArrayList<Prop> props = new ArrayList<Prop>();
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = reader.readLine()) != null){	
			String[] parts = line.split(" ");
			Prop prop = new Prop(sprites.get(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
			props.add(prop);
		}
		
		reader.close();
		
		return props;
	}
	
	public static HashMap<String, Sprite> loadSprites(String fileName, Image sheet) throws IOException{
		HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = reader.readLine()) != null){
			String[] parts = line.split(" ");

			Rectangle rect = new Rectangle(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
					Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
			Image image = Loader.loadImage(sheet, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
			Sprite sprite = new Sprite(image, rect);
			sprites.put(parts[0], sprite);
		}
		
		reader.close();
		
		return sprites;
	}
}

