package binarysearch;

public class InsertValueSearch {

	
	public static void main(String[] args) {
		int[]arr={1,8, 10, 89, 1000, 1000,1234};
		
		System.out.println(search(arr,1000));;
	}

	
	public static int search(int[] nums, int target) {
		if (nums==null||nums.length==0) {
			return -1;
		}

		return search(nums, 0, nums.length-1, target);
    }
	
	public static int search(int[] nums, int left, int right, int target) {
		// 小心边界问题
		if (left > right || target < nums[0] || target > nums[nums.length - 1]) {
			return -1;
		}
		
		int mid =0;
		if (left==right) {
			mid=left;
		}else {
			
			mid = left + (right - left) * (target - nums[left]) / (nums[right] - nums[left]);
		}
		if (target > nums[mid]) {
			return search(nums, mid + 1, right, target);
		} else if (target < nums[mid]) {
			return search(nums, left, mid - 1, target);
		} else {
			return mid;
		}

	}
	
}
