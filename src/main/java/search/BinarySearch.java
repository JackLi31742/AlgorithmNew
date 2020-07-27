package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  
 * ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 */
public class BinarySearch {
	
	public static void main(String[] args) {
		int[]arr={1,8, 10, 89, 1000, 1000,1234};
		
		System.out.println(search2(arr,1000));;
	}

	
	public static int search(int[] nums, int target) {
		if (nums==null||nums.length==0) {
			return -1;
		}

		return search(nums, 0, nums.length-1, target);
    }
	
	public static int search(int[] nums, int left, int right, int target) {
		//小心边界问题
		if (left <= right) {

			int mid = (right - left) / 2 + left;
			if (target > nums[mid]) {
				return search(nums, mid + 1, right, target);
			} else if (target < nums[mid]) {
				return search(nums, left, mid - 1, target);
			} else {
				return mid;
			}
		} else {
			return -1;
		}

	}
	/**
	 * 如果有重复的值
	 * {1,8, 10, 89, 1000, 1000，1234} 
	 * 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
	 */
	
	public static List<Integer> search2(int[] nums, int target) {
		if (nums==null||nums.length==0) {
			return new ArrayList<Integer>();
		}

		return search2(nums, 0, nums.length-1, target);
	}
	public static List<Integer> search2(int[] nums, int left, int right, int target) {
		List<Integer> result=new ArrayList<>();
		if (left <= right) {

			int mid = (right - left) / 2 + left;
			if (target > nums[mid]) {
				return search2(nums, mid + 1, right, target);
			} else if (target < nums[mid]) {
				return search2(nums, left, mid - 1, target);
			} else {
				//指针，因为要做移动
				int temp=mid-1;
				while(temp>=0&&nums[temp]==nums[mid]){
					result.add(temp);
					temp--;
				}
				result.add(mid);
				temp=mid+1;
				while(temp<nums.length&&nums[temp]==nums[mid]){
					result.add(temp);
					temp++;
				}
				return result;
			}
		} else {
			return result;
		}
	}
	
	/**
	 * 非递归二分查找
	 * @param nums 升序数组
	 * @param target
	 * @return 找到返回下标，找不到返回-1
	 */
	public static int search3(int[] nums, int target) {
		if (nums==null||nums.length<=0) {
			return -1;
		}
		int left=0;
		int right=nums.length-1;
		while(left<=right){
			int mid=(right-left)/2+left;
			
			if (nums[mid]==target) {
				return mid;
			}else if(nums[mid]<target){
				//左右边界在移动，不是mid在移动
				left=mid+1;
			}else {
				right=mid-1;
			}
		}
		return -1;
	}
}
