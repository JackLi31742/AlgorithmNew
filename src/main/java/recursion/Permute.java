package recursion;

import java.util.ArrayList;
import java.util.List;

public class Permute {
	
	public static void main(String[] args) {
		
		int[] nums= {1,2,3};
		
		Permute permute=new Permute();
		System.out.println(permute.permute(nums));;
	}

	/**
	 * 46. 全排列
	 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
		
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		if (nums==null) {
			return result;
		}
		List<Integer> path=new ArrayList<Integer>();
		boolean[] visited=new boolean[nums.length];
		return permute(nums,result,1,path,visited);
//		return result;
    }
	
	/**
	 * 
	 * 
	 */
	public List<List<Integer>> permute(int[] nums,List<List<Integer>> result,
			int count,List<Integer> path,boolean[]visited) {
		
		if (count <= nums.length) {
			for (int i = 0; i < nums.length; i++) {
				if (!visited[i]) {
					count++;
					visited[i] = true;
					path.add(nums[i]);
					permute(nums, result, count, path, visited);
					
					
//					if (count<=nums.length) {
//						
//						permute(nums, result, count, path, visited);
//					}else {
//						result.add(path);
//						/**
//						 * 在这里回退没有用，因为这里是最后一个元素，要在上一个元素开始回退
//						 */
//						visited[i]=false;
//						//不能传nums[i]，会被list当成下标移除
//						path.remove(path.size()-1);
//					}
					
					//回溯
					visited[i]=false;
					//不能传nums[i]，会被list当成下标移除
					path.remove(path.size()-1);
					count--;
				}
			}
		} else {
			//走到这的时候，说明已经path.size() == nums.length
//			if (path.size() == nums.length) {
				//result 的add是引用，所以需要重新创建path的副本
				//跟返回值是void还是List<List<Integer>>没有关系
				result.add(new ArrayList<>(path));
				//这里是否返回也没有关系，因为下边再没有代码了
//				return result;
//			}

		}
		return result;
	}
	
	public void permute2(int[] nums,List<List<Integer>> result,
			int count,List<Integer> path,boolean[]visited) {
		
		if (count>nums.length) {
			result.add(new ArrayList<Integer>(path));
			return ;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!visited[i]) {
				count++;
				visited[i] = true;
				path.add(nums[i]);
				permute2(nums, result, count, path, visited);
				
				//回溯
				visited[i]=false;
				//不能传nums[i]，会被list当成下标移除
				path.remove(path.size()-1);
				count--;
			}
		}
		
	}
	public void permute(int[] nums,List<List<Integer>> result) {
		for (int i = 0; i < nums.length; i++) {
			
			for (int j = 0; j < nums.length; j++) {
				for (int j2 = 0; j2 < nums.length; j2++) {
					List<Integer> list=new ArrayList<Integer>();
					list.add(nums[i]);
					if (!list.contains(nums[j])) {
						list.add(nums[j]);
						if (!list.contains(nums[j2])) {
							
							list.add(nums[j2]);
							result.add(list);
						}
					}
					
				}
			}
		}
	}
	
	
	
	/**
	 * 52. 下一个排列
	 * 给定一个整数数组来表示排列，找出其之后的一个排列。
	 */
	public int[] nextPermutation(int[] nums) {
        // write your code here
    }
	
	
	
	/**
	 * 197. 排列序号
	 * 给出一个不含重复数字的排列，求这些数字的所有排列按字典序排序后该排列的编号。其中，编号从1开始。
	 * @param A
	 * @return
	 */
	public long permutationIndex(int[] A) {
        // write your code here
    }
}
