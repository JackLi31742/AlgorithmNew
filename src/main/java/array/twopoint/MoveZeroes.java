package array.twopoint;

import java.util.Arrays;

public class MoveZeroes {

	
	public static void main(String[] args) {
		
		int[] nums = {0, 1, 0, 3, 12};
		
		moveZeroes(nums);
	}
	/**
	 * 539. Move Zeroes
	 * 给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序
	 * @param nums
	 */
	
	
	public static void moveZeroes(int[] nums) {
        // write your code here
		
		if (nums==null||nums.length==0) {
			return;
		}
		
		int left=0;
		int right=0;
		
		while(right<nums.length) {
			if (left<=right&&nums[right]==0) {
				right++;
			}else {
				swip(nums, left, right);
				left++;
				right++;
			}
		}
		
		System.out.println(Arrays.toString(nums));
    }
	
	
	//不保持原数组非0元素的顺序
	//答案是错的
	public static void moveZeroes2(int[] nums) {
		
		if (nums==null||nums.length==0) {
			return;
		}
		
		int cur=0;
		int right=nums.length-1;
		
		while(cur<=right) {
			if (cur<=right&&nums[cur]==0) {
				swip(nums, cur, right);
				right--;
			}else {
				cur++;
			}
		}
		
		System.out.println(Arrays.toString(nums));
	}
	
	//既然只找0，那么可以不每次都做交换
	public static void moveZeroes3(int[] nums) {
		
		if (nums==null||nums.length==0) {
			return;
		}
		
		int left=0;
		int right=0;
		
		//如果没有0，那么left和right在同一个下标，则不会改变nums
		//如果有0，那么left指向非0的下一个下标，right比left更靠右
		while(right<nums.length) {
			
			if (nums[right]!=0) {
				nums[left]=nums[right];
				left++;
			}
			
			right++;
		}
		
		
		while(left<nums.length) {
			nums[left]=0;
			left++;
		}
	}
	
	
	public static void swip(int[]nums,int i,int j) {
		int temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
}
