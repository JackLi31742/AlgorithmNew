package tree.second;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.print.attribute.standard.PagesPerMinute;

import com.sun.org.apache.bcel.internal.generic.I2F;

import tree.TreeNode;

public class BST {
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(2);
		TreeNode node2=new TreeNode(1);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(35);
		TreeNode node5=new TreeNode(4);
		
		node1.left=node2;
		node1.right=node3;
		
//		node3.left=node4;
//		node2.right=node5;
		
		
		BST bst=new BST();
		
//		Stack<TreeNode> stack = bst.getStack(node1, 8);
//		System.out.println(stack);
//		bst.insertNode(node1, node3);
		
		bst.removeNode(node1, 20);
		
		bst.closestKValues(node1, 5, 2);
	}

	/**
	 * 85. 在二叉查找树中插入节点
	 * 给定一棵二叉查找树和一个新的树节点，将节点插入到树中。

	你需要保证该树仍然是一棵二叉查找树。
	 * @param root
	 * @param node
	 * @return
	 */
	public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
		if (root==null) {
			return node;
		}
		
		TreeNode cur=root;
		
		TreeNode pre=null;
		
		while(cur!=null) {
			
			pre=cur;
			
			if (cur.val>node.val) {
				cur=cur.left;
			}else {
				cur=cur.right;
			}
		}
		
		if (pre.val>node.val) {
			pre.left=node;
		}else {
			pre.right=node;
		}
		
		return root;
    }
	
	/**
	 * 87. 删除二叉查找树的节点
		给定一棵具有不同节点值的二叉查找树，删除树中与给定值相同的节点。
		如果树中没有相同值的节点，就不做任何处理。你应该保证处理之后的树仍是二叉查找树。
	 * @param root
	 * @param value
	 * @return
	 */
	public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
		if (root==null) {
			return null;
		}
		
		TreeNode cur=root;
		TreeNode pre=root;
		
		if (cur.val==value) {
			return remove(cur, value);
		}
		
		dfs(cur, pre, value);
		
		return cur;
    }
	
	public void dfs(TreeNode cur,TreeNode pre, int value) {
		
		if (cur==null) {
			return ;
		}
		
		if (pre.left==cur) {
			if (cur.val==value) {
				
				pre.left=remove(cur, value);
				return;
			}
		}
		
		if(pre.right==cur){
			if (cur.val==value) {
				pre.right=remove(cur, value);;
				return;
			}
		}
		
		if (cur.val>value) {
			dfs(cur.left, cur,value);
			return;
		}

		if (cur.val<value) {
			dfs(cur.right,cur, value);
			return ;
		}
		
		
	}
	
	public TreeNode remove(TreeNode cur, int value) {
		
		if (cur.left!=null&&cur.right!=null) {
			if (cur.right.left!=null) {
				
				cur.left.right=cur.right;
				cur=cur.left;
			}else {
				
				cur.right.left=cur.left;
				cur=cur.right;
			}
			
		}else if (cur.left!=null) {
			cur=cur.left;
		}else {
			cur=cur.right;
		}
		
		return cur;
	}
	
	/**
	 * dummy 
	 * @param root
	 * @param value
	 * @return
	 */
	public TreeNode removeNode2(TreeNode root, int value) {
		
		if (root==null) {
			return null;
		}
		
		TreeNode dummy=new TreeNode(-1);
		
		dummy.left=root;
		TreeNode cur=root;
		
		removeNode2(cur, dummy, value);
		
		return dummy.left;
	}
	
	public void removeNode2(TreeNode cur, TreeNode parent,int value) {
		
		if (cur==null) {
			return ;
		}
		
		if (parent.left==cur) {
			if (cur.val==value) {
				
				parent.left=remove(cur, value);
				return;
			}
		}
		
		if(parent.right==cur){
			if (cur.val==value) {
				parent.right=remove(cur, value);;
				return;
			}
		}
		
		if (cur.val>value) {
			removeNode2(cur.left, cur,value);
			return;
		}

		if (cur.val<value) {
			removeNode2(cur.right,cur, value);
			return ;
		}
	}
	
	
	/**
	 * 11. 二叉查找树中搜索区间
		给定一个二叉查找树和范围[k1, k2]。按照升序返回给定范围内的节点值。
	 * @param root
	 * @param k1
	 * @param k2
	 * @return
	 */
	public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
		
		List<Integer> result =new ArrayList<Integer>();
		
		TreeNode cur =root;
		
		searchRange(cur, k1, k2, result);
		
		return result;
    }
	
	public void searchRange(TreeNode root, int k1, int k2,List<Integer> result) {
		
		if (root==null) {
			
			return ;
		}
		
		if (root.val>k2) {
			
			searchRange(root.left, k1, k2, result);
			return ;
		}
		
		if (root.val<k1) {
			
			searchRange(root.right, k1, k2, result);
			return ;
		}
		
		result.add(root.val);
		searchRange(root.left, k1, k2, result);
		searchRange(root.right, k1, k2, result);
	}
	
	/**
	 * 902. BST中第K小的元素
	 * 给一棵二叉搜索树，写一个 KthSmallest 函数来找到其中第 K 小的元素。
	 * 
	 * 时间复杂度分析
	O(k + h)
	当 k 是 1 的时候 ==> O(h)
	当 k 是 n 的时候 ==> O(n)
	k和h两者取大值
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {
        // write your code here
		
		if (root==null||k==0) {
			return -1;
		}
		
		Stack<TreeNode> stack=new Stack<TreeNode>();
		
		int index=1;
		int result=-1;
		TreeNode cur=root;
		
		while(cur!=null || !stack.isEmpty()) {
			
			if (cur!=null) {
				stack.push(cur);
				cur=cur.left;
			}else {
				TreeNode node = stack.pop();
				//先处理当前的，再++
				if (index==k) {
					result=node.val;
					break;
				}
				
				index++;
				cur=node.right;
			}
			
		}
		
		return result;
    }
	
	/**
	 * 915. BST的中序前驱节点
	 * 给出一棵二叉搜索树以及其中的一个节点，找到这个节点在这棵树中的中序前驱节点。
	 * @param root
	 * @param p
	 * @return
	 */
	public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
	        // write your code here
		if (root==null||p==null) {
			return null;
		}
		
		Stack<TreeNode> stack=new Stack<TreeNode>();
		
		TreeNode cur=root;
		
		TreeNode pre=null;
		
		while(cur!=null||!stack.isEmpty()) {
			
			if(cur!=null) {
				stack.push(cur);
				cur=cur.left;
			}else {
				TreeNode node = stack.pop();
				//在pop的时候才是正在的中序，push的时候不是
				if (node==p) {
					break;
				}else {
					pre=node;
				}
				cur=node.right;
			}
			
			
		}
		
		return pre;
	}
	/**
	 * 900. 二叉搜索树中最接近的值
		给一棵非空二叉搜索树以及一个target值，找到在BST中最接近给定值的节点值

		给出的目标值为浮点数
		我们可以保证只有唯一一个最接近给定值的节点
	 * @param root
	 * @param target
	 * @return
	 */
	public int closestValue(TreeNode root, double target) {
        // write your code here
		
		TreeNode cur=root;
		double dif=0;
		double min=Integer.MAX_VALUE;
		TreeNode result=null;
		while(cur!=null) {
			
			dif=Math.abs(target-cur.val);
			if (dif<min) {
				min=dif;
				result=cur;
			}
			if (cur.val<target) {
				cur=cur.right;
			}else {
				cur=cur.left;
			}
		}
		
		return result.val;
    }
	
	/**
	 * 901. 二叉搜索树中最接近的值 II
		给定一棵非空二叉搜索树以及一个target值，找到 BST 中最接近给定值的 k 个数。
		给出的target值为浮点数
		你可以假设 k 总是合理的，即 k ≤ 总节点数
		我们可以保证给出的 BST 中只有唯一一个最接近给定值的 k 个值的集合
		
		
		用两个迭代器，从target的位置开始，一个向前遍历树，一个向后遍历
	 * @param root
	 * @param target
	 * @param k
	 * @return
	 */
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
		
		List<Integer> result=new ArrayList<Integer>();
		
		if (root==null||k==0) {
			return result;
		}
		
		Stack<TreeNode> upper = getStack(root, target);
		
		Stack<TreeNode> lowwer = new Stack<TreeNode>();
		
		lowwer.addAll(upper);
		
		if (target<lowwer.peek().val) {
			//为了靠近target，往更小的方向移动
			moveLowwer(lowwer);
		}else {
			moveUpper(upper);
		}
		
		int index=0;
		while(!lowwer.isEmpty()&&!upper.isEmpty()&&index<k) {
			
			if (isLeftCloser(target, upper, lowwer)) {
				result.add(lowwer.peek().val);
				moveLowwer(lowwer);
			}else {
				
				result.add(upper.peek().val);
				moveUpper(upper);
			}
			index++;
		}
		
		while (index<k) {
			
			if (!lowwer.isEmpty()) {
				result.add(lowwer.peek().val);
				moveLowwer(lowwer);
			}
			
			if (!upper.isEmpty()) {
				result.add(upper.peek().val);
				moveUpper(upper);
			}
			index++;
		}
		
		return result;
    }
	
	public void moveUpper(Stack<TreeNode> upper) {
		
		TreeNode node = upper.peek();
		
		if (node.right==null) {
			
			node=upper.pop();
			//一直找到栈空或者找到左子树
			while(!upper.isEmpty()&&upper.peek().right==node) {
				node=upper.pop();
			}
		}else {
			node=node.right;
			while(node!=null) {
				upper.push(node);
				node=node.left;
			}
		}
		
		
	}
	
	public void moveLowwer(Stack<TreeNode> lowwer) {
		
		TreeNode node=lowwer.peek();
		
		if (node.left==null) {
			
			node=lowwer.pop();
			while(!lowwer.isEmpty()&&lowwer.peek().left==node) {
				
				node=lowwer.pop();
			}
		}else {
			node=node.left;
			while(node!=null) {
				
				lowwer.push(node);
				node=node.right;
			}
		}
	}
	
	/**
	 * 是否左边的距离target更近，如果左边近，返回true，右边近，返回false
	 * @param target
	 * @param upper
	 * @param lowwer
	 * @return
	 */
	public boolean isLeftCloser(double target,Stack<TreeNode> upper,Stack<TreeNode> lowwer) {
		
		TreeNode up = upper.peek();
		
		TreeNode low = lowwer.peek();
		
		if (Math.abs(up.val-target)<Math.abs(low.val-target)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 	找到了插入target的路线
	 * @param root
	 * @param target
	 * @return
	 */
	public Stack<TreeNode> getStack(TreeNode root, double target){
		
		Stack<TreeNode> stack=new Stack<TreeNode>();
		
		TreeNode cur=root;
		while(cur!=null) {
			
			stack.push(cur);
			if (cur.val>target) {
				cur=cur.left;
			}else {
				cur=cur.right;
			}
		}
		
		return stack;
	}
}
