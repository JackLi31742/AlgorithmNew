package dp.index;

public class ShortestPath {

	/**
	 * 630. 骑士的最短路径II
		在一个 n * m 的棋盘中(二维矩阵中 0 表示空 1 表示有障碍物)，
		骑士的初始位置是 (0, 0) ，他想要达到 (n - 1, m - 1) 这个位置，
		骑士只能从左边走到右边。找出骑士到目标位置所需要走的最短路径并返回其长度，
		如果骑士无法达到则返回 -1.
		
		如果骑士所在位置为(x,y)，那么他的下一步可以到达以下位置:

		向右走
		(x + 1, y + 2)
		(x - 1, y + 2)
		(x + 2, y + 1)
		(x - 2, y + 1)
	 * @param grid
	 * @return
	 */
	public int shortestPath2(boolean[][] grid) {
        // write your code here
		if (grid==null||grid.length==0||grid[0].length==0) {
			return -1;
		}
		
		int n=grid.length;
		int m=grid[0].length;
		//从（0,0）到(i,j)的最短距离
		int[][]dp=new int[n][m];
		
		int[] xArr= {1,-1,2,-2};
		int[] yArr= {2,2,1,1};
		
		dp[0][0]=0;
		
		for (int j = 0; j < m; j++) {
			
			for (int i = 0; i < n; i++) {
				
				if (grid[i][j]==true) {
					
					
				}
				
			}
			
		}
    }
	
	public boolean check(boolean[][] grid, int n ,int m,int x ,int y) {
			
		if (x<0||x>=n||y<0||y>=m||grid[x][y]==false) {
			return false;
		}
		
		return true;
	}
}
