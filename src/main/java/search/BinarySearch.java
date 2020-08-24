package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BinarySearch {
	
	public static void main(String[] args) {
//		int[]arr={1,8, 10, 89, 1000, 1000,1234};
//		
//		System.out.println(search2(arr,1000));;
//		
//		int[]arr1={ -1, 3, 3, 7, 10, 14, 14 };
//		
//		System.out.println(findFirstBeyondNum(arr1, 9));
//		
//		int[]arr2= {1,2,3};
//		
//		System.out.println(Arrays.toString(searchRange(arr2, 1)));
		
		
		int[] arr3= {12, 20, -21, -21, -19, -14, -11, -8, -8, -8, -6, -6, -4, -4, 0, 1, 5, 5, 6, 11, 11, 12};
		
		System.out.println(search5(arr3, -8));
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
	 
	 /**
	  * 34. 在排序数组中查找元素的第一个和最后一个位置
	  * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

			你的算法时间复杂度必须是 O(log n) 级别。

			如果数组中不存在目标值，返回 [-1, -1]。

	  * @param nums
	  * @param target
	  * @return
	  */
	 public static int[] searchRange(int[] nums, int target) {
		 int left=0;
		 int right=nums.length-1;
		 int[] result= {-1,-1};
		 while(left<=right) {
			 int mid=(right-left)/2+left;
			 if (nums[mid]>target) {
				right=mid-1;
				
			}else if (nums[mid]<target) {
				left=mid+1;
			}else {
				int tempLeft=mid;
				while(tempLeft>=0&&nums[tempLeft]==target) {
					tempLeft--;
				}
				if (tempLeft>=0&&nums[tempLeft]==target) {
					
					result[0]=tempLeft;
				}else {
					result[0]=tempLeft+1;
				}
				
				int tempRight=mid;
				while(tempRight<nums.length&&nums[tempRight]==target) {
					tempRight++;
				}
				
				if (tempRight<nums.length&&nums[tempRight]==target) {
					result[1]=tempRight;
					
				}else {
					result[1]=tempRight-1;
				}
				//这里要return，或者break
				return result;
			}
		 }
		 
		 return result;
	 }
	 
	 
	 /**
	  * 33. 搜索旋转排序数组
	  * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

		( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
		
		搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
		
		你可以假设数组中不存在重复的元素。
	  */
	 
	 public static int search4(int[] nums, int target) {
		 int left=0;
		 int right=nums.length-1;
		 return search4(nums, target, left, right);
		 
	 }
	 
	 public static int search4(int[] nums, int target,int left,int right) {
		 
		 if (left<=right) {
			
			 if (nums[left]==target) {
				return left;
			 }
			 if (nums[right]==target) {
				return right;
			}
			 int mid=(right-left)/2+left;
			 if (nums[mid]==target) {
				 return mid;
			 }
			 //左侧有序
			 if (nums[left]<=nums[mid]) {
				 //target在左侧
				 if (nums[left]<=target&&target<=nums[mid]) {
					 return search4(nums, target, left, mid-1);
				 }else {//target在右侧
					 return search4(nums, target, mid+1, right);
				 }
			 }else {//右侧有序
				 //target在右侧
				 if (nums[mid]<=target&&target<=nums[right]) {
					 return search4(nums, target, mid+1, right);
				 }else {//target在左侧
					 return search4(nums, target, left, mid-1);
				 }
			 }
		}else {
//			if (nums[left]==target) {
//				return left;
//			}
			return -1;
		}
		 
	 }
	 
	 /**
	  * 33. 搜索旋转排序数组
	  * 非递归
	  * @param nums
	  * @param target
	  * @param left
	  * @param right
	  * @return
	  */
	 public static int search42(int[] nums, int target,int left,int right) {
		 
		 while (left<=right) {
			
			 if (nums[left]==target) {
				return left;
			 }
			 if (nums[right]==target) {
				return right;
			}
			 int mid=(right-left)/2+left;
			 if (nums[mid]==target) {
				 return mid;
			 }
			 //左侧有序
			 if (nums[left]<=nums[mid]) {
				 //target在左侧
				 if (nums[left]<=target&&target<=nums[mid]) {
					 right=mid-1;
				 }else {//target在右侧
					 left=mid+1;
				 }
			 }else {//右侧有序
				 //target在右侧
				 if (nums[mid]<=target&&target<=nums[right]) {
					 left=mid+1;
				 }else {//target在左侧
					 right=mid-1;
				 }
			 }
		}
		 
//		 else {
//			if (nums[left]==target) {
//				return left;
//			}
			return -1;
//		}
		 
	 }
	 
	 /**
	  * 面试题 10.03. 搜索旋转数组 33是特例，只旋转了一次
	  * 搜索旋转数组。给定一个排序后的数组，包含n个整数，
	  * 但这个数组已被旋转过很多次了，次数不详。
	  * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。
	  * 若有多个相同元素，返回索引值最小的一个。
	  * @param arr
	  * @param target
	  * @return
	  */
	 public static int search5(int[] arr, int target) {

		 int left=0;
		 int right=arr.length-1;
		 return search5(arr, target, left, right);
	 }
	 
	 public static int search5(int[] nums, int target,int left,int right) {
		 
		 while (left<=right) {
			
			 //为了找到索引最小的，一直让left去匹配
			 if (nums[left]==target) {
				return left;
			 }
//			 if (nums[right]==target) {
//				return right;
//			}
			 int mid=(right-left)/2+left;
//			 if (nums[mid]==target) {
//				 return mid;
//			 }
//			 System.out.println(left+":"+nums[left]+","+mid+":"+nums[mid]+","+right+":"+nums[right]);
			 //左侧有序
			 if (nums[left]<nums[mid]) {
				 
				//target在左侧
				 if (nums[left]<=target&&target<=nums[mid]) {
					 //为了搞定重复的，不能用+1或者-1
					 right=mid;
				 }else {//target在右侧
					 left=mid;
				 }
			 }else if(nums[left]>nums[mid]){//右侧有序

				 if (nums[mid]<=target&&target<=nums[right]) {
					 left=mid;
				 }else {//target在左侧
					 right=mid;
				 }
			 }else {
				left++;
			}
		}
		 

		return -1;

		 
	 }
	 
	 /**
	  * 4. 寻找两个正序数组的中位数
	  * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

		请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
		
		你可以假设 nums1 和 nums2 不会同时为空。

	  * @param nums1
	  * @param nums2
	  * @return
	  */
	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 if (nums1==null) {
			
		}
	 }
	 
	
}
