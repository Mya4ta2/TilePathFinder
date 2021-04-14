package path.finder.example;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;

public class TileMap {
    public final Tile[] array;
    public final int width, heigth;

    public TileMap(int width, int hegiht) {
        this.array = new Tile[width * hegiht];
        this.heigth = hegiht;
        this.width = width;

        fill();
    }

    public void fill(){
        for (int i = 0; i < array.length; i++){
            array[i] = new Tile(i % width, i / width);
        }
    }

    public Tile get(int x, int y) {
        return array[y * width + x];
    }

    public Tile get(Vector2 vector) {
        return get((int) vector.x, (int) vector.y);
    }

    public boolean inBounds(Vector2 position) {
        if (position.x < 0 || position.y < 0) return false;
        if (position.x > width-1 || position.y > heigth-1) return false;

        return true;
    }
}
