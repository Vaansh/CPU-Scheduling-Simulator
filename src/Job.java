/**
 * Job class. Containing entire information
 * of a job. 
 *
 * @author Vaansh Lakhwara
 * @version 1.0
 */  
public class Job implements Comparable<Job>{
	String jobName;
    int jobLength;
    int currentJobLength;
    int jobPriority;
    int finalPriority;
    long entryTime;
    long endTime;
    long waitTime;
    
    /**
     * Null constructor.
     */
	public Job() {
		this.jobName="";
        this.jobLength=0;
        this.currentJobLength=0;
        this.jobPriority=0;
        this.finalPriority=0;
        this.entryTime=0;
        this.endTime=0;
        this.waitTime=0;
    }
    
    /**
     * Parameterized constructor.
     */
    public Job(String jobName, int jobLength, int currentJobLength, int jobPriority, int finalPriority, long entryTime, long endTime, long waitTime){
        this.jobName=jobName;
        this.jobLength=jobLength;
        this.currentJobLength=currentJobLength;
        this.jobPriority=jobPriority;
        this.finalPriority=finalPriority;;
        this.entryTime=entryTime;
        this.endTime=endTime;
        this.waitTime=waitTime;
    }
    
	/**
	 * Copy constructor.
	 */
    public Job(Job j){
    	this.jobName=j.jobName;
		this.jobLength=j.jobLength;
		this.currentJobLength=j.currentJobLength;
		this.jobPriority=j.jobPriority;
		this.finalPriority=j.finalPriority;;
		this.entryTime=j.entryTime;
		this.endTime=j.endTime;
		this.waitTime=j.waitTime;
  }    
   
   /**
    * toString function.
    */
    public String toString() {
    	return jobName + " jobName " +
    			jobLength + " jobLength " + 
    			currentJobLength + " currentJobLength " + 
    			jobPriority + " jobPriority " +    			
    			finalPriority + " finalPriority ";
    }
    
   /**
    * Equals function.
    * @param j1 Job to compare.
    * @param j2 Other job to compare.
    * @return boolean value of equality,
    */
	public boolean Equals(Job j1, Job j2){
		return j1.getJobPriority()==j2.getJobPriority();
	}
	
	/**
	 * Accessor method.
	 * @return final priority.
	 */
	public int getJobPriority() {
		return finalPriority;
	}
	
    /**
	 * Accessor method.
	 * @return entry time.
	 */
    public long getEntryTime() {
    	return entryTime;
    }
    
    /**
	 * Accessor method.
	 * @return job length.
	 */
    public int getJobLength() {
		return jobLength;
	}
    
    /**
	 * compareTo method.
	 * @return compared value.
	 */
    @Override
	public int compareTo(Job o) {		
		if (jobPriority<o.jobPriority) {
			return 1;
		}		
		else if(jobPriority>o.jobPriority) {
			return -1;
		}		
		return 0;
	}		
}