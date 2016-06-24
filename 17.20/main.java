import java.util.Comparator;
import java.util.PriorityQueue;

public class main {
	Comparator<Integer> maxHeapComparator, minHeapComparator;
	PriorityQueue<Integer> maxHeap, minHeap;
	
	void addNewNumber(int randomNumber){
		if(maxHeap.size() == minHeap.size()){
			if(minHeap.peek() != null && randomNumber > minHeap.peek()){
				maxHeap.offer(minHeap.poll());
				minHeap.offer(randomNumber);
			}
			else{
				maxHeap.offer(randomNumber);
			}
		}
		else{
			if(randomNumber < maxHeap.peek()){
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(randomNumber);
			}
			else{
				minHeap.offer(randomNumber);
			}
		}
	}
	
	double getMedian(){
		if(maxHeap.isEmpty()){
			return 0;
		}
		if(maxHeap.size() == minHeap.size()){
			return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2;
		}
		else{
			return maxHeap.peek();
		}
	}
}
