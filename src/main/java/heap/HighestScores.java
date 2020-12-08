package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * 613. 优秀成绩
每个学生有两个属性 id 和 scores。找到每个学生最高的5个分数的平均值。
 *
 */
public class HighestScores {

	
	public Map<Integer, Double> highFive(Record[] results) {
        // Write your code here
		
		Comparator<Record> comparator=new Comparator<Record>() {

			@Override
			public int compare(Record o1, Record o2) {
				// TODO Auto-generated method stub
				
				
				return o1.score-o2.score;
				
			}
		};
		
		Map<Integer, PriorityQueue<Record>> map=new HashMap<Integer, PriorityQueue<Record>>();
		
		for (int i = 0; i < results.length; i++) {
			
			if (map.containsKey(results[i].id)) {
				
				PriorityQueue<Record> minHeap = map.get(results[i].id);
				
				if (minHeap.size()<5) {
					minHeap.add(results[i]);
					
				}else {
					
					if (minHeap.peek().score<results[i].score) {
						minHeap.poll();
						minHeap.add(results[i]);
					}
				}
				continue;
			}
			
			PriorityQueue<Record> minHeap=new PriorityQueue<Record>(5,comparator);
			
			minHeap.add(results[i]);
			map.put(results[i].id, minHeap);
		}
		
		
		Map<Integer, Double> resultMap=new HashMap<Integer, Double>();
		
		
		
		for (Entry<Integer, PriorityQueue<Record>> entry : map.entrySet()) {
			
			Integer id = entry.getKey();
			
			PriorityQueue<Record> heap = entry.getValue();
			
			Iterator<Record> iterator = heap.iterator();
			
			double sum=0;
			while(iterator.hasNext()) {
				sum+=iterator.next().score;
			}
			
			double avg=sum*100/5/100;
			resultMap.put(id, avg);
		}
		
		return resultMap;
    }
}

class Record {
	public int id, score;

	public Record(int id, int score) {
		this.id = id;
		this.score = score;
	}
}
