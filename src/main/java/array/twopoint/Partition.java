package array.twopoint;

import java.util.Arrays;

public class Partition {

	public static void main(String[] args) {
		int[]nums= {9,3,2,4,8};
		
//		partitionArray(nums, 2);
		
		Partition partition=new Partition();
		
//		System.out.println(partition.kthLargestElement(3, nums));;
//		
//		System.out.println(Arrays.toString(nums));
		
		int[][] matrix = {{1,5,7},
							{3,7,8},
							{4,8,9}};
		
		System.out.println(partition.kthSmallest(matrix, 1));;
	}
	
	/**
	 * 912. 排序数组
	 * 给你一个整数数组 nums，请你将该数组升序排列。
	 * @param nums
	 * @return
	 */
	public int[] sortArray(int[] nums) {
		
		if (nums==null||nums.length==0) {
			return nums;
		}
		
		sort(nums, 0, nums.length-1);
		
		return nums;
		
    }
	
	public void sort(int[] nums,int left,int right) {
		//这里必须要有等号，否则会Stack Overflow
		if (left>=right) {
			return ;
		}
		int partition=partition(nums, left, right);
		sort(nums, partition, right);
		sort(nums, left, partition-1);
	}
	
	
	
	public int partition(int[] nums,int left,int right) {
		
		int k=nums[(right-left)/2+left];
		
		while(left<=right) {
			//和partitionArray不同，这里不能有=
			while(left<=right&&nums[left]<k) {
				left++;
			}
			
			while(left<=right&&nums[right]>k) {
				right--;
			}
			
			
			if (left<=right) {
				swip(nums, left, right);
				
				left++;
				right--;
			}
			
		}
		
		return left;
	}
	
	/**
	 * lintcode 31. 数组划分
	 * 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：

		所有小于k的元素移到左边
		所有大于等于k的元素移到右边
		
		返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int partitionArray(int[] nums, int k) {
        // write your code here
		if (nums==null||nums.length==0) {
			return 0;
		}
		
		
		int left=0;
		int right=nums.length-1;
		
		//当left==right时，肯定会走下边两个while循环中的一个，会导致不满足循环条件，从而退出循环
		//当left在right右边时，left肯定是第一个大于等于k的
		while(left<=right) {
			//一直走这个循环，知道nums[left]>=k
			while(left<=right&&nums[left]<k) {
				left++;
			}
			
			while(left<=right&&nums[right]>=k) {
				right--;
			}
			
			//指针在上边的两个while循环走完后可能right已经在left左边，这时就不能继续走了
			if (left<=right) {
				swip(nums, left, right);
				
				left++;
				right--;
			}
			
		}
		
		return left;
    }
	
	
	/**
	 * 144. 交错正负数
	 * 给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
	 * @param A
	 */
	
	public void rerange(int[] A) {
        // write your code here
		
		if (A==null||A.length==0) {
			return ;
		}
		
		int index=partitionArray(A, 0);
		
		int negCount=index;
		int posCount=A.length-index;
		
		swipNegPos(A,negCount, posCount,index);
		
    }
	
	//交换正负数
	public void swipNegPos(int[]nums,int negCount,int posCount,int index) {
		
		
		
		//负数比正数少，负数i从下标0，正数j从nums.length-2,开始交换，i每次加2，j每次-2
		//相等的情况，负数i从下标1，正数j从nums.length-2,开始交换，i每次加2，j每次-2
		//负数比正数多，负数i从下标1，正数j从nums.length-1，开始交换，i每次加2，j每次-2
		
		int i=0;int j=0;
		if (negCount<posCount) {
			i=0;
			j=nums.length-2;
		}else if (negCount==posCount) {
			i=1;
			j=nums.length-2;
		}else {
			i=1;
			j=nums.length-1;
		}
		
		while(i<index) {
			swip(nums, i, j);
			i=i+2;
			j=j-2;
		}
		
		
	}
	
	/**
	 * 373. 奇偶分割数组
	 * 分割一个整数数组，使得奇数在前偶数在后。
	 * @param nums
	 */
	public void partitionArray(int[] nums) {
        // write your code here
		if (nums==null||nums.length==0) {
			return ;
		}
		
		int left=0;
		int right=nums.length-1;
		while(left<=right) {
			
			while(left<=right&&nums[left]%2!=0) {
				left++;
			}
			
			while(left<=right&&nums[right]%2==0) {
				right--;
			}
			
			if (left<=right) {
				swip(nums, left, right);
				left++;
				right--;
			}
		}
		
    }
	
