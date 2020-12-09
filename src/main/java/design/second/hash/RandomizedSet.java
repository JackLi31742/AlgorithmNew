package design.second.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 657. O(1)实现数组插入/删除/随机访问
 *	设计一个数据结构实现在平均 O(1) 的复杂度下执行以下所有的操作。

insert(val): 如果这个元素不在set中，则插入。

remove(val): 如果这个元素在set中，则从set中移除。

getRandom: 随机从set中返回一个元素。每一个元素返回的可能性必须相同。


526. 负载均衡器
为网站实现一个负载均衡器，提供如下的 3 个功能：

添加一台新的服务器到整个集群中 => add(server_id)。
从集群中删除一个服务器 => remove(server_id)。
在集群中随机（等概率）选择一个有效的服务器 => pick()。
最开始时，集群中一台服务器都没有。每次 pick() 调用你需要在集群中随机返回一个 server_id。


允许重复的数：https://www.jiuzhang.com/solutions/insert-delete-getrandom-o1-duplicates-allowed/
 */
public class RandomizedSet {
	
	private Map<Integer, Integer> valueToIndex;
	
	private List<Integer> valueList;
	
	private Random random;
	
    public RandomizedSet() {
        // do intialization if necessary
    	valueList=new ArrayList<Integer>();
    	valueToIndex=new HashMap<Integer, Integer>();
    	
    	random=new Random();
    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        // write your code here
    	
    	if (valueToIndex.containsKey(val)) {
			return false;
		}
    	
    	valueToIndex.put(val, valueList.size());
    	valueList.add(val);
    	return true;
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        // write your code here
    	if (!valueToIndex.containsKey(val)) {
			return false;
		}
    	
    	int index=valueToIndex.remove(val);
    	int last=valueList.get(valueList.size()-1);
    	//用数组最后一个值覆盖要删除的值
    	valueList.set(index, last);
    	//这样的话是O(n)
//    	valueList.remove(index);
    	valueToIndex.put(last, index);
    	
    	valueList.remove(valueList.size()-1);
    	return true;
    }
    
    
    

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        // write your code here
    	
    	int index=random.nextInt(valueList.size());
    	
    	return valueList.get(index);
    	
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */