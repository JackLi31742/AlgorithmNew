package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 如果求最短路径和以及有几条路线，用dp
	和递归中骑士周游问题很像
 * @author JackLi
 *
 */
public class MinPath {

	/**
	 * 611. 骑士的最短路线
	 * 给定骑士在棋盘上的 初始 位置(一个2进制矩阵 0 表示空 1 表示有障碍物)，
	 * 找到到达 终点 的最短路线，返回路线的长度。如果骑士不能到达则返回 -1 。
	 * 
	 * 起点跟终点必定为空.
		骑士不能碰到障碍物.
		路径长度指骑士走的步数.
		
		如果骑士的位置为 (x,y)，他下一步可以到达以下这些位置:
		(x + 1, y + 2)
		(x + 1, y - 2)
		(x - 1, y + 2)
		(x - 1, y - 2)
		(x + 2, y + 1)
		(x + 2, y - 1)
		(x - 2, y + 1)
		(x - 2, y - 1)
		
		
	 * @param grid
	 * @param source
	 * @param destination
	 * @return
	 */
	public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
		
		if (grid==null||grid.length==0||grid[0].length==0) {
			return -1;
		}
		
		Queue<Point> queue=new LinkedList<Point>();
		
		int row=grid.length;
		int col=grid[0].length;
		
		boolean[] visited=new boolean[row];
		
		int[]x= {1,1,-1,-1,2,2,-2,-2};
		int[]y= {2,-2,2,-2,1,-1,1,-1};
		
		
    }
	
	
	/**
	 * 630. 骑士的最短路径II
	 * 
	 * 在一个 n * m 的棋盘中(二维矩阵中 0 表示空 1 表示有障碍物)，骑士的初始位置是 (0, 0) ，
	 * 他想要达到 (n - 1, m - 1) 这个位置，骑士只能从左边走到右边。
	 * 找出骑士到目标位置所需要走的最短路径并返回其长度，如果骑士无法达到则返回 -1.
	 * 
		如果骑士所在位置为(x,y)，那么他的下一步一步到达以下位置:

		(x + 1, y + 2)
		(x - 1, y + 2)
		(x + 2, y + 1)
		(x - 2, y + 1)
	 * @param grid
	 * @return
	 */
	public int shortestPath2(boolean[][] grid) {
        // write your code here
    }
}


class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
}
