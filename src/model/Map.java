package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Han on 2016-11-14.
 */
public class Map {
	private final int x_size;
	private final int y_size;

	/**
	 * 0 = path
	 * -1 = obstacle
	 */
	private AStarNode[][] map;
	private AStarNode startNode;
	private AStarNode endNode;

	public Map(final int x_size, final int y_size) {
		this.x_size = x_size;
		this.y_size = y_size;

		map = new AStarNode[x_size][y_size];

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				map[i][j] = new AStarNode(i, j);
	}

	public void setStartNode(int x, int y) {
		if (x > x_size || y > y_size) throw new IllegalArgumentException("x, y is Out Of Range");
		 startNode = this.map[x][y];
	}

	public AStarNode getStartNode() {
		return startNode;
	}

	public void setEndNode(int x, int y) {
		if (x > x_size || y > y_size) throw new IllegalArgumentException("x, y is Out Of Range");
		this.endNode = map[x][y];
	}

	public AStarNode getEndNode() {
		return endNode;
	}

	public void setObstacle(int x, int y) {
		map[x][y].setObstacle(true);
	}

	/**
	 * 0 보다 작은 x, y 를 가지는 Node 는 list 에 추가하면 안된다.
	 * maxLength 보다 큰 x, y 를 가지는 Node 는 list 에 추가하면 안된다.
	 * Obstacle Node 는 list 에 추가하면 안된다.
	 * */
	public List<AStarNode> getChildNodes(AStarNode parentNode) {
		List<AStarNode> childNodes = new ArrayList<>();

		int x = parentNode.getX();
		int y = parentNode.getY();

		if (x - 1 >= 0 && y - 1 >= 0) childNodes.add(map[x - 1][y - 1]);        // x - 1, y - 1
		if (x - 1 >= 0) childNodes.add(map[x - 1][y]);                          // x - 1, y
		if (x - 1 >= 0 && y + 1 < y_size) childNodes.add(map[x - 1][y + 1]);    // x - 1, y + 1

		if (y - 1 >= 0) childNodes.add(map[x][y - 1]);                          // x, y - 1
		if (y + 1 < y_size) childNodes.add(map[x][y + 1]);                      // x, y + 1

		if (x + 1 < x_size && y - 1 >= 0) childNodes.add(map[x + 1][y - 1]);    // x + 1, y - 1
		if (x + 1 < x_size) childNodes.add(map[x + 1][y]);                      // x + 1, y
		if (x + 1 < x_size && y + 1 < y_size) childNodes.add(map[x + 1][y + 1]);// x + 1, y + 1

		for (int i = 0; i < childNodes.size(); i++) {
			AStarNode node = childNodes.get(i);
			if (node.isObstacle()) childNodes.remove(node);
		}

		return childNodes;
	}
}
