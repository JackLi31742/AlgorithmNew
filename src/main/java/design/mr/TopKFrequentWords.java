package design.mr;

/**
 * 549. 最常使用的k个单词(Map Reduce)

 * @author HP
 *
 */
public class TopKFrequentWords {

	 public static class Map {
	        public void map(String _, Document value,
	                        OutputCollector<String, Integer> output) {
	            // Write your code here
	            // Output the results into output buffer.
	            // Ps. output.collect(String key, int value);
	        }
	    }

	    public static class Reduce {

	        public void setup(int k) {
	            // initialize your data structure here
	        }   

	        public void reduce(String key, Iterator<Integer> values) {
	            // Write your code here
	        }

	        public void cleanup(OutputCollector<String, Integer> output) {
	            // Output the top k pairs <word, times> into output buffer.
	            // Ps. output.collect(String key, Integer value);
	        }
	    }
}
