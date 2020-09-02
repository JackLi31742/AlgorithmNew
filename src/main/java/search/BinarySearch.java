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
		
		System.out.println(find(arr1, 15));
		
//		int[]nums2= {1,2};
//		int[]nums1= {3,4};
//		System.out.println(findMedianSortedArrays4(nums1,nums2));;
		
//		System.out.println(mySqrt2(8));;
//		System.out.println(Math.sqrt(8));
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

		 
		方法一：合并两个数组
	  */
	 public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 if (nums1==null) {
			 return getMedian(nums2);
		 }
		 if (nums2==null) {
			 return getMedian(nums1);
		 }
		 int[] merge=new int[nums1.length+nums2.length];
		 
		 //按照长度划分不对，因为长的不一定是大的
//		 if (nums1.length>=nums2.length) {
//			int[]temp=nums1;
//			nums1=nums2;
//			nums2=temp;
//		}
		 int i=0;
		 int j=0;
		 int index=0;
		 while(i<nums1.length&&j<nums2.length) {
			 if (nums1[i]<=nums2[j]) {
				 merge[index]=nums1[i];
				 i++;
			 }else {
				 merge[index]=nums2[j];
				 j++;
			 }
			 index++;
			 
		 }
		
		 for (int i2 = i; i2 < nums1.length; i2++) {
			merge[index]=nums1[i2];
			index++;
		}
		 for (int j2 = j; j2 < nums2.length; j2++) {
			merge[index]=nums2[j2];
			index++;
		}
		 
		 return getMedian(merge);
	 }
	 
	 
	 /**
	  * 方法二：用两个指针，不合并数组
	  * @param nums1
	  * @param nums2
	  * @return
	  */
	 public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		 if (nums1==null) {
			 return getMedian(nums2);
		 }
		 if (nums2==null) {
			 return getMedian(nums1);
		 }
		 
		 int i=0;
		 int j=0;
		 
		 int len1=nums1.length;
		 int len2=nums2.length;
		 
		 int len=len1+len2;
		 int left=0;
		 int right=0;
//		 int[] merge=new int[nums1.length+nums2.length];
//		 int index=0;
		 while((i+j)<=len/2) {
			 //需要用两个指针记录，否则出循环的时候，i和j很可能会数组越界
			 //而且right其实一直就是记录merge[index]的值，最后返回的len/2的
			 //那么left就可以记录上一次right的值，即len/2-1的值，那么如果len是偶数，就可以拿到值了
			 left=right;
			 //当i<nums1.length时，可以走merge[index]=nums1[i];
			 //当i>=nums1.length时，说明nums1走完了，所以走的是merge[index]=nums2[j];
			 //当j>=nums2.length时，就不能走merge[index]=nums2[j];走的也是上边的
			 if (i<nums1.length&&(j>=nums2.length||nums1[i]<=nums2[j])) {
//				 merge[index]=nums1[i];
				 right=nums1[i];
				 i++;
			 }else {
//				 merge[index]=nums2[j];
				 right=nums2[j];
				 j++;
			 }
//			 index++;
		 }
		 
