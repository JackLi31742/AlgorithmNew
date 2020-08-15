package recursion;

public class Knight {
	public static void main(String[] args) {
		int n=6;
		int[][]board=new int[n][n];
		
		KnightTour(board, 1, 2, n);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]+"\t");
			}
			System.out.println();
		}
	}

	public static void KnightTour(int[][]board,int x,int y,int n) {
		int[] xarr= {1,2,2,1,-1,-2,-2,-1};
		int[] yarr= {-2,-1,1,2,2,1,-1,-2};
		int step=0;
		boolean[][]visited=new boolean[n][n];
		board[x][y]=0;
		visited[x][y]=true;
		KnightTour(board, x, y, visited, step, xarr, yarr, n);
		
	}
	
	public static boolean KnightTour(int[][]board,int x,int y,
			boolean[][]visited,int step,int[] xarr,int[] yarr,int n) {
		if (step==(n*n)-1) {
			return true;
		}
		for (int i = 0; i < xarr.length; i++) {
			int nextX=x+xarr[i];
			int nextY=y+yarr[i];
			if (check(nextX, nextY, visited, n)) {
				board[nextX][nextY]=step+1;
				visited[nextX][nextY]=true;
				KnightTour(board, nextX, nextY, visited,step+1,xarr,yarr,n);
			}
		}
		
		return false;
	}
	
	public static boolean check(int x,int y,boolean[][]visited,int n) {
		if (x>=0&&x<n&&y>=0&&y<n&&!visited[x][y]) {
			return true;
		}else {
			return false;
		}
	}
}
