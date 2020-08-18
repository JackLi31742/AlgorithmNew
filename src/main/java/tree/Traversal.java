package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.sun.media.sound.RIFFInvalidDataException;

public class Traversal {

	public static void main(String[] args) {
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(4);
		TreeNode node5=new TreeNode(5);
		
		node1.left=node2;
		node1.right=node3;
		node2.left=node4;
		node3.right=node5;
		
//		System.out.println(postorderTraversal2(node1));;
		System.out.println(levelOrder(node1));
		
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
		
		Stack<Node> stack=new Stack<Node>();
		
		while(cur!=null||!stack.isEmpty()) {
			
			//一直找到左子树的最左边
			while (cur!=null) {
				Node node=new Node(cur, false);
				stack.push(node);
				cur=cur.left;
			}
			
			if (!stack.isEmpty()) {
				if (!stack.peek().flag) {
					cur=stack.peek().treeNode.right;
					//标明从左边的子树返回到了根节点
					stack.peek().flag=true;
					
				}else {
					result.add(stack.pop().treeNode.val);
					
				}
			}
			
			
		}
		return result;
	}
	
	/**
	 * 102. 二叉树的层序遍历
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		if (root==null) {
			return result;
		}
		Queue<TreeNode> queue=new ArrayDeque<TreeNode>();
		queue.add(root);
//		List<Integer> first=new ArrayList<Integer>();
//		first.add(root.val);
//		result.add(first);
		while(!queue.isEmpty()) {
			int size=queue.size();
			List<Integer> list=new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode node=queue.poll();
				list.add(node.val);
//			System.out.print(node.val+" ");
				if (node.left!=null) {
//					list.add(node.left.val);
					queue.add(node.left);
				}
				if (node.right!=null) {
//					list.add(node.right.val);
					queue.add(node.right);
				}
				
			}
//			if (list.size()>0) {
				
				result.add(list);
//			}
		}
		
		return result;
    }
}
