package recursion;

import java.util.ArrayList;
import java.util.List;

public class Combine {

	public static void main(String[] args) {
		
		System.out.println(combine(4, 3));;
	}
	
	/**
	 * 77. 组合
	 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
	 * @param n
	 * @param k
	 * @return
	 */
	 public static List<List<Integer>> combine(int n, int k) {
		 List<List<Integer>> result=new ArrayList<List<Integer>>();
			if (n==0||k==0) {
				return result;
			}
			List<Integer> path=new ArrayList<Integer>();
			boolean[] visited=new boolean[n];
			int index=1;
			combine(n,k,result,1,path,visited,index);
			return result;
	 }
	 
	 public static void combine(int n, int k,List<List<Integer>> result,
			 int count,List<Integer> path,boolean[] visited,int index) {
		 
		 if (count<=k) {
			
			 for (int i = index; i <= n; i++) {
				 if (!visited[i-1]) {
					 visited[i-1]=true;
					 path.add(i);
					 count++;
					 combine(n, k, result, count, path, visited,i+1);
					 
					 visited[i-1]=false;
					 path.remove(path.size()-1);
					 count--;
				}
			 }
		}else {
			
			result.add(new ArrayList<Integer>(path));
		}
		
	 }
}