//		 System.out.println(i+","+left);
//		 System.out.println(j+","+right);
//		 System.out.println(Arrays.toString(merge));
		 if (len%2==0) {
			return (left+right)/2.0;
		 }else {
			return right;
		}
	 }
	 
	 public static double findMedianSortedArrays3(int[] A, int[] B) {
		    int m = A.length;
		    int n = B.length;
		    int len = m + n;
		    int left = -1, right = -1;
		    int aStart = 0, bStart = 0;
		    for (int i = 0; i <= len / 2; i++) {
		        left = right;
		        if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
		            right = A[aStart++];
		        } else {
		            right = B[bStart++];
		        }
		    }
		    
		    System.out.println(aStart);
		    System.out.println(bStart);
		    if ((len & 1) == 0)
		        return (left + right) / 2.0;
		    else
		        return right;
		}

	
	 
	 /**
	  * 
	  * 二分法
	  * 
	  * 将两个数组切割后，要找到中位数，就是要找到切割后，
	  * 第一个数组左边的与第二个数组左边的数量和=第一个数组右边的与第二个数组右边的数量和
	  * 和方法2里的i+j=len/2的效果是一样的
	  * 二分法就是想通过二分的思想，加快扫描的过程
	  * 
	  * 由于两个数组有序，所以LMax1<RMin1,LMax2<RMin2
	  * 
	  * 如果能找到c1和c2，使得LMax2<RMin1,LMax1<RMin2，
	  * 
	  * 那么将两个数组合并后，在左边的还在左边，在右边的还在右边，不会混到另一个数组的另一侧
	  * 
	  * 这样，中位数就会出现在Max(LMax1,LMax2)和Min(RMin1,RMin2)中
	  * 
	  * 这里涉及到了len最后是奇数还是偶数的问题，对两个数组虚拟加入‘#’，
	  * 让len1转换成2len1+1 ，len2转换成2len2+1, 两数之和就变成了2(len1+len2)+2，恒为偶数
	  * 
	  * 加长后的偶数的中位数就是len1+len2+1和len1+len2
	  * 
	  * 原来的中位数就是在(len1+len2+1)/2和(len1+len2)/2的位置
	  * 
	  * 对于奇数，取(len1+len2)/2
	  * 对于偶数，(len1+len2+1)/2和(len1+len2)/2相等，取(len1+len2)/2和(len1+len2)/2-1的值
	  */
	 public static double findMedianSortedArrays4(int[] nums1, int[] nums2) {
		 
		 int len1=nums1.length;
		 int len2=nums2.length;
		 
		 int len=len1+len2;
		 //由于二分涉及到数组长短的比较，这里调用自身，少了额外的temp数组
		 //和解法1中涉及的长度划分不一样
		 if (len1>len2) {
			return findMedianSortedArrays4(nums2, nums1);
		 }
		 
		 //第1个数组切割后左边的最大值，右边的最小值
		 int LMax1=0,RMin1=0;
		//第2个数组切割后左边的最大值，右边的最小值
		 int LMax2=0,RMin2=0;
		 //c1代表i，c2代表j
		 //(c1+c2)<=len/2
		 int c1=0,c2=0;
		 
		 int low=0;
		 //由于虚拟加入了‘#’,所以长度是2len1+1，由于这里是下标，所以是2len1
		 int high=2*len1;
		 
		 while(low<=high) {
			 c1=(high-low)/2+low;
			 
			 c2=len-c1;
				 
			 if (c1==0) {
				//说明数组1的左边没有值了，为了方便比较，要设LMax1是一个不影响Max函数的值
				LMax1=Integer.MIN_VALUE;
			 }else {
				 LMax1=nums1[(c1-1)/2];
			}
			 
			 if (c1==2*len1) {
				//说明数组1的右边没有值了，为了方便比较，要设RMin1是一个不影响Min函数的值
				 RMin1=Integer.MAX_VALUE;
			 }else {
				 RMin1=nums1[c1/2];
			}
			 
			 
			 if (c2==0) {
					//说明数组2的左边没有值了，为了方便比较，要设LMax2是一个不影响Max函数的值
					LMax2=Integer.MIN_VALUE;
			  }else {
				  LMax2=nums2[(c2-1)/2];
			}
			 
			 if (c2==2*len2) {
				//说明数组2的右边没有值了，为了方便比较，要设RMin2是一个不影响Min函数的值
				 RMin2=Integer.MAX_VALUE;
			 }else {
				 RMin2=nums2[c2/2];
			}
			 
			 if (LMax1<=RMin2&&LMax2<=RMin1) {
				break;
			 }
			 
			 //说明数组1左边的元素太多了，
			 if (LMax1>RMin2) {
				//c1小了，c2就大了
				high=c1-1;
			 }  
			 else if (LMax2>RMin1) {
				 //c2需要向左移动，c1向右移动
				low=c1+1;
			}
		}
		 
		if (len%2==0) {
			return (Math.max(LMax1, LMax2)+Math.min(RMin1, RMin2))/2.0;
		}else {
			return Math.min(RMin1, RMin2);
		}
		 
		 
		 
	 }
	public static double getMedian(int[] nums) {
		if (nums.length%2==0) {
			int left=nums[nums.length/2-1];
			int right=nums[nums.length/2];
			return (left+right)/2.0;
		}else {
			return nums[nums.length/2];
		}
	}
	
	/**
	 * 69. x 的平方根

	 * 实现 int sqrt(int x) 函数。

		计算并返回 x 的平方根，其中 x 是非负整数。
		
		由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

	 * @param x
	 * @return
	 */
	public static int mySqrt(int x) {
		int left=0;
		int right=x;
		int mid=0;
		while(left<=right) {
			mid=(right-left)/2+left;
			if (mid==0) {
				if (x==0) {
					return mid;
				}else {
					left=mid+1;
				}
			}else {
				
				if (mid<x/mid) {
					left=mid+1;
				}else if (mid>x/mid) {
					right=mid-1;
				}else {
					return mid;
				}
			}
		}
//		System.out.println(left+","+mid+","+right);
		return right;
    }
	
	/**
	 * 保留6位小数
	 * @param x
	 * @return
	 */
	
	public static double mySqrt2(int x) {
		double left=0;
		double right=x;
		double mid=0;
		//介值定理，所以每次mid不能加1，减一，得加减range
		double range=0.000001;
		while(left<=right) {
			mid=(right-left)/2.0+left;
			if (mid==0) {
				if (x==0) {
					return mid;
				}else {
					left=mid+range;
				}
			}else {
				
				if (mid<x/mid) {
					left=mid+range;
				}else if (mid>x/mid) {
					right=mid-range;
				}else {
					return mid;
				}
			}
		}
//		System.out.println(left+","+mid+","+right);
		
		return (double)Math.round(right*1000000)/1000000;
    }
}
