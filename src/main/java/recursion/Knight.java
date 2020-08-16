package recursion;

public class Knight {
	public static void main(String[] args) {
		int n=6;
		int[][]board=new int[n][n];
		
		long start = System.currentTimeMillis();
		KnightTour(board, 2, 1, n,2,1);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]+"\t");
			}
			System.out.println();
		}
	}

	public static void KnightTour(int[][]board,int x,int y,int n,int startX,int startY) {
		int[] xarr= {1,2,2,1,-1,-2,-2,-1};
		int[] yarr= {-2,-1,1,2,2,1,-1,-2};
		int step=0;
		boolean[][]visited=new boolean[n][n];
		board[x][y]=0;
		visited[x][y]=true;
		KnightTour(board, x, y, visited, step, xarr, yarr, n,startX,startY);
		
	}
	
	public static boolean KnightTour(int[][]board,int x,int y,
			boolean[][]visited,int step,int[] xarr,int[] yarr,int n,int startX,int startY) {
		if (step==(n*n)-1) {
			
			for (int i = 0; i < xarr.length; i++) {
				int nextX=x+xarr[i];
				int nextY=y+yarr[i];
				if (nextX==startX&&nextY==startY) {
					
					return true;
				}
			}
			return false;
		}
		for (int i = 0; i < xarr.length; i++) {
			int nextX=x+xarr[i];
			int nextY=y+yarr[i];
			if (check(nextX, nextY, visited, n)) {
				//这里只是检查了是否合法，就将值改变，但其实不知道能不能走得完
				board[nextX][nextY]=step+1;
				visited[nextX][nextY]=true;
				if (KnightTour(board, nextX, nextY, visited,step+1,xarr,yarr,n,startX,startY)) {
					return true;
				}else {
					board[nextX][nextY]=0;//走不通，回溯
					visited[nextX][nextY]=false;
				}
				
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


