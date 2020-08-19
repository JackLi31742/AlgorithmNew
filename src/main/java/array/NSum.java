package array;

import java.util.Arrays;
import java.util.HashMap;

public class NSum {
	public static void main(String[] args) {
		int[] nums= {2, 7, 11, 15};
		System.out.println(Arrays.toString(twoSum(nums, 9)));;
	}
	
	/**
	 * 1. 两数之和
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

}
