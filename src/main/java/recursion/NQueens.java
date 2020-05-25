package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.12. 八皇后
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
 * 这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。

 * @author LANG
 *
 */
public class NQueens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NQueens nQueens=new NQueens();
//		nQueens.max=8;
//		nQueens.list=new ArrayList<Integer>(8);
//		System.out.println(nQueens.list);
//		nQueens.check(0);
//		System.out.println(nQueens.count);
		
		List<Integer> list=new ArrayList<>(8);
		System.out.println(list);
		
		int []arr=new int[8];
		for (int i = 0; i < arr.length; i++) {
			
			System.out.println(i+":"+arr[i]);
		}
		
	}
	
	/**
	 * 51. N皇后
	 * @param n
	 * @return
	 */
	int count=0;
	int max=0;
	List<Integer> list=null;
//	public List<List<String>> solveNQueens(int n) {
//		max=n;
//		list=new ArrayList<Integer>(max);
//		check(n);
//		
//	}
	
	public void check(int n){
		if (n==max) {
			count++;
			return ;
		}
		
		for (int i = 0; i < max; i++) {
			
			list.set(n, i);
			if (judge(n)) {
				check(n+1);
			}
		}
		
	}
	
	public boolean judge(int n){
		for (int i = 0; i < n; i++) {
			if (list.get(i)==list.get(n)||Math.abs(n-i)==Math.abs(list.get(i)-list.get(n))) {
				return false;
			}
		}
		
		return true;
	}

}
