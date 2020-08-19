package search;

import java.util.ArrayList;
import java.util.List;
public class BinarySearch {
	
	public static void main(String[] args) {
		int[]arr={1,8, 10, 89, 1000, 1000,1234};
		
		System.out.println(search2(arr,1000));;
		
		int[]arr1={ -1, 3, 3, 7, 10, 14, 14 };
		
		System.out.println(findFirstBeyondNum(arr1, 9));
	}

	
	/**
	 * 704. 二分查找
	 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  
	 * ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
	 *
	 */
	
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
	
	
	/**
	 * 在一个有序数组中，查找出第一个大于 9 的数字，假设一定存在。
	 * 例如，arr = { -1, 3, 3, 7, 10, 14, 14 }; 则返回 10。
	 * 
	 * 问题转换为35. 搜索插入位置，如果再arr中有9，那么找到9之后，向后遍历第一个比9大的值返回
	 * 如果找不到，那么9要插入的索引位就是第一个比9大的
	 */
	
	public static int findFirstBeyondNum(int[] arr,int target) {
		int left=0;
		int right=arr.length-1;
		while(left<=right) {
			int mid=(right-left)/2+left;
			if (arr[mid]<target) {
				left=mid+1;
			}else if(arr[mid]>target) {
				right=mid-1;
			}else {
				while(mid<arr.length&&arr[mid]==target) {
					mid++;
				}
				return arr[mid];
			}
			
		}
		return arr[left];
		
				
	}
	
	/**
	 * 35. 搜索插入位置
	 * Arrays中的二分查找，如果没找到，返回的是-(left+1)
	 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
	 * @param nums
	 * @param target
	 * @return
	 */
	 public static int searchInsert(int[] nums, int target) {
		 int left=0;
		 int right=nums.length-1;
		 
		 while(left<=right) {
			 int mid=(right-left)/2+left;
			 if (nums[mid]>target) {
				right=mid-1;
			}else if (nums[mid]<target) {
				left=mid+1;
			}else {
				return mid;
			}
		 }
		 
		 return left;
	 }
}
