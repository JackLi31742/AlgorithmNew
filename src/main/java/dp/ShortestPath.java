package dp;

import java.util.ArrayList;
import java.util.List;

public class ShortestPath {
	
	final static int N = 10000;// 表示不可以连接
	
	static List<String> list;
	static int count=0;
	public static void main(String[] args) {
		
		int[][] m = { 
				{ 0, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 3, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 8, 7, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 6, 8, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 3, 5, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 2, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3 } };
		
		
		list=new ArrayList<String>();
		
		list.add("A");
		list.add("B1");
		list.add("B2");
		list.add("C1");
		list.add("C2");
		list.add("C3");
		list.add("C4");
		list.add("D1");
		list.add("D2");
		list.add("D3");
		list.add("E1");
		list.add("E2");
		list.add("E3");
		list.add("F1");
		list.add("F2");
		list.add("G");
		
		
		int matrix[][] = {
			      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
			/*A*/ { N,   5,   7,   N,   N,   N,   2},
			/*B*/ { 5,   N,   N,   9,   N,   N,   3},
			/*C*/ { 7,   N,   N,   N,   8,   N,   N},
			/*D*/ { N,   9,   N,   N,   N,   4,   N},
			/*E*/ { N,   N,   8,   N,   N,   5,   4},
			/*F*/ { N,   N,   N,   4,   5,   N,   6},
			/*G*/ { 2,   3,   N,   N,   4,   6,   N}}; 

		        System.out.println(minPath(m));;
		        
		        System.out.println(count);
		        
		        
		        
		        int[][]grid= {
		                       {1,3,1},
		                       {1,5,1},
		                       {4,2,1}
		                     };
		        
		        
		        System.out.println(minPathSum(grid));;
	}
	/***
	 * 图的最短路径
	 * 从后向前递推，从后向前决策
	 * 这种解法应该不是动态规划，拉勾上边讲错了
	 * @param m
	 */
	public static int minPath(int[][] m) {
		
		return minPath(m, m[0].length-1);
	}
	
	/**
	 * 
	 * @param m
	 * @param end 到达的节点
	 * @return distance的返回值给了minPath，加上m[i][end]赋值给了tempDis
	 */
	public static int minPath(int[][] m,int end) {
		if (end==0) {
			return 0;
		}
		
		int distance=N;
		
		//由于每一轮都要找到前一轮，所以i不能是小于m[0].length，那样的话，之前的决策就没用了
		for (int i = 0; i < end; i++) {
			if (m[i][end]!=0) {
				count++;
				System.out.println(list.get(i)+"到"+list.get(end)+"的矩阵距离是："+m[i][end]);
				int tempDis=m[i][end]+minPath(m,i);
				//递归回溯相加后，就不是i到end的距离了，是一直累加的距离
				System.out.println("经过"+list.get(i)+"到"+list.get(end)+"的距离是："+tempDis);
				if (tempDis<distance) {
					distance=tempDis;
				}
			}
		}
		
		return distance;
	} 
	
	
	/**
	 * 64. 最小路径和
	 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
	 * @param grid
	 * @return
	 */
	public static int minPathSum(int[][] grid) {

		if (grid==null||grid.length<=0||grid[0].length<=0) {
			return -1;
		}
		
		int m=grid.length;
		int n=grid[0].length;
		
		return minPathSum2(grid, 0, 0, m-1, n-1);
    }
	
	public static int minPathSum(int[][] grid,int startX,int StartY,int endX,int endY) {

		int[][] distance=new int[grid.length][grid[0].length];
		for (int i = startX; i <= endX; i++) {
			for (int j = StartY; j <= endY; j++) {
				int left=0,top=0;
				if ((i-1)<0&&(j-1)<0) {
					distance[i][j]=grid[i][j];
				}else {
					
					if ((i-1)<0) {
						top=Integer.MAX_VALUE;
					}else {
						top=distance[i-1][j];
					}
					if ((j-1)<0) {
						left=Integer.MAX_VALUE;
					}else {
						left=distance[i][j-1];
					}
					
					distance[i][j]=Math.min(left, top)+grid[i][j];
				}
			}
		}
		
		return distance[endX][endY];
    }
	
	
	public static int minPathSum2(int[][] grid,int startX,int StartY,int endX,int endY) {

		int[][] distance=new int[grid.length][grid[0].length];
		
		//将边界进行单独处理，省去了在循环中进行判断
		distance[0][0]=grid[0][0];
		
		for (int i = startX+1; i <= endX; i++) {
			distance[i][0]=distance[i-1][0]+grid[i][0];
		}
		
		for (int j = StartY+1; j <= endY; j++) {
			distance[0][j]=distance[0][j-1]+grid[0][j];
		}
		
		for (int i = startX+1; i <= endX; i++) {
			for (int j = StartY+1; j <= endY; j++) {
				
				
				int top=distance[i-1][j];
			
				int left=distance[i][j-1];
					
					
				distance[i][j]=Math.min(left, top)+grid[i][j];
				
			}
		}
		
		return distance[endX][endY];
    }
}
