package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.TreeNode;

public class BST {
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(10);
		TreeNode node2=new TreeNode(5);
		TreeNode node3=new TreeNode(15);
		TreeNode node4=new TreeNode(6);
		TreeNode node5=new TreeNode(20);
		
		node1.left=node2;
		node1.right=node3;
		node3.left=node4;
		node3.right=node5;
		
		
		System.out.println(isValidBST(node1));;
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
    }
}