	/**
	 * 49. 字符大小写排序
		给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序
	 * @param chars
	 */
	public void sortLetters(char[] chars) {
        // write your code here
		
		if (chars==null||chars.length==0) {
			return;
		}
		
		int left=0;
		int right=chars.length-1;
		
		while(left<=right) {
			while(left<=right&&chars[left]>'Z') {
				left++;
			}
			while(left<=right&&chars[right]<='Z') {
				right--;
			}
			
			if (left<=right) {
				swip(chars, left, right);
				left++;
				right--;
			}
		}
    }
	
	/**
	 * 148. 颜色分类
	 * 给定一个包含红，白，蓝且长度为 n 的数组，
	 * 将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。

		我们可以使用整数 0，1 和 2 分别代表红，白，蓝。
	 * @param nums
	 */
	public void sortColors1(int[] nums) {
        // write your code here
		partitionArray(nums, 1);
		partitionArray(nums, 2);
    }
	
	
	public void sortColors12(int[] nums) {
        // write your code here

		if (nums==null||nums.length==0) {
			return ;
		}
		//代表0的右侧
		int p0=0;
		//代表2的左侧
		int p2=nums.length-1;
		
		int cur=0;
		
		while(cur<=p2) {
			//每次都考察nums[cur]，每次只考察一个，所以是if else的关系
			//由于要走指针，所以每次if都需要范围限制
			if (p0<=cur&&nums[cur]==0) {
				swip(nums, p0, cur);
				p0++;
				
			}else if (cur<=p2&&nums[cur]==2) {
				swip(nums, cur, p2);
				p2--;
				
			}else {
				cur++;
			}
			
			
		}
		
    }
	
	/**
	 * lintcode 143. Sort Colors II
	给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，
	将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序。
	
	这是O(K*N)
	 * @param colors
	 * @param k
	 */
	public void sortColors2(int[] colors, int k) {
        // write your code here
		if (colors==null||colors.length==0) {
			return ;
		}
		
		
		for (int i = 1; i < k; i++) {
			partitionArray(colors, i+1);
		}
    }
	
	/**
	 * lintcode 143. Sort Colors II
	 * 本质还是对数组进行排序，所以是快排，但是由于数组是有范围的，所以在分治时，可以加速到O(logK)
	 * @param colors
	 * @param k
	 */
	public void sortColors22(int[] colors, int k) {
		
		if (colors==null||colors.length==0) {
			return;
		}
		
		quickSort(colors, 1, k,0,colors.length-1);
	}
	
	
	public void quickSort(int[] colors, int colorFrom, int colorTo, int start, int end) {

		// 不适用partition方式，需要在这里重新定义变量
		int left = start;
		int right = end;

		if (left >= right || colorFrom >= colorTo) {
			return;
		}

		int mid = (colorTo - colorFrom) / 2 + colorFrom;

		while (left <= right) {
			//因为mid 是向下取整的 所以等于的在左边 和 mid同侧
			// 这应该就是下标和值的不同
			while (left <= right && colors[left] <= mid) {
				left++;
			}
			while (left <= right && colors[right] > mid) {
				right--;
			}

			if (left <= right) {
				swip(colors, left, right);
				left++;
				right--;
			}
		}

		quickSort(colors, colorFrom, mid, start, right);

		//由于是向下取整，所以mid + 1
		quickSort(colors, mid + 1, colorTo, left, end);
	}
	
	
	
	
	public static void swip(char[]nums,int i,int j) {
		char temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
	
	public static void swip(int[]nums,int i,int j) {
		int temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
	
	/**
	 * 5. 第k大元素
		在数组中找到第 k 大的元素。
		要求时间复杂度为O(n)，空间复杂度为O(1)。
	 * @param n
	 * @param nums
	 * @return
	 */
	public int kthLargestElement(int k, int[] nums) {
        // write your code here
		if (nums==null||nums.length==0|| k < 1 || k > nums.length) {
			return -1;
		}
		
		int partition=sort(k, nums, 0, nums.length-1);
		
		return nums[partition];
    }
	
	public int sort(int k, int[] nums,int left,int right) {
		
		if (left>=right) {
			return left;
		}
		
		int partition=partition(k,nums,left,right);
		
		if (partition<nums.length-k) {
			//这是递归，要返回值，否则partition进了递归再出来，没有任何改变
			return sort(k,nums,partition,right);
		}else if(partition>nums.length-k){
			return sort(k,nums,left,partition-1);
		}
		return partition;
	}
	
	public int partition(int k, int[] nums,int left,int right) {
		
		
		
		int mid=left+(right-left)/2;
		int pivot=nums[mid];
		
		while(left<=right) {
			
			while(left<=right&&nums[left]<pivot) {
				left++;
			}
			
			while(left<=right&&nums[right]>pivot) {
				right--;
			}
			
			if (left<=right) {
				int temp=nums[left];
				nums[left]=nums[right];
				nums[right]=temp;
				left++;
				right--;
			}
		}
		
		return left;
	}
	

	
}
