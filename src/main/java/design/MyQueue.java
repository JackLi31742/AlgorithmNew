package design;

/**
 * LintCode 492
 * 
 * @author JackLi
 *
 */
public class MyQueue {

	private int[] data;
	private int front;
	private int tail;
	private int num;
	
	 public MyQueue() {
		 this.num=100;
		 this.data=new int[num];
	}

	/*
     * @param item: An integer
     * @return: nothing
     */
    public void enqueue(int item) {
        // write your code here
    	//队列满
    	if (tail==num-1) {
			return;
		}
    	data[tail]=item;
    	tail++;
    }

    /*
     * @return: An integer
     */
    public int dequeue() {
        // write your code here
//    	if (front==num-1) {
//			return -1;
//		}
    	// 队列为空
        if (front == tail){
            return -1;
        }
    	return data[front++];
    }
}
