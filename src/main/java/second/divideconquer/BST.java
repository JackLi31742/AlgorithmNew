package second.divideconquer;

import java.util.List;
import java.util.Stack;

import tree.TreeNode;

public class BST {
	
	public static void main(String[] args) {
		
		TreeNode node1=new TreeNode(2);
		TreeNode node2=new TreeNode(1);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(11);
		TreeNode node5=new TreeNode(20);
		TreeNode node6=new TreeNode(6);
		node1.left=node2;
		node1.right=node3;
		
//		node3.left=node4;
//		node3.right=node5;
//		node5.right=node6;
		
		BST bst=new BST();
		bst.inorderPredecessor(node1, node2);
		
	}
	
	/**
	 * 95. 验证二叉查找树
	 * 给定一个二叉树，判断它是否是合法的二叉查找树(BST)

	一棵BST定义为：
	
	节点的左子树中的值要严格小于该节点的值。
	节点的右子树中的值要严格大于该节点的值。
	左右子树也必须是二叉查找树。
	一个节点的树也是二叉查找树。
	
	
	不能仅仅判断节点和它的左右子节点，因为在左右子树中，有可能左子树的最大值，右子树的最小值会导致非法
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
        // write your code here
		Result3 result3 = isValid(root);
		return result3.isValid;
    }
	
	public Result3 isValid(TreeNode root) {
		
		if (root==null) {
			return new Result3(true);
		}
		
		Result3 left = isValid(root.left);
		
		Result3 right = isValid(root.right);
		
		//先判断非法的，符合合法的条件比较多
		
		if (!left.isValid||!right.isValid) {
			return new Result3(false);
		}
		
		if (left.maxNode!=null&&left.maxNode.val>=root.val) {
			return new Result3(false);
		}
		
		if (right.minNode!=null&&right.minNode.val<=root.val) {
			return new Result3(false);
		}
		
		//以整体看待这三个节点
		Result3 result3=new Result3(true);
		
		
//		if (right.maxNode!=null) {
//			result3.maxNode=right.maxNode;
//		}else if (right.minNode!=null) {
//			result3.maxNode=right.minNode;
//		}else {
//			result3.maxNode=root;
//		}
//		
//		if (left.minNode!=null) {
//			result3.minNode=left.minNode;
//		}else if (left.maxNode!=null) {
//			result3.minNode=left.maxNode;
//		}else {
//			result3.minNode=root;
//		}
		//right.maxNode 和right.minNode是同时存在的，所以不需要上述的多个if else 判断
		result3.minNode = left.minNode != null ? left.minNode : root;
        result3.maxNode = right.maxNode != null ? right.maxNode : root;
		return result3;
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
	 * @param root
	 * @param target
	 * @param k
	 * @return
	 */
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
		
		
    }
}



class Result3{
	
	boolean isValid;
	//用是否为null判断，比用isLeft,LeftMax这样的boolean和int要好
	TreeNode maxNode;
	TreeNode minNode;
	
	public Result3(boolean isValid) {
		this(isValid,null,null);
	}
	
	
	public Result3(boolean isValid, TreeNode maxNode, TreeNode minNode) {
		super();
		this.isValid = isValid;
		this.maxNode = maxNode;
		this.minNode = minNode;
	}


	@Override
	public String toString() {
		return "Result3 [isValid=" + isValid + ", maxNode=" + maxNode + ", minNode=" + minNode + "]";
	}
	
}
