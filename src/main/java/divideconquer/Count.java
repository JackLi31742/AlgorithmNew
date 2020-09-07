package divideconquer;

import java.util.ArrayList;
import java.util.List;

import com.sun.imageio.plugins.common.I18NImpl;

public class Count {
	
	
	public static void main(String[] args) {
		List<Integer> result=new ArrayList<Integer>(2);
		
//		System.out.println(result.get(0));;
		int[]nums= {1,3,2,3,1};
		System.out.println(reversePairs(nums));;
	}
	
	/**
	 * 剑指 Offer 51. 数组中的逆序对
	 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
	 * @param nums
	 * @return
	 */
	static int count=0;
	public static int reversePairs(int[] nums) {

		if (nums==null||nums.length==0) {
			return 0;
		}
		
		
		mergeSort(nums, 0, nums.length-1);
		return count;
    }
	
	public static void mergeSort(int[] nums,int left,int right) {
		if (left<right) {
			int mid=(right-left)/2+left;
			mergeSort(nums, left, mid);
			mergeSort(nums, mid+1, right);
			mergeCount(nums, left,mid, right);
		}
	}
	
	
	public static void mergeCount(int[] nums,int left,int mid,int right) {
		int i=left;
		int j=mid+1;
		
		int[]temp=new int[right-left+1];
		
		int t1=0;
		
		while(i<=mid&&j<=right) {
			if (nums[i]>nums[j]) {
				//在这会重复计算
//				count+=j-(mid+1)+1;
				//以j指向的元素作为参考，进行计算，只要nums[j]要进到temp数组，那么从i到mid，所有的都是逆序对
				count+=mid-i+1;
				temp[t1]=nums[j];
				j++;
			}else {
				temp[t1]=nums[i];
				i++;
			}
			t1++;
		}
		
		//在上边时已经计算过，这里就不用再次计算
		while(i<=mid) {
			temp[t1]=nums[i];
			i++;
			t1++;
		}
		while(j<=right) {
			temp[t1]=nums[j];
			j++;
			t1++;
		}
		
		int t2=0;
		while(left<=right) {
			nums[left]=temp[t2];
			t2++;
			left++;
		}
	}

	/**
	 * 315. 计算右侧小于当前元素的个数
	 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
	 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。


		输入：nums = [5,2,6,1]
		输出：[2,1,1,0] 
		
		利用归并排序
	 * @param nums
	 * @return
	 */
	public static List<Integer> countSmaller(int[] nums) {
		List<Integer> result=new ArrayList<Integer>();
		if (nums==null||nums.length==0) {
			return result;
		}
		for (int i = 0; i < nums.length; i++) {
			result.add(0);
		}
		if (nums.length==1) {
			return result;
		}
		//只开辟一个就够了，比重复开辟消耗小
		int[]temp=new int[nums.length];
		//由于排序过程会导致下标发生改变，需要记录原来的下标
		int[]index=new int[nums.length];
		for (int i = 0; i < index.length; i++) {
			index[i]=i;
		}
		countSmaller(nums, 0, nums.length-1, result, temp,index);
		return result;
    }
	
	
	public static void countSmaller(int[] nums,int left,int right,
			List<Integer> result,int[] temp,int[]index) {
		if (left<right) {
			int mid=(right-left)/2+left;
			countSmaller(nums, left, mid, result, temp,index);
			countSmaller(nums, mid+1, right, result, temp,index);
			count(nums, left,mid, right, result, temp,index);
		}	
	}
	
	public static void count(int[] nums,int left,int mid,int right,
			List<Integer> result,int[] temp,int[]index) {
		//左边起始
		int i=left;
		//右边起始
		int j=mid+1;
		int t1=0;
		int t2=0;
		while(i<=mid&&j<=right) {
			if (nums[i]>nums[j]) {
				result.set(i, (result.get(i)+1));
				temp[t1]=nums[j];
				j++;
			}else {
				temp[t1]=nums[i];
				i++;
			}
			t1++;
		}
		
		while(i<=mid) {
			temp[t1]=nums[i];
			i++;
			t1++;
		}
		while(j<=right) {
			temp[t1]=nums[j];
			j++;
			t1++;
		}
		
		while(left<=right) {
			nums[left]=temp[t2];
			t2++;
			left++;
		}
		
	}
}
