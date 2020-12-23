package design.system;

/**
 * 502. 迷你Cassandra

 *
 */
public class MiniCassandra {
    public MiniCassandra() {
        // do intialization if necessary
    }

    /*
     * @param raw_key: a string
     * @param column_key: An integer
     * @param column_value: a string
     * @return: nothing
     */
    public void insert(String row_key, int column_key, String value) {
        // write your code here
    }

    /*
     * @param row_key: a string
     * @param column_start: An integer
     * @param column_end: An integer
     * @return: a list of Columns
     */
    public List<Column> query(String row_key, int column_start, int column_end) {
        // write your code here
    }
}

class Column {
	public int key;
	public String value;
	public Column(int key, String value) {
	       this.key = key;
	         this.value = value;
	}
}
