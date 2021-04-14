package path.finder.example;

import com.badlogic.gdx.math.Vector2;

public class Tile {
    public Vector2 position;
    public TileType type = TileTypes.reachable;
    public boolean explored;
    public boolean reached;
    public Tile previous; //TODO get run away this field

    public Tile(int x, int y) {
        this.position = new Vector2(x, y);
    }
}
