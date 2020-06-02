package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Traversal {

	/**
	 * 144. 二叉树的前序遍历
	 * 
	 */
	//递归
	public List<Integer> preorderTraversal(TreeNode root) {

		List<Integer> result=new ArrayList<Integer>();
		if (root==null) {
			return result;
		}
		
		result.add(root.val);
		preorderTraversal(root.left, result);
		preorderTraversal(root.right, result);
		return result;
    }
	
	public List<Integer> preorderTraversal(TreeNode node,List<Integer> result) {
		if (node==null) {
			return result;
		}
		result.add(node.val);
		preorderTraversal(node.left, result);
		preorderTraversal(node.right, result);
		return result;
		
	}
	
	//迭代需要借助栈
	public List<Integer> preorderTraversal2(TreeNode root) {

		List<Integer> result=new ArrayList<Integer>();
		if (root==null) {
			return result;
		}
		
		
		TreeNode cur=root;
		Stack<Integer> stack=new Stack<Integer>();
		while(cur!=null) {
			result.add(cur.val);
			if (cur.right!=null) {
				stack.add(cur.right.val);
			}
			if (cur.left!=null) {
				cur=cur.left;
			}
		}
		
		return result;
    }
	
}
