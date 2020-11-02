package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.TreeNode;

public class BST {
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(2);
		TreeNode node2=new TreeNode(1);
		TreeNode node3=new TreeNode(9);
		TreeNode node4=new TreeNode(2);
		TreeNode node5=new TreeNode(8);
		TreeNode node6=new TreeNode(10);
		
		node1.left=node2;
//		node1.right=node3;
//		node2.left=node4;
//		node3.left=node5;
//		node3.right=node6;
		
		
//		System.out.println(isValidBST(node1));;
		
		BST bst=new BST();
		System.out.println(bst.closestValue(node1, 2147483647.0));;
	}
	
	
	
	/**
	 * lintcode 11. 二叉查找树中搜索区间
	 * 给定一个二叉查找树和范围[k1, k2]。按照升序返回给定范围内的节点值。
	 */
	
	public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
    }
	
	/**
	 * 689. 两数之和 - BST版本
	 * 给一棵二叉搜索树以及一个整数 n, 在树中找到和为 n 的两个数字
	 * @param root
	 * @param n
	 * @return
	 */
	public int[] twoSum(TreeNode root, int n) {
        // write your code here
    }
	
	/**
	 * 701. 修剪二叉搜索树
	 * 给定一个有根的二分搜索树和两个数字min和max，
	 * 修整这个树使得所有的数字在这个新的树种都是在min和max之间（包括min和max）。
	 * 然后这个所得的树仍然是合法的二分搜索树。
	 * @param root
	 * @param minimum
	 * @param maximum
	 * @return
	 */
	public TreeNode trimBST(TreeNode root, int minimum, int maximum) {
        // write your code here
    }
	
	
	/**
	 * 98. 验证二叉搜索树
	 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
	 * 
	 * 利用中序遍历
	 * @param root
	 * @return
	 */
	public static boolean isValidBST(TreeNode root) {
		if (root==null) {
			return true;
		}
		List<Integer> list=new ArrayList<Integer>();
		inOrderTraversal(root, list);

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i)<=list.get(i-1)) {
				return false;
			}
		}
		
		return true;
    }
	
	
	public static void inOrderTraversal(TreeNode root,List<Integer> list) {
		if (root!=null) {
			
			inOrderTraversal(root.left, list);
			list.add(root.val);
			inOrderTraversal(root.right, list);
		}
	}
	
	/**
	 * 非递归
	 * @param root
	 * @return
	 */
	public static boolean isValidBST2(TreeNode root) {
		if (root==null) {
			return true;
		}
		List<Integer> list=new ArrayList<Integer>();
		
		Stack<TreeNode> stack=new Stack<TreeNode>();
		TreeNode cur=root;
		while(cur!=null||!stack.isEmpty()) {
			while (cur!=null) {
				stack.push(cur);
				cur=cur.left;
			}
			TreeNode node=stack.pop();
			if (list.size()>0&&list.get(list.size()-1)>=node.val) {
				return false;
			}
			list.add(node.val);
			cur=node.right;
			
		}
		
		return true;
	}
	
	/**
	 * lintcode 900. 二叉搜索树中最接近的值
	 * 给一棵非空二叉搜索树以及一个target值，找到在BST中最接近给定值的节点值
	 * 给出的目标值为浮点数
		我们可以保证只有唯一一个最接近给定值的节点
	 * @param root
	 * @param target
	 * @return
	 */
	public int closestValue(TreeNode root, double target) {
        // write your code here
		List<Integer> list=getInOrderList(root);
		
		
		int index = findIndex(list, target);
		
		//先讨论特殊情况
		if (index==list.size()) {
			
			return list.get(list.size()-1);
		}
		
		if (index==0) {
			return list.get(index);
		}
		
		
			
		double d1=target-list.get(index-1);
		double d2=list.get(index)-target;
//			double d3=list.get(index+1)-target;
		
		return d1>d2?list.get(index):list.get(index-1);
		
		
		
    }
	
	//得到中序遍历结果
	public List<Integer> getInOrderList(TreeNode root){
		
		List<Integer> list=new ArrayList<>();
		if (root==null) {
			return list;
		}
		
		Stack<TreeNode> stack=new Stack<>();
		
		TreeNode curNode=root;
		
		while(curNode!=null||!stack.isEmpty()) {
			if(curNode!=null) {
				stack.push(curNode);
				curNode=curNode.left;
			}else {
				
				TreeNode node=stack.pop();
				list.add(node.val);
				
				curNode=node.right;
			}
			
			
		}
		
		return list;
	}
	
	//找到插入的位置
	public int findIndex(List<Integer> list,double target) {
		
		int left=0; 
		int right=list.size()-1;
		
		while(left <= right) {
			int mid=left+(right-left)/2;
			
			if (list.get(mid)==target) {
				return mid;
			}else if (list.get(mid)<target) {
				left=mid+1;
			}else {
				right=mid-1;
			}
			
		}
		
		//结果偏右
		return left;
	}
	/**
	 * lintcode 901. 二叉搜索树中最接近的值 II
	给定一棵非空二叉搜索树以及一个target值，找到 BST 中最接近给定值的 k 个数。
	
		给出的target值为浮点数
		你可以假设 k 总是合理的，即 k ≤ 总节点数
		我们可以保证给出的 BST 中只有唯一一个最接近给定值的 k 个值的集合
	 * @param root
	 * @param target
	 * @param k
	 * @return
	 */
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
		
		
		List<Integer> list=getInOrderList(root);
		
		if (k>=list.size()) {
			return list;
		}
		
		List<Integer> result=new ArrayList<>();
		
		if (k<=0) {
			return result;
		}
		
		int index = findIndex(list, target);
		
		/**
		 * 类似合并两个有序数组
		 */
		int count=0;
		
		int len=list.size();
		
		int left=0,right=0;
		
		while(count<k&&left<index&&right<(len-index)) {
			
			double a=target-list.get(index-1-left);
			
			double b=list.get(index+right)-target;
			
			if (a<b) {
				result.add(list.get(index-1-left));
				left++;
			}else {
				result.add(list.get(index+right));
				right++;
			}
			
			count++;
		}
		
		if (count<k) {
			
			while(count<k&&left<index) {
				result.add(list.get(index-1-left));
				left++;
				count++;
			}
			
			while(count<k&&right<(len-index)) {
				result.add(list.get(index+right));
				right++;
				count++;
			}
		}
		
		
		return result;
    }
}
