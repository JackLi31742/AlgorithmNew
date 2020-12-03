package binarysearch.second;

import java.util.List;

public class BinarySearch {

	
	public static void main(String[] args) {
		int[]A= {123,123,123};
		BinarySearch binarySearch=new BinarySearch();
		
		binarySearch.woodCut(A, 3);
				
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
	public static int findPosition(int[] nums, int target) {
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
	  * 447. 在大数组中查找
	  * 给一个按照升序排序的非负整数数组。这个数组很大以至于你只能通过固定的接口 ArrayReader.get(k) 
	  * 来访问第k个数(或者C++里是ArrayReader->get(k))，并且你也没有办法得知这个数组有多大。

		找到给出的整数target第一次出现的位置。你的算法需要在O(logk)的时间复杂度内完成，k为target第一次出现的位置的下标。

		如果找不到target，返回-1。
		
		
		本题既然无非得知数组的右边界，所以需要想办法得到右边界，需要使用倍增的思想去得到右边界
		
		每次倍增，那么当走到k时，其实用的是logK的时间走到的，然后再二分
	  * @param reader
	  * @param target
	  * @return
	  */
//	 public int searchBigSortedArray(ArrayReader reader, int target) {
//	        // write your code here
//		 
//		 int right=getNumLargerThanTarget(reader, target);
//		 
//		 int left=0;
//		 
//		 while(left+1<right) {
//			 
//			 int mid=left+(right-left)/2;
//			 
//			 if (reader.get(mid)<target) {
//				left=mid;
//			}else  {
//				//找第一个位置，向左找
//				right=mid;
//			
//			}
//		 }
//		 
//		 if (reader.get(left)==target) {
//			return left;
//		}
//		 
//		 if (reader.get(right)==target) {
//			return right;
//		}
//		 
//		 return -1;
//	 }
//	 
//	 public int getNumLargerThanTarget(ArrayReader reader, int target) {
//		 
//		 int index=1;
//		 while(reader.get(index)<=target) {
//			 index=index*2;
//		 }
//		 
//		 return index;
//	 }
	 
	 
	 
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
			 
			 //由于是向下取整，所以mid+1不会越界，但mid-1可能会
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
	  * //由于是向下取整，所以mid+1不会越界，但mid-1可能会
	  * @param nums
	  * @return
	  */
	public int mountainSequence2(int[] nums) {

		if (nums == null || nums.length == 0) {
			return -1;
		}

		int left = 0;
		int right = nums.length - 1;

		while (left + 1 < right) {

			 int mid=left+(right-left)/2;
			 
			 if (nums[mid]>nums[mid+1]) {//下坡
				right=mid;
			}else {//上坡
				left=mid;
			}
		}
		//即使在while循环中不return，无非多走了几次，不影响时间复杂度，
		//会使left和right最后趋近于山顶两侧
		return Math.max(nums[left], nums[right]);
	}
	
	/**
	 * 460. 在排序数组中找最接近的K个数
	 * 给一个目标数 target, 一个非负整数 k, 一个按照升序排列的数组 A。在A中找与target最接近的k个整数。
	 * 返回这k个数并按照与target的接近程度从小到大排序，如果接近程度相当，那么小的数排在前面。
	 * 
	 * 
	 * 找到target在A的位置，将A一分为二，接下来跟合并两个排序数组很像
	 * @param A
	 * @param target
	 * @param k
	 * @return
	 */
	public static int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
		
		if (A==null||A.length==0) {
			return new int[] {};
		}
		
		
		int pos=findInsert(A, target);
		
		
		int left=pos-1;
		int right=pos;
		
		int[] result=new int[k];
		int index=0;
		
		while(left>=0&&right<=A.length-1&&index<k) {
			
			int leftDif=(target-A[left]);
			int rightDif=(A[right]-target);
			
			if (leftDif<=rightDif) {
				result[index]=A[left];
				left--;
			} else {
				result[index]=A[right];
				right++;
			}
			index++;
		}
		
		while(left>=0&&index<k) {
			
			result[index]=A[left];
			left--;
			index++;
		}
		
		while(right<=A.length-1&&index<k) {
			
			result[index]=A[right];
			right++;
			index++;
		}
		
		return result;
		
    }
	
	/**
	 * 搜索插入的位置，得到的下标偏右
	 * @param A
	 * @param target
	 * @return
	 */
	public static int findInsert(int[] A, int target) {
		
		int left=0;
		int right=A.length-1;
		
		while(left<=right) {
			
			int mid=left+(right-left)/2;
			
			if (A[mid]>target) {
				right=mid-1;
			}else if (A[mid]<target) {
				left=mid+1;
			}else {
				return mid;
			}
		}
		
		return left;
	}
	
	/**
	 * 159. 寻找旋转排序数组中的最小值
	 * 假设一个排好序的数组在其某一未知点发生了旋转
	 * （比如0 1 2 4 5 6 7 可能变成4 5 6 7 0 1 2）。
	 * 你需要找到其中最小的元素。
	 * 
	 * 12345
	 * 数组旋转后
	 * 45123
	 * 也是分割为两部分，右半部分的条件是<=最后一个数，可以举特例12345，并不是小于第一个数
	 * @param nums
	 * @return
	 */
	public static int findMin(int[] nums) {
        // write your code here
		
		if (nums==null||nums.length==0) {
			return Integer.MIN_VALUE;
		}
		
		int left=0;
		int right=nums.length-1;
//		int target=nums[right];
		
		while(left+1<right) {
			
			int mid = left + (right-left)/2;
			//当right指针变化时，为了加快搜索速度，可以将target的值不写死
			if (nums[mid]<=nums[right]) {
				right=mid;
			}else {
				left=mid;
			}
		}
		
		return Math.min(nums[left], nums[right]);
    }
	
	
	public static int findMinIndex(int[] nums) {
        // write your code here
		
		if (nums==null||nums.length==0) {
			return Integer.MIN_VALUE;
		}
		
		int left=0;
		int right=nums.length-1;
//		int target=nums[right];
		
		while(left+1<right) {
			
			int mid = left + (right-left)/2;
			//当right指针变化时，为了加快搜索速度，可以将target的值不写死
			if (nums[mid]<=nums[right]) {
				right=mid;
			}else {
				left=mid;
			}
		}
		
		return nums[left]>nums[right]?right:left;
    }
	
	/**
	 * 62. 搜索旋转排序数组
	 * 假设有一个排序的按未知的旋转轴旋转的数组
	 * (比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)。给定一个目标值进行搜索，
	 * 如果在数组中找到目标值返回数组中的索引位置，否则返回-1。你可以假设数组中不存在重复的元素。
	 * 
	 * 根据target的位置调用二分法
	 * @param A
	 * @param target
	 * @return
	 */
	public static int search(int[] A, int target) {
		
		if (A==null||A.length==0) {
			return -1;
		}
		
		int minIndex=findMinIndex(A);
		
		//还是按照最右边的数进行区分target的区间
		if (target<=A[A.length-1]) {
			
			return binarySearch(A, target, minIndex, A.length-1);
		}else {
			
			return binarySearch(A, target, 0, minIndex-1);
		}
	}
	
	/**
	 * 
	 * @param A
	 * @param target
	 * @param left
	 * @param right
	 * @return
	 */
	public static int binarySearch(int[] A, int target ,int left,int right) {
		
		while(left + 1 <right) {
			
			int mid=left+(right-left)/2;
			if (A[mid]==target) {
				return mid;
			}else if (A[mid]<target) {
				left=mid;
			}else {
				right=mid;
			}
			
		}
		
		if (A[left]==target) {
			return left;
		}
		
		if (A[right]==target) {
			return right;
		}
		
		return -1;
	}
	
	/**
	 * 旋转数组在二分后，本质还是旋转数组
	 * 调用一次二分法
	 * @param A
	 * @param target
	 * @return
	 */
	public static int search2(int[] A, int target) {
        // write your code here
		
		if (A==null||A.length==0) {
			return -1;
		}
		
		int left=0;
		int right=A.length-1;
		
		while(left+1<right) {
			
			int mid=left+(right-left)/2;
			
			if (target<=A[right]) {
				
//				if (A[mid]>A[right]) {
//					left=mid;
//				}else {
//					if (target>A[mid]) {
//						left=mid;
//					}else {
//						right=mid;
//					}
//				}
				
				if (target<=A[mid]&&A[mid]<=A[right]) {
					right=mid;
				}else {
					left=mid;
				}
				
			}else {
//				if (A[mid]>A[right]) {
//					if (target<A[mid]) {
//						right=mid;
//					}else {
//						left=mid;
//					}
//				}else {
//					right=mid;
//				}
				
				if (target>=A[mid]&&A[mid]>A[right]) {
					left=mid;
				}else {
					right=mid;
				}
				
			}
			
		}
		
		if (A[left]==target) {
			return left;
		}
		
		if (A[right]==target) {
			
			return right;
		}
		
		return -1;
				
    }
	
	/**
	 * 75. 寻找峰值
	
		你给出一个整数数组(size为n)，其具有以下特点：

		相邻位置的数字是不同的
		A[0] < A[1] 并且 A[n - 2] > A[n - 1]
		假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，
		返回数组中任意一个峰值的位置。
		
		
		要想有峰值，必须是先上升后下降
	 * @param A
	 * @return
	 */
	public int findPeak(int[] A) {
        // write your code here
		if (A==null||A.length==0) {
			return -1;
		}
		
		int left=0;
		int right=A.length-1;
		
		while(left+1<right) {
			
			int mid=left+(right-left)/2;
			
			if (mid==0) {
				left=mid;
			}else if (mid==A.length-1) {
				right=mid;
			}else {
				if (A[mid]>A[mid-1]&&A[mid]>A[mid+1]) {
					return mid;
				}else if (A[mid]>A[mid-1]&&A[mid+1]>A[mid]) {//上坡
					left=mid;
				}else if (A[mid]<A[mid-1]&&A[mid+1]<A[mid]) {//下坡
					right=mid;
				}else {
					//由于在谷底的时候左右都可以，所以上边的判断其实可以简化不需要用&&
					left=mid;
//					right=mid;
				}
			}
		}
		
		return A[left]>A[right]?left:right;
    }
	
	/**
	 * 390. 找峰值 II

	 * @param A
	 * @return
	 */
	public List<Integer> findPeakII(int[][] A) {
        // write your code here
    }
	
	
	/**
	 * 183. 木材加工
	 * 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，
	 * 需要得到的小段的数目至少为 k。当然，我们希望得到的小段越长越好，
	 * 你需要计算能够得到的小段木头的最大长度。
	 * 
	 * 无法切出要求至少 k 段的,则返回 0 即可。
	 * 
	 * 这是在答案范围上进行二分，即长度是有范围的，就是1到Max(L中的最大值,L中的sum/k)
	 * 
	 * 最后的长度，当超过这个长度时，会导致除以这个长度后的值<k
	 * @param L
	 * @param k
	 * @return
	 */
	public int woodCut(int[] L, int k) {
        // write your code here
		
		if (L==null||L.length==0) {
			return 0;
		}
//		System.out.println(L.length);
		long left=1;
		long right=getMax(L, k);
		
		while(left+1 < right) {
			
			long mid=left+(right-left)/2;
			
			long result=getResult(L, mid);
			
			if (result<k) {//说明mid大了，导致分割的段少了
				
				right=mid;
			}else if (result>=k) {
				
				left=mid;
			}
//			else {
//				return (int)mid;
//			}

		}
		
		long max=0;
		
		if (getResult(L, left)>=k) {
			
			max=left;
		}
		
		if (getResult(L, right)>=k) {
			
			if (right>max) {
				max=right;
			}
		}
		
		return (int)max;
    }
	
	
	public long getResult(int[] L,long len) {
		
		long result=0;
		
		for (int i = 0; i < L.length; i++) {
			result+=L[i]/len;
		}
		
//		System.out.println(result);
		return result;
	}
	
	
	public long getMax(int[] L,int k) {
		
		int max=L[0];
		long sum=L[0];
		
		for (int i = 1; i < L.length; i++) {
			
			if (L[i]>max) {
				max=L[i];
			}
			sum+=L[i];
		}
//		System.out.println(max);
//		System.out.println(sum);
		return Math.max(max, sum/k);
	}
}
