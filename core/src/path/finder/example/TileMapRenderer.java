package path.finder.example;

import com.badlogic.gdx.graphics.Color;

public class TileMapRenderer {
    public TileMap tileMap;

    public TileMapRenderer(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public void render() {
        for (Tile tile : tileMap.array) {
            if (!tile.explored && !tile.reached) Draw.batch.setColor(Color.WHITE);
            if (tile.explored) Draw.batch.setColor(Color.BLUE);
            Draw.batch.draw(
                    tile.type.sprite,
                    tile.position.x * tile.type.size,
                    tile.position.y * tile.type.size,
                    tile.type.size,
                    tile.type.size
            );
        }
    }
}
