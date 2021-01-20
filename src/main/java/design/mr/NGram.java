package design.mr;

/**
 * 
537. N-Gram (Map Reduce)

 * @author HP
 *
 */
public class NGram {

	public static class Map {
        public void map(String s, int n, String str,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, Integer value);
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
        }
    }
}
