package twopoint;

import java.util.Arrays;

public class SortColors {
	
	public static void main(String[] args) {
		int[] nums= {2,0,2,1,1,0};
		
		sortColors(nums);
		
		System.out.println(Arrays.toString(nums));
	}

	/**
	 * 75. 颜色分类
	 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
	 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

		此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

		两次partition，快速选择算法

	 * @param nums
	 */
	
	//这不是两次partition
	public static void sortColors(int[] nums) {

		//代表0的右边界
		int p0=0;
		//代表当前
		int cur=0;
		//代表2的左边界
		int p2=nums.length-1;
		
		while(cur<=p2) {
			//需要在if里加指针的边界控制，否则会一直在正确的下标打转
			if (cur>=p0&&nums[cur]==0) {
				swip(nums, cur, p0);
				p0++;
			}else if (cur<=p2&&nums[cur]==2) {
				swip(nums, cur, p2);
				p2--;
			}else {
				//在交换之后不移动cur，因为不知道交换了之后的值是什么情况，所以需要再判断一次
				cur++;
			}
		}
		
    }
	
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
