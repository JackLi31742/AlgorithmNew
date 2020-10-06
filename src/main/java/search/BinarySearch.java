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
		int[]arr1={ -1, 3, 3, 7, 10, 14, 14 };
//		
//		System.out.println(findFirstBeyondNum(arr1, 9));
//		
//		int[]arr2= {1,2,3};
//		
//		System.out.println(Arrays.toString(searchRange(arr2, 1)));
		
		
		int[] arr3= {12, 20, -21, -21, -19, -14, -11, -8, -8, -8, -6, -6, -4, -4, 0, 1, 5, 5, 6, 11, 11, 12};
		
//		System.out.println(find(arr1, 15));
		
//		int[]nums2= {1,2};
//		int[]nums1= {3,4};
//		System.out.println(findMedianSortedArrays4(nums1,nums2));;
		
//		System.out.println(mySqrt2(8));;
//		System.out.println(Math.sqrt(8));
		
		int[] arr= {1,3,5,6};
		
		searchInsert(arr, 2);
	}

	/**
	 * lintcode 457. 经典二分查找问题
	 * 在一个排序数组中找一个数，返回该数出现的任意位置，如果不存在，返回 -1。
	 * 通过 while 循环，将区间范围从 n 缩小到 2 （只有 start 和 end 两个点）。
		在 start 和 end 中判断是否有解。
	 * @param nums
	 * @param target
	 * @return
	 */
	public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        // 要点1: start + 1 < end
        while (start + 1 < end) {
     // 要点2：start + (end - start) / 2
            int mid = start + (end - start) / 2;
            // 要点3：=, <, > 分开讨论，mid 不+1也不-1
            if (nums[mid] == target) {
                return mid;
                //大部分时候，mid 是可以 +1 和 -1 的。在一些特殊情况下，比如寻找目标的最后一次出现的位置时，
//                当 target 与 nums[mid] 相等的时候，是不能够使用 mid + 1 或者 mid - 1 的。
//                因为会导致漏掉解。那么为了节省脑力，统一写成 start = mid / end = mid 并不会造成任何解的丢失，
//                并且也不会损失效率——log(n) 和 log(n+1) 没有区别。
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // 要点4: 循环结束后，单独处理start和end
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
	
	/**
	 * lintcode 458. 目标最后位置
	 * @param nums
	 * @param target
	 * @return
	 */
	public int lastPosition(int[] nums, int target) {
        // write your code here
		if (nums==null||nums.length==0) {
			return -1;
		}
		
		int left=0;
		int right=nums.length-1;
		
		while(left +1 < right) {
			
			int mid=left+(right-left)/2;
			
			if (nums[mid]==target) {
				left=mid;
			}else if (nums[mid]<target) {
				left=mid;
			}else {
				right=mid;
			}
		}
		
		if (nums[right]==target) {
			return right;
		}
		if (nums[left]==target) {
			return left;
		}
		
		return -1;
    }
	
	
		/**
		 * lintcode 14. 二分查找
		 * 用O(logn)的时间查找到target第一次出现的下标（从0开始）
		 * @param nums
		 * @param target
		 * @return
		 */
	 public int binarySearch(int[] nums, int target) {
	        // write your code here
		 
		 if (nums == null || nums.length ==0) {
			return -1;
		}
		 
		 int left=0;
		 int right=nums.length-1;
		 
		 while(left +1 <right) {
			 
			 int mid =left+(right-left)/2;
			 
			 if (nums[mid]==target) {
				right=mid;
			}else if (nums[mid]<target) {
				left=mid;
			}else {
				right=mid;
			}
		 }
		 
		 if (nums[left]==target) {
			return left;
		}
		 if (nums[right]==target) {
			return right;
		}
		 return -1;
	 }
	 
	 /**
	  * 585. 山脉序列中的最大值
	  * 给 n 个整数的山脉数组，即先增后减的序列，找到山顶（最大值）
	  * @param nums
	  * @return
	  */
	 public int mountainSequence(int[] nums) {

		 if (nums==null||nums.length==0) {
			return -1;
		}
		 
		 int left=0;
		 int right=nums.length-1;
		 
		 while(left+1<right) {
			 
			 int mid=left+(right-left)/2;
			 //山顶
			 if (nums[mid]>nums[mid-1]&&nums[mid]>nums[mid+1]) {
				return nums[mid];
			 }else if (nums[mid]<nums[mid-1]) {//下坡
				right=mid;
			}else  {//上坡
				left=mid;
			}
		 }
		 
		 return Math.max(nums[left], nums[right]);
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
		//循环退出条件有=
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
	 * 变体三：查找第一个大于等于给定值的元素
	 * 
	 * 数组中存储的这样一个序列：3，4，6，7，10。如果查找第一个大于等于 5 的元素，那就是 6。
	 */
	
	public static int find(int[]arr,int target) {
		
		int left=0;
		int right=arr.length-1;
		while(left<=right) {
			int mid=left+(right-left)/2;
			
			if (arr[mid]>=target) {
				//和变体1类似
				if (mid==0||arr[mid-1]<target) {//不用循环了
					return mid;
				}else {
					right=mid-1;
				}
				
			}else {
				left=mid+1;
			}
		}
		
		return -1;
	}
	
	/**
	 * 二分查找的变种
	 * 变体一：查找第一个值等于给定值的元素
	 * 有序数据集合中存在重复的数据，我们希望找到第一个值等于给定值的数据，
	 * @return
	 */
	public static int searchFirst(int[] arr,int target) {
		
		int left=0;
		int right=arr.length-1;
		while(left<=right) {
			int mid=left+(right-left)/2;
			if (arr[mid]>target) {
				right=mid-1;
			}else if (arr[mid]<target) {
				left=mid+1;
			}else {//这里就不用循环去找了
				//要找第一个等于给定值的元素，那么如果mid是0，左边再没有元素了，肯定就是要找的
				//或者mid-1的值小于target说明也是第一个
				if (mid==0||arr[mid-1]<target) {
					return mid;
				}else {//说明在left和mid之间
					right=mid-1;
				}
			}
		}
		
		return -1;
		
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
	  * 用left+1 <right
	  * ，不如上面的解法
	  * @param nums
	  * @param target
	  * @return
	  */
	 public static int searchInsert2(int[] nums, int target) {
		 
		 if (nums ==null) {
			return -1;
		}
		 
		 int left=0;
		 int right=nums.length-1;
		 
		 while(left +1 <right) {
			 
			 int mid=left+(right-left)/2;
			 if (nums[mid]==target) {
				return mid;
			}else if (nums[mid]<target) {
				left=mid;
			}else {
				right=mid;
			}
		 }
		 
		 
		 if (nums[left]==target) {
			return left;
		}
		 
		 if (nums[right]==target) {
			return right;
			
		}
		 
		 if (nums[left]<target&&nums[right]>target) {
			return right;
		}
		 if (nums[left]>target) {
			
			 return left;
		}
		 return right+1;
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
	 
	 
	 
	 
	 
	
	
}
