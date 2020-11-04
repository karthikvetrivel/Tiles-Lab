// Karthik Vetrivel
// Post-AP CS: Data Structures D block
// November 3rd, 2020
// Tile Application Method Manager.

import java.util.*; 
import java.awt.*; 

public class TileManager {
    private ArrayList<Tile> tileList; 

    // TileManager constructor--initializes tileList.
    public TileManager() {
     tileList = new ArrayList<Tile>(); 
    }

    // Accepts Tile object 'rect' and adds it to the tileList ArrayList. 
    public void addTile(Tile rect) {
        tileList.add(rect);
    }

    // Accepts Graphic paramter 'g' and draws all tiles in tileList ArrayList.
    public void drawAll(Graphics g) {
        for (Tile tile : tileList) {
            tile.draw(g);
        }
    }

    /*
    Moves the top-most selected tile to the end of the tileList.
    Accepts two integer parameters, x and y, which determine which tile will move.
    */
    public void raise(int x, int y) {
        int selectedIndex = findTopTile(x, y);
        if (selectedIndex >= 0) {
            tileList.add(tileList.get(selectedIndex));
            tileList.remove(selectedIndex);
        }
    }

    /*
    Moves the top-most selected tile to the beginning of tileList.
    Accepts two integer parameters, x and y, which determine which tile will move.
    */
    public void lower(int x, int y) {
        int selectedIndex = findTopTile(x, y);

        if (selectedIndex >= 0) {
            tileList.add(0, tileList.get(selectedIndex));
            tileList.remove(selectedIndex + 1);
        }
    }

    /*
    Removes the top-most selected tile from tileList.
    Accepts two integer parameters, x and y, which determine which tile will be removed.
    */
    public void delete(int x, int y) {
        int selectedIndex = findTopTile(x, y);

        if (selectedIndex >= 0) {
            tileList.remove(selectedIndex);
        }
    }

     /*
    Removes all tiles at the selected coordinate from tileList.
    Accepts two integer parameters, x and y, which determine which tile(s) will be removed.
    */
    public void deleteAll(int x, int y) {
        int selectedIndex = findTopTile(x, y);

        // continue removing the top-tile at the given coordinates until there are no tiles remaining.
        while (selectedIndex >= 0) {
            tileList.remove(selectedIndex);
            selectedIndex = findTopTile(x, y); 
        }
        
    }

    /*
    Shuffles the order of tileList. Assigns each Tile in tileList a random x, y coordinate.
    Accepts two integer parameters, width and height, which represent the dimensions of the application
    window. 
    */
    public void shuffle(int width, int height) { 
        Collections.shuffle(tileList);

        for (Tile tile : tileList) {
            tile.setX((int)(Math.random() * (double)(width - tile.getWidth() + 1)));
            tile.setY((int)(Math.random() * (double)(height - tile.getHeight() + 1)));
        }
    }


    /*
    Helper function. Determines the top-most tile at any given coordinate.
    Accepts two integer parameters, x and y, which determine where the top-most tile is located.
    Returns the index in the titeList of top-most tile. 
    */
    private int findTopTile(int x, int y) {
        for (int i = tileList.size() - 1; i >= 0; i--) {
            Tile tile = tileList.get(i); 
            if (tile.getX() <= x && tile.getX() + tile.getWidth() >= x &&
                tile.getY() <= y && tile.getY() + tile.getHeight() >= y) {    
                return i; 
            }
        }
        return -1; 
    }

}