package design;

import java.util.Stack;

import tree.TreeNode;

/**
 * 173. 二叉搜索树迭代器
 * lintcode 86. 二叉查找树迭代器
 *	实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

 中序遍历顺序
 
 Traversal.java中的inorderTraversal2方法
 
 因为next和hashnext分开了，不能用inorderTraversal3
 */
public class BSTIterator {
	
	public Stack<TreeNode> stack;

	public BSTIterator(TreeNode root) {

		stack=new Stack<TreeNode>();
		findMostLeft(root);
    }
    
	//每次执行指针都会动
    /** @return the next smallest number */
    public TreeNode next() {
    	TreeNode result = null;
    	if (hasNext()) {
			result=stack.pop();
			findMostLeft(result.right);
		}
    	return result;
    }
    
    public void findMostLeft(TreeNode node) {
    	TreeNode cur=node;
    	while (cur!=null) {
			stack.push(cur);
			cur=cur.left;
		}
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {

    	return !stack.isEmpty();
    }
}
