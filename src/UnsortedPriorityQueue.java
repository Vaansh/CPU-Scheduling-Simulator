import java.util.*;

/**
 * UnsortedPriorityQueue. Priority queue implementation 
 * using unsorted list based arraylist.
 *
 * @author Vaansh Lakhwara
 * @version 1.0
 */  
public class UnsortedPriorityQueue{	
	long waitTimeTotal=0;
	long waitTimeMin;
	int starveCount=0;	
	long systemcycles=0;
	int prioritychanges=0;
	int numdeleted=0;
	long oncycle=0;
	int processesterminated=0;
	long totaltime=0;
	
	ArrayList<Job> list = new ArrayList<>();
	
	/**
	 * Insert an element into queue.  
	 * @param element to insert
	 */
	public Job insert(String jobName, int jobLength, int currentJobLength, int jobPriority, int finalPriority, long entryTime, long endTime, long waitTime){
		Job n;
		
		if(list.size()==0) {
			n = new Job( jobName,  jobLength,  currentJobLength,  jobPriority,  finalPriority,  entryTime,  endTime,  waitTime);			
		}
		else {
			n = new Job( jobName,  jobLength,  currentJobLength,  jobPriority,  finalPriority,   list.get(list.size()-1).getEntryTime()+1,  endTime,  waitTime);
		}		
		list.add(n);
		systemcycles+=jobLength;
		starveCount++;
		return n;
	}
	 
	/**
	 * Returns minimum key from queue.
	 * @return minimum key.
	 */
	public int[] min(){
		int i=1;
		Job min = list.get(0);
		int minindex=0,maxindex=0;
		Job max=list.get(0);
		min.waitTime+=1;
		if(size()==0)
			return new int[] {minindex, maxindex};
		
		while(i<size()) {
			list.get(i).waitTime+=1;
			if(min.getJobPriority()<((Job) list.get(i)).getJobPriority()) {
				max=list.get(i);
				maxindex=i;
			}
			i++;
		}		

		i=1;
		while(i<size()-1) {
			if(min.getJobPriority()>list.get(i).getJobPriority()) {
				min=list.get(i);
				minindex=i;
			}										
			i++;															
		}
		return new int[] {minindex, maxindex};
	}

	/**
	 * Removes minimum key from queue.
	 * @return minimum key.
	 */
	public Job removeMin(){						
		waitTimeTotal+=1;
		oncycle++;
		int [] minmax = min();
		Job temp = list.get(minmax[0]);
		
		if(temp.currentJobLength>0) {		
			list.set(minmax[0], new Job( temp.jobName,  temp.jobLength,  temp.currentJobLength-1,  
					temp.jobPriority, 
					list.get(minmax[1]).getJobPriority()<40?list.get(minmax[1]).getJobPriority()+1:40, 
					list.get(list.size()-1).getEntryTime()+1,  temp.endTime+2,  list.get(list.size()-1).waitTime));						
		}
		else {
			for(int p=0;p<list.size() && p!=minmax[0] && p!=minmax[1];p++) {
				list.get(p).endTime=0;
				list.get(p).waitTime+=1;
			}			
			if(processesterminated%30==0 && processesterminated>0) {
					prioritychanges+=1;	
						minmax[0]=minmax[1];					
				}	
			temp.endTime+=1;
			totaltime+=list.get(minmax[0]).waitTime-list.get(minmax[0]).endTime-list.get(minmax[0]).jobPriority;
			list.remove(minmax[0]);
			processesterminated++;			
		}		
		return temp;
	}

	/**
	 * Returns size of queue.
	 * @return size.
	 */
	public int size(){
		return list.size();
	}

	/**
	 * State of queue.
	 * @return boolean value of queue being empty.
	 */
	public boolean isEmpty(){
		return (size()==0);
	}
		
}