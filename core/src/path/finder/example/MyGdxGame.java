package path.finder.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	private TileTypes tileTypes = new TileTypes();
	private TileMap map;
	private TileMapRenderer mapRenderer;
	private OrthographicCamera camera;

	private Texture goalTexture;
	private Texture startTexture;

	private Tile goal;
	private Tile start;
	Texture pathTexture;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();

		Draw.batch = new SpriteBatch();

		tileTypes.load();
		map = new TileMap(10, 10);
		mapRenderer = new TileMapRenderer(map);

		goalTexture = new Texture("goal.png");
		goal = map.get(7,5);

		startTexture = new Texture("start.png");
		start = map.get(3,5);

		map.get(5,5).type = TileTypes.noReachable;
		map.get(5,6).type = TileTypes.noReachable;
		map.get(5,4).type = TileTypes.noReachable;

		path = findPath(start, goal);

		pathTexture = new Texture("path.png");
	}

	ArrayList<Tile> path;

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Draw.batch.begin();
		mapRenderer.render();

		drawAtTile(goal, goalTexture);
		drawAtTile(start,startTexture);

		for (Tile tile : path) {
			drawAtTile(tile, pathTexture);
		}

		Draw.batch.end();
	}

	public void drawAtTile(Tile tile, Texture texture) {
		Draw.batch.draw(
				texture,
				tile.position.x * tile.type.size,
				tile.position.y * tile.type.size,
				tile.type.size,
				tile.type.size
		);
	}

	public ArrayList<Tile> findPath(Tile start, Tile goal) {
		ArrayList<Tile> reachable = new ArrayList<>();
		ArrayList<Tile> explored = new ArrayList<>();

		reachable.add(start);

		while (reachable.size() != 0) {
			Tile tile = reachable.get(0);

			if (tile == goal) {
				ArrayList<Tile> path = new ArrayList<Tile>();

				while (tile != null) {
					path.add(tile);
					tile = tile.previous;
				}

				return path;
			}

			reachable.remove(tile);
			explored.add(tile);
			tile.explored = true;

			ArrayList<Tile> newReachable = new ArrayList<>(getTiles(tile));
			newReachable.removeAll(explored);

			for (Tile adjacent : newReachable) {
				if (!reachable.contains(adjacent)) {
					adjacent.previous = tile;
					reachable.add(adjacent);
				}
			}
		}

		return null;
	}

	public ArrayList<Tile> getTiles(Tile tile) {
		ArrayList<Tile> tiles = new ArrayList<>();

		Tile nearestTile;

		nearestTile = getTile(tile,1,0);
		if (nearestTile != null) tiles.add(nearestTile);

		nearestTile = getTile(tile,-1,0);
		if (nearestTile != null) tiles.add(nearestTile);

		nearestTile = getTile(tile,0,1);
		if (nearestTile != null) tiles.add(nearestTile);

		nearestTile = getTile(tile,0,-1);
		if (nearestTile != null) tiles.add(nearestTile);

		return tiles;
	}

	public Tile getTile(Tile tile, int x, int y) {
		if (map.inBounds(new Vector2(tile.position.x + x, tile.position.y + y))) {
			if (map.get((int) tile.position.x + x, (int) tile.position.y + y).type.reachable) {
				return (map.get((int) tile.position.x + x, (int) tile.position.y + y));
			}
		}

		return null;
	}
}
