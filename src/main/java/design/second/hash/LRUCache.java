package design.second.hash;

import java.util.HashMap;

/**
 * 134. LRU缓存策略
 * • 新节点从尾部加入
• 老节点从头部移走

整体是hash+双链表
 *
 */
public class LRUCache {
	
	public static void main(String[] args) {
		
		LRUCache lruCache=new LRUCache(1);
		lruCache.set(2, 1);
		lruCache.get(2);
		lruCache.set(3, 2);
//		lruCache.set(4, 1);
		lruCache.get(2);
		lruCache.get(3);
	}
	
	private HashMap<Integer, Node> map;
	
	private int capacity;
	
	//head是dummy node
	private Node head;
	
	//dummy node
	private Node tail;

	/*
	    * @param capacity: An integer
	    */public LRUCache(int capacity) {
	        // do intialization if necessary
	    	
	    	this.capacity=capacity;
	    	
	    	map=new HashMap<Integer, LRUCache.Node>();
	    	
	    	head=new Node(-1,-1);
	    	tail=new Node(-1, -1);
	    	
	    	head.next=tail;
	    	tail.pre=head;
	    	
	    }

	    /*
	     * @param key: An integer
	     * @return: An integer
	     */
	    public int get(int key) {
	        // write your code here
	    	
	    	if (!map.containsKey(key)) {
	    		
				return -1;
			}
	    	
	    	Node node = map.get(key);
	    	
	    	moveToTail(node);
	    	
	    	return node.value;
	    }

	    /*
	     * @param key: An integer
	     * @param value: An integer
	     * @return: nothing
	     */
	    public void set(int key, int value) {
	        // write your code here
	    	
	    	if (map.containsKey(key)) {
	    		
				Node node = map.get(key);
				node.value=value;
				moveToTail(node);
				
				return;
			}
	    	
	    	if (map.size()>=capacity) {
				
				//删除头，不是删除入参key	
    			map.remove(head.next.key);
//    			System.out.println(map);
    			//head是dummy node ，直接挪到下一个节点即可
    			head=head.next;
    			head.pre=null;
			}
	    	
	    	Node node=new Node(key,value);
	    	map.put(key, node);
	    	
	    	addToTail(node);
	    			
	    }
	    
	    
	    public void moveToTail(Node node) {
	    	
	    	Node preNode = node.pre;
	    	Node nextNode= node.next;
	    	
	    	//有dummy node 不用判空
//	    	if (nextNode==null) {
//				return;
//			}
	    	
			preNode.next=nextNode;
			
			nextNode.pre=preNode;
	    	
	    	addToTail(node);
	    	
	    }
	    
	    public void addToTail(Node node) {
	    	
	    	
	    	tail.pre.next=node;
	    	node.pre=tail.pre;
	    	
	    	node.next=tail;
	    	tail.pre=node;
	    }
	    
	    static class Node {
	    	
	    	int key;
	    	int value;
	    	Node pre;
	    	Node next;
			public Node(int key, int value) {
				super();
				this.key = key;
				this.value = value;
			}
			
	    }
}


