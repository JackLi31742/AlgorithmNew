package design.mr;

/**
 * 503. 乱序字符串 (Map Reduce版本)

 * @author HP
 *
 */
public class Anagram {

	 public static class Map {
	        public void map(String key, String value,
	                        OutputCollector<String, String> output) {
	            // Write your code here
	            // Output the results into output buffer.
	            // Ps. output.collect(String key, String value);
	        }
	    }

	    public static class Reduce {
	        public void reduce(String key, Iterator<String> values,
	                           OutputCollector<String, List<String>> output) {
	            // Write your code here
	            // Output the results into output buffer.
	            // Ps. output.collect(String key, List<String> value);
	        }
	    }
}
