package bfs;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 如果求最短路径和以及有几条路线，用dp
	和递归中骑士周游问题很像
 *
 */
public class Knight {
	
	public static void main(String[] args) {
		boolean[][] b= {
					{false,false,false},
					{false,false,false},
					{false,false,false}
				
				};
		
		Point source=new Point(2,0);
		Point destination=new Point(2,2);
		shortestPath(b, source, destination);
	}

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
	public static int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
		
		if (grid==null||grid.length==0||grid[0].length==0) {
			return -1;
		}
		
		Queue<Point> queue=new LinkedList<Point>();
		
		int row=grid.length;
		int col=grid[0].length;
		
		//和图不一样，图中的矩阵中的坐标是边，这里的矩阵每个坐标都是点
//		boolean[][] visited=new boolean[row][col];
		
		int[] xArr= {1,1,-1,-1,2,2,-2,-2};
		int[] yArr= {2,-2,2,-2,1,-1,1,-1};
		
		int count=0;
		
		queue.add(source);
		
			
		while(!queue.isEmpty()) {
		
			//由于每一层count加1，所以需要遍历每一层，所以用size，
			//而不是每一个节点poll出来的时候加1
			int size=queue.size();
			for (int i = 0; i < size; i++) {
				
				Point point=queue.poll();
				
				if (point.x==destination.x&&point.y==destination.y) {
					return count;
				}
//				int x=point.x;
//				int y=point.y;
				//在这里设置容易超时，需要在加入queue的时候就设置
//				grid[point.x][point.y]=true;
				
				for (int j = 0; j < 8; j++) {
					
//					int nextX=point.x+xArr[j];
//					int nextY=point.x+yArr[j];
					Point temp = new Point(point.x+xArr[j],point.y+yArr[j]);
					
//					if (temp.x==destination.x&&temp.y==destination.y) {
//						return count+1;
//					}
					
					if (check(temp, grid, row, col)) {
						
						queue.add(temp);
						grid[temp.x][temp.y]=true;
					}
				}
			}
			
			count++;
		}
		
		return -1;
    }
	
	
	public static boolean check(Point p,boolean[][]grid,int row,int col) {
		if (p.x>=0&&p.x<row&&p.y>=0&&p.y<col&&!grid[p.x][p.y]) {
			return true;
		}else {
			return false;
		}
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
		
		if (grid==null || grid.length==0 ||grid[0].length==0) {
			return -1;
		}
		
		int n=grid.length;
		int m=grid[0].length;
		
		int[] xArr= {1,-1,2,-2};
		int[] yArr= {2,2,1,1};
		int count=0;
		
		Queue<int[]> queue=new LinkedList<int[]>();
		
		queue.add(new int[] {0,0});
		
		while(!queue.isEmpty()) {
			
			int size=queue.size();
			
			for (int i = 0; i < size; i++) {
				
				int[] point=queue.poll();
				
				if (point[0]==n-1&&point[1]==m-1) {
					return count;
				}
				
				for (int j = 0; j < 4; j++) {
					int nextX=point[0]+xArr[j];
					int nextY=point[1]+yArr[j];
					
					if (check(nextX,nextY, grid, n, m)) {
						queue.add(new int[] {nextX,nextY});
						grid[nextX][nextY]=true;
					}
				}
				
			}
			count++;
		}
		
		return -1;
    }
	
	
	public boolean check(int x ,int y,boolean[][]grid,int row,int col) {
		if (x>=0&&x<row&&y>=0&&y<col&&!grid[x][y]) {
			return true;
		}else {
			return false;
		}
	}
}


class Point {
	public int x;
	public int y;

	public Point() {
		x = 0;
		y = 0;
	}

	public Point(int a, int b) {
		x = a;
		y = b;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
