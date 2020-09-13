package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NSum {
	public static void main(String[] args) {
		int[] nums= {-1,0,1,2,-1,-4};
		System.out.println(Arrays.toString(twoSum(nums, 9)));;
		
		
		System.out.println(threeSum3(nums));
	}
	
	/**
	 * 1. 两数之和
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

		你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
        	//遍历到后边的时候，如果有值等于target-nums[i]，那肯定已经在map中了
			if (map.get(target-nums[i])==null) {
				map.put(nums[i], i);
			}else {
				return new int[] {map.get(target-nums[i]),i};
			}
		}
        return null;
    }

	/**
	 * 15. 三数之和
	 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
	 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。


	我们枚举的三元组 (a,b,c) 满足 a≤b≤c，保证了只有 (a, b, c) 这个顺序会被枚举到，而 (b,a,c)、(c,b,a) 等等这些不会，这样就减少了重复。

	同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {

		List<List<Integer>> result=new ArrayList<List<Integer>>();
		
		if (nums==null||nums.length<3) {
			return result;
		}
		
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i==0||nums[i]>nums[i-1]) {
				
				for (int j = i+1; j < nums.length; j++) {
					if (j==i+1||nums[j]>nums[j-1]) {
						
						for (int k = j+1; k < nums.length; k++) {
							if (k==j+1||nums[k]>nums[k-1]) {
								
								List<Integer> list=new ArrayList<>();
								if (nums[i]+nums[j]+nums[k]==0) {
									list.add(nums[i]);
									list.add(nums[j]);
									list.add(nums[k]);
								}
								if (list.size()>0) {
									
									result.add(list);
								}
							}
						}
					}
				}
			}
		}
		
		return result;
    }
	
	/**
	 * 双指针
	 * 当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法
	 * 将枚举的时间复杂度从 O(N^2) 减少至 O(N)。为什么是 O(N) 呢？这是因为在枚举的过程每一步中，
	 * 「左指针」会向右移动一个位置（也就是题目中的 b），而「右指针」会向左移动若干个位置，
	 * 这个与数组的元素有关，但我们知道它一共会移动的位置数为 O(N)，均摊下来，每次也向左移动一个位置，因此时间复杂度为 O(N)。

	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum2(int[] nums) {
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		
		if (nums==null||nums.length<3) {
			return result;
		}
		
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i==0||nums[i]>nums[i-1]) {
				//
				int j=i+1;
				int k=nums.length-1;
				while(j<nums.length&&k>0&&j<k) {
					if (j==i+1||nums[j]>nums[j-1]) {
						
						//由于k在第一个while的外边，k不会被重置为nums.length-1，会接着上一次的继续递减
						//所以形成了双指针
						//同时，必须要大于0，否则k容易走到j的左边
						while(k>0&&j<k&&nums[i]+nums[j]+nums[k]>0) {
							k--;
						}
						if (j==k) {
							break;
						}
						if (nums[i]+nums[j]+nums[k]==0) {
							List<Integer> list=new ArrayList<>();
							list.add(nums[i]);
							list.add(nums[j]);
							list.add(nums[k]);
							result.add(list);
						}
					}
					j++;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 用hash 去重太麻烦了
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum3(int[] nums) {
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		
		if (nums==null||nums.length<3) {
			return result;
		}
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			
			int target=-nums[i];
			for (int j = i+1; j < nums.length; j++) {
				
					
					if (map.containsKey(target-nums[j])&&
							map.get(target-nums[j])!=j&&map.get(target-nums[j])!=i) {
						List<Integer> list=new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[map.get(target-nums[j])]);
						result.add(list);
					}else {
						map.put(nums[j], j);
					}
				
			}
		}
		
		return result;
	}
	
	
}
