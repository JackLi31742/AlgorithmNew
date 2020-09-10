package design;

/**
 * 460. LFU缓存
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
	put(key, value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。
	当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
	在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最久未使用的键。
	「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 *
 */
public class LFUCache {

	public LFUCache(int capacity) {

    }
    
    public int get(int key) {

    }
    
    public void put(int key, int value) {

    }
}
