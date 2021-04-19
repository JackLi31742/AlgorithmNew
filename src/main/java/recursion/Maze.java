package recursion;

public class Maze {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][]maze={{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
		int[]start={0,4};
		int[]destination={3,2};
		Maze maze2=new Maze();
		System.out.println(maze2.hasPath(maze, start, destination));;
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * lintcode 787. The Maze
	 * 在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚上，下，左或右移动，
			但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。
		给定球的起始位置，目的地和迷宫，确定球是否可以停在终点。
		迷宫由二维数组表示。1表示墙和0表示空的空间。你可以假设迷宫的边界都是墙。开始和目标坐标用行和列索引表示。
	 *
	 */
	//	不需要额外的数组，改变了自身的数组
	
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		if (maze==null||start[0]>=maze.length||start[1]>=maze[0].length
				||destination[0]>=maze.length||destination[1]>=maze[0].length) {
			return false;
		}
		
//		return hasPath(maze, destination, start[0], start[1]);
		
		
		boolean[][] visited=new boolean[maze.length][maze[0].length];
		boolean result= dfs(maze, start, destination, visited);
		
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				System.out.print(visited[i][j]+"\t");
			}
			System.out.println();
		}
		return result;
	}
	/**
	 * 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
		在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
		
		这个方法是错的
	 * @param maze
	 * @param destination
	 * @param i 行标
	 * @param j 列标
	 * @return
	 */
	public boolean hasPath(int[][] maze, int[] destination,int i,int j) {
		//边界
		if (i>=maze.length||j>=maze[0].length
				||i<0||j<0||maze[i][j]==1||maze[destination[0]][destination[1]]==1) {
			return false;
		}
		//退出条件，由于直接就true，所以最后destination不会置为2
		if (i==destination[0]&&j==destination[1]) {
			return true;
		}
		//如果该点没有走过
		if (maze[i][j]==0) {
			//假定可以走
			maze[i][j]=2;
			if (hasPath(maze, destination, i+1, j)) {//下
				return true;
			}else if (hasPath(maze, destination, i, j+1)) {//右
				return true;
			}else if (hasPath(maze, destination, i-1, j)) {//上
				return true;
			}else if (hasPath(maze, destination,i,j-1)) {//左
				return true;
			}else {
				maze[i][j]=3;
				return false;
			}
		}else {
			return false;
		}
		
	}
	/**
	 * 以下算法，超时
	 */
	int[] deltaX = new int[] {0, 0, -1, 1};
    int[] deltaY = new int[] {1, -1, 0, 0};
	 private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited){
	        if(start[0] == destination[0] && start[1] == destination[1]){
	            return true;
	        }
	        
	        int x = start[0], y = start[1];
	        
	        if(!isValid(x, y, maze) || visited[x][y]){
	            return false;
	        }
	        
	        visited[x][y] = true;
	        for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[0].length; j++) {
					System.out.print(visited[i][j]+"\t");
				}
				System.out.println();
			}
	        System.out.println("==================");
	        
	        for(int i = 0; i < 4; i++){
	            int xx = x;
	            int yy = y;
	            
	            while(isValid(xx + deltaX[i], yy + deltaY[i], maze)){
	                xx += deltaX[i];
	                yy += deltaY[i];
	            }
	            if(dfs(maze, new int[]{xx, yy}, destination, visited)){
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    private boolean isValid(int x, int y, int[][] maze){
	        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
	    }

}
