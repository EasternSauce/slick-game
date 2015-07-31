package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Image;

import game.Game;

public class Loader{
	public static Image loadImage(Image sheet, int x, int y){
		return sheet.getSubImage(x * Game.TILE_SIZE, y * Game.TILE_SIZE,
				Game.TILE_SIZE, Game.TILE_SIZE);
	}
	
	public static String[][] loadAreaMap(String fileName) throws IOException{
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
}

