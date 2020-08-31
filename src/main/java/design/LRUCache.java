package design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU缓存机制
 * 
 * 设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
	
	获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
	写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；
	如果关键字不存在，则插入该组「关键字/值」。
	当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
	https://www.cnblogs.com/xiaoxi/p/6170590.html

 */
public class LRUCache extends LinkedHashMap<Integer, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2629115163020729850L;
	public static void main(String[] args) {
//		LinkedHashMap<Integer, Integer> linkedHashMap=new LinkedHashMap<Integer, Integer>(3,0.75f,true);
//		linkedHashMap.put(1, 1);
//		linkedHashMap.put(2, 2);
//		linkedHashMap.put(3, 3);
//		linkedHashMap.put(4, 4);
//		
//		for (Map.Entry<Integer,Integer> entry: linkedHashMap.entrySet()) {
//			System.out.println(entry.getKey()+":"+entry.getValue());
//		}
//		
//		linkedHashMap.size();
//		
//		linkedHashMap.get(3);
//		System.out.println();
//		for (Map.Entry<Integer,Integer> entry: linkedHashMap.entrySet()) {
//			System.out.println(entry.getKey()+":"+entry.getValue());
//		}
		
		LRUCache cache=new LRUCache(2);
		
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		
		cache.forEach((k,v)->System.out.println(k));
		
		System.out.println();
		
		cache.get(2);
		
		cache.forEach((k,v)->System.out.println(k));
		
	}
	
	int capacity;
	public LRUCache(int capacity) {
		//调用linkedhashmap，开启按照访问顺序进行存储
		super(capacity,0.75f,true);
		this.capacity=capacity;
    }
    
    public int get(int key) {
    	if (!containsKey(key)) {
			return -1;
		}
    	//get会将节点移到双链表的尾部
    	Integer result=super.get(key);
    	return result;
    }
    //此方法是淘汰策略的核心
    public boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
        return size()>this.capacity;
    }
    
    public void put(int key, int value) {
    	//removeEldestEntry会在put时调用
    	super.put(key, value);
    }
    
    
    
}