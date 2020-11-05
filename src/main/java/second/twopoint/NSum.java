package second.twopoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NSum {
	public static void main(String[] args) {
		int[] nums= {-1,0,1};
		System.out.println(twoSum5(nums, 0));;
		
		
//		System.out.println(threeSum3(nums));
	}
	
	/**
	 * 1. 两数之和
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

		你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

		由于要返回下标，所以用哈希的方法更好，否则可以先对数组进行排序，再用双指针
		判断nums[left]+nums[right]>target，那么说明需要right--，因为nums[right]加上最小值还要比target大，所以right下标
		的值肯定是不符合要求的，
		
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
        	//遍历到后边的时候，如果有值等于target-nums[i]，那肯定已经在map中了
        	//否则就是还没有遍历到，继续遍历即可
			if (map.get(target-nums[i])==null) {
				map.put(nums[i], i);
			}else {
				return new int[] {map.get(target-nums[i]),i};
			}
		}
        return null;
    }
	
	/**
	 * lintcode 609. 两数和-小于或等于目标值
	 * 给定一个整数数组，找出这个数组中有多少对的和是小于或等于目标值。返回对数
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int twoSum5(int[] nums, int target) {
        if (nums==null||nums.length<2) {
			return 0;
		}
        
		Arrays.sort(nums);
		
		int count=0;
		
		for (int i = 0; i < nums.length; i++) {
			for (int j = nums.length-1; j >i; j--) {
				if (nums[i]+nums[j]<=target) {
					count++;
				}
			}
		}
		
		return count;
    }

	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int twoSum52(int[] nums, int target) {
		
		if (nums==null||nums.length<2) {
			return 0;
		}
        
		Arrays.sort(nums);
		
		int count=0;
		
		int left=0;
		int right=nums.length-1;
		while(left<right) {
			while (left<right&&nums[left]+nums[right]>target) {
				right--;
			}
			count+=right-left;
			left++;
		}
		
		
		return count;
	}
	
	
	/**
	 * 15. 三数之和
	 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
	 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。


	我们枚举的三元组 (a,b,c) 满足 a≤b≤c，保证了只有 (a, b, c) 这个顺序会被枚举到，
	 而 (b,a,c)、(c,b,a) 等等这些不会，这样就减少了重复。

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
			//if(i!=0&&nums[i]==nums[i-1])
//				continue
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
	
	/**
	 * lintcode 382. 三角形计数
	 * 给定一个整数数组，在该数组中，寻找三个数，分别代表三角形三条边的长度，问，可以寻找到多少组这样的三个数来组成三角形？
	 * 排序后，
	 * a<=b<=c充要条件
	 * a+b>c
	 * 同时如果a+b>c，那么(a到b之间的数)+b>c
	 * @param S
	 * @return
	 */
	public int triangleCount(int[] S) {
        // write your code here
    }
	
	/**
	 * 58. 四数之和
	给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
	四元组(a, b, c, d)中，需要满足a <= b <= c <= d

答案中不可以包含重复的四元组。
	 * @param numbers
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] numbers, int target) {
        // write your code here
    }
	/**
	 * lintcode 976. 4数和 II
	 * 给出 A, B, C, D 四个整数列表，
	 * 计算有多少的tuple (i, j, k, l)满足A[i] + B[j] + C[k] + D[l]为 0。

	为了简化问题，A, B, C, D 具有相同的长度，且长度N满足 0 ≤ N ≤ 500。
	所有的整数都在范围(-2^28, 2^28 - 1)内以及保证结果最多为2^31 - 1
	
	
	分成两组，A和B，C和D，分别记录两组的和以及对应的个数，用hashmap保存，然后加起来
	 * @param A
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
	 public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
	        // Write your code here
	    }
}
