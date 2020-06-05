package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Traversal {

	public static void main(String[] args) {
		TreeNode node1=new TreeNode(3);
		TreeNode node2=new TreeNode(1);
		TreeNode node3=new TreeNode(2);
		TreeNode node4=new TreeNode(1);
		
		node1.left=node2;
		node1.right=node3;
//		node3.left=node4;
		
		System.out.println(postorderTraversal2(node1));;
		
	}
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
	public static List<Integer> preorderTraversal2(TreeNode root) {

		List<Integer> result=new ArrayList<Integer>();
		if (root==null) {
			return result;
		}
		
		
		TreeNode cur=root;
		Stack<TreeNode> stack=new Stack<TreeNode>();
		if(cur!=null) {
			result.add(cur.val);
			if (cur.right!=null) {
				stack.push(cur.right);
			}
			
			if (cur.left!=null) {
				stack.push(cur.left);
			}
			
			while(!stack.isEmpty()) {
				TreeNode node=stack.pop();
				result.add(node.val);
				if (node.right!=null) {
					stack.push(node.right);
				}
				
				if (node.left!=null) {
					stack.push(node.left);
				}
			}
			
		}
		
		return result;
    }
	
	//只放右节点
	public static List<Integer> preorderTraversal3(TreeNode root) {

		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		//第一次把root放进去，是为了好进第一个while循环，因为当节点走到树的右侧时，其实和左侧的套路一样
		stack.push(cur);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			while (node != null) {
				result.add(node.val);
				if (node.right != null) {
					stack.push(node.right);
				}
				node = node.left;
			}
		}

		return result;
	}
	
	/**
	 * 中序遍历
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		inorderTraversal(root,result);
		return result;
    }
	
	public static List<Integer> inorderTraversal(TreeNode root,List<Integer> result) {
		if (root==null) {
			return result;
		}
		inorderTraversal(root.left, result);
		result.add(root.val);
		inorderTraversal(root.right, result);
		return result;
	}
	
	//迭代
	public static List<Integer> inorderTraversal2(TreeNode root) {
		
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();

		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			TreeNode node = stack.pop();
			result.add(node.val);

			cur = node.right;
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}

		}

		return result;
		
	}
	
	public static List<Integer> inorderTraversal3(TreeNode root) {
		
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();

		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}else {
				
				TreeNode node = stack.pop();
				result.add(node.val);
				
				cur = node.right;
			}
			

		}

		return result;
		
	}
	
	/**
	 * 后序遍历
	 */
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result=new ArrayList<Integer>();
		
		postorderTraversal(root, result);
		return result;
    }
	
	public static List<Integer> postorderTraversal(TreeNode root,List<Integer> result) {
		if (root==null) {
			return result;
		}
		postorderTraversal(root.left, result);
		postorderTraversal(root.right, result);
		result.add(root.val);
		return result;
	}
	
	//迭代
	public static  List<Integer> postorderTraversal2(TreeNode root) {
		List<Integer> result=new ArrayList<Integer>();
		if (root==null) {
			return result;
		}
		TreeNode cur=root;
		
		Stack<TreeNode> stack=new Stack<TreeNode>();
		
		stack.push(cur);
		
		while(cur!=null) {
			
			while (cur.left!=null) {
				stack.push(cur.left);
				cur=cur.left;
			}
			if (cur.right!=null) {
				stack.push(cur.right);
				cur=cur.right;
			}else {
				
				if(!stack.isEmpty()) {
					result.add(stack.pop().val);
				}
			}
			
		}
		return result;
	}
}
