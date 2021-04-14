package path.finder.example;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TileTypes {
    public static TileType reachable, noReachable;

    public void load() {
        reachable = new TileType() {
            {
                reachable = true;
                sprite = new Sprite(new Texture("reachable.png"));
                size = 32;
            }
        };

        noReachable = new TileType() {
            {
                reachable = false;
                sprite = new Sprite(new Texture("noReachable.png"));
                size = 32;
            }
        };
    }
}
