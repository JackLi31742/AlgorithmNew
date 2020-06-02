package design;

import java.util.Arrays;

/**
 * 705. 设计哈希集合
 * add(value)：向哈希集合中插入一个值。
	contains(value) ：返回哈希集合中是否存在这个值。
	remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做
 *
 */
public class MyHashSet {
	NodeLinkedList[] nodeLinkedLists;
	int capacity;
	/** Initialize your data structure here. */
    public MyHashSet() {
    	capacity=16;
    	nodeLinkedLists=new NodeLinkedList[capacity];
    }
    
    public void add(int key) {
    	int num=hash(key);
    	if (nodeLinkedLists[num]==null) {
			
    		nodeLinkedLists[num]=new NodeLinkedList();
		}
    	nodeLinkedLists[num].add(key);
    	
    }
    
    public void remove(int key) {
    	int num=hash(key);
    	if (nodeLinkedLists[num]==null) {
			
    		return;
		}
    	nodeLinkedLists[num].remove(key);
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
    	int num=hash(key);
    	if (nodeLinkedLists[num]==null) {
			
    		return false;
		}
    	return nodeLinkedLists[num].contains(key);

    }
    
    //散列函数
    int hash(int key) {
    	
    	return key%capacity;
    }

	@Override
	public String toString() {
		return "MyHashSet [nodeLinkedLists=" + Arrays.toString(nodeLinkedLists) + "]";
	}
    
    
}

//作为数组内的链表
class NodeLinkedList{
	Node head;
	
	public void add(int key) {
		if (contains(key)) {
			return;
		}
		if (head==null) {
			head=new Node(key);
			return ;
		}
		Node node=new Node(key);
		Node curNode=head;
		//添加到最后
		while(curNode.nextNode!=null) {
			curNode=curNode.nextNode;
		}
		curNode.nextNode=node;
    }
    
    public void remove(int key) {
    	if (head==null) {
			return;
		}
    	if (head.val==key) {
    		//找到头结点，删除
			head=head.nextNode;
			return;
		}
    	Node preNode=head;
    	Node curNode=head.nextNode;
    	while(curNode!=null) {
    		if (curNode.val==key) {
				preNode.nextNode=curNode.nextNode;
			}
    		preNode=preNode.nextNode;
			curNode=curNode.nextNode;
		}
		
    	

    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
    	if (head==null) {
			return false;
		}
    	Node curNode=head;
    	while(curNode!=null) {
    		if (curNode.val==key) {
				return true;
			}
			curNode=curNode.nextNode;
		}
		return false;

    }

	@Override
	public String toString() {
		return "NodeLinkedList [head=" + head + "]";
	}
    
    
}

//作为数据节点
class Node{
	int val;
	Node nextNode;
	public Node(int val) {
		super();
		this.val = val;
	}
	@Override
	public String toString() {
		return "Node [val=" + val + ", nextNode=" + nextNode + "]";
	}
	
	
}
