package recursion;

import java.util.ArrayDeque;
import java.util.Queue;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
/**
 * 不能用bfs
 *
 */
public class Knight3 {
	public static void main(String[] args) {
		
		
		int n=6;
		int[][]board=new int[n][n];
		
		long start = System.currentTimeMillis();
		KnightTour(board, 2, 0, n,2,0);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	
	public static boolean KnightTour(int[][]board,int x,int y,int n,int startX,int startY) {
		int[] xarr= {1,2,2,1,-1,-2,-2,-1};
		int[] yarr= {-2,-1,1,2,2,1,-1,-2};
		int step=0;
		boolean[][]visited=new boolean[n][n];
//		board[x][y]=step;
//		visited[x][y]=true;
		Point point=new Point(x, y);
		Queue<Point> queue=new ArrayDeque<Point>();
		queue.add(point);
		while(!queue.isEmpty()){
			Point p=queue.poll();
			board[p.x][p.y]=step;
			visited[p.x][p.y]=true;
			if (step==n*n-1) {
				for (int i = 0; i < xarr.length; i++) {
					int nextX=p.x+xarr[i];
					int nextY=p.y+yarr[i];
					if (nextX==startX&&nextY==startY) {
						
						return true;
					}
				}
				return false;
			}
			for (int i = 0; i < xarr.length; i++) {
				int nextX=p.x+xarr[i];
				int nextY=p.y+yarr[i];
				Point next=new Point(nextX, nextY);
				if (check(next, n, visited)) {
					
					queue.add(next);
				}
			}
			
			step++;
		}
		
		return false;
	}
	
	
	public static boolean check(Point p,int n,boolean[][]visited) {
		if (p.x>=0&&p.x<n&&p.y>=0&&p.y<n&&!visited[p.x][p.y]) {
			return true;
		}else {
			return false;
		}
	}
	

}
