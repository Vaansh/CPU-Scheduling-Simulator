import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * PriorityQueueSimulatorTester. Driver 
 * class to implement both the custom
 * made priority queues.
 * 
 * @author Vaansh Lakhwara
 * @version 1.0
 */  

public class PriorityQueueSimulatorTester {
	/**
	 * randomNumberGenerator. Creates a random number
	 * generated between the two values.
	 * 
	 * @param min Lower number.
	 * @param max Higher number.
	 * @return Random value between the two numbers.
	 */
    public static int randomNumberGenerator(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max-min) + min;
    }
    
    /**
     * Driver values.
     * @param args Command line arguments.
     */
	public static void main(String[] args){
		//try-with-resource block used (works with Java 7 or later).
		try(FileWriter f = new FileWriter("results.txt", true); 
			BufferedWriter b = new BufferedWriter(f); 
			PrintWriter p = new PrintWriter(b);
			Scanner scan = new Scanner(System.in);) {
				System.out.println("▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
		    	System.out.println("░░░░░░░░░░░░░WELCOME TO CPU SCHEDULING░░░░░░░░░░░░░░");
		    	System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		    	p.println("▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
		    	p.println("░░░░░░░░░░░░░WELCOME TO CPU SCHEDULING░░░░░░░░░░░░░░");
		    	p.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		    	int [] maxNumberOfJobs = new int[] {100, 1000, 10000, 100000};
		        int i=0;      
	
		        while(i<maxNumberOfJobs.length){        	
		        	Job [] jobsInputArray = new Job[maxNumberOfJobs[i]];
		            int len=jobsInputArray.length;
		            System.out.println("\nChecking for " + jobsInputArray.length + " numbers...\n");
		            p.write("\nChecking for " + jobsInputArray.length + " numbers...\n");
		            int j2=0;
		            int j=1;
		            
		            while (j2<len){
		                String jobName = "Job_" + j;
		                int jobNumber = j;
		                int jobLength = randomNumberGenerator(1,71);
		                int currentJobLength = jobLength;
		                int jobPriority = randomNumberGenerator(1,41);;
		                int finalPriority = jobPriority;
		                long entryTime = 0;
		                long endTime = 0;
		                long waitTime = 0;                
		                jobsInputArray[j2]= new Job(jobName, jobLength, currentJobLength, jobPriority, finalPriority, entryTime, endTime, waitTime);
		                j2++;
		                j++;
		            }        	                
		            
		            if(jobsInputArray.length<100000) {
		            	System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		            	System.out.println("Testing with unsorted priority queue...");
		            	p.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		            	p.println("Testing with unsorted priority queue...");
				        int k1=0;
				        double st1, et1, elapsed1;
				        double et2, elapsed2;
				        st1 = System.currentTimeMillis();  
				        UnsortedPriorityQueue unsortedPQ = new UnsortedPriorityQueue();
				        while (k1<j2){            
				            unsortedPQ.insert(jobsInputArray[k1].jobName,  jobsInputArray[k1].jobLength,  jobsInputArray[k1].currentJobLength,  jobsInputArray[k1].jobPriority,  
				            		jobsInputArray[k1].finalPriority,  jobsInputArray[k1].entryTime,  jobsInputArray[k1].endTime,  jobsInputArray[k1].waitTime);            
				            k1++;
				        }
				        
				        long w=0;
				        Job j3= new Job();
				        while (unsortedPQ.list.size()>0){
				            j3=unsortedPQ.removeMin();            
				            w+=j3.waitTime;    
				            et2 = System.currentTimeMillis();
					        elapsed2 = et2 - st1;
				            if(elapsed2>120000) {
						        UnsortedPriorityQueue unsortedPQShort = new UnsortedPriorityQueue();
						        for(int shift=0; shift<100; shift++)
						        	unsortedPQShort.insert(jobsInputArray[shift].jobName,  jobsInputArray[shift].jobLength,  jobsInputArray[shift].currentJobLength,  jobsInputArray[shift].jobPriority,  
						            		jobsInputArray[shift].finalPriority,  jobsInputArray[shift].entryTime,  jobsInputArray[shift].endTime,  jobsInputArray[shift].waitTime);
						        unsortedPQ=unsortedPQShort;
						        st1 = System.currentTimeMillis();  
				            }
				        }	                        
				        et1 = System.currentTimeMillis();
				        elapsed1 = et1 - st1;
		            
				        System.out.println("Current systemtime(cycles): " + unsortedPQ.systemcycles + "\n" + 
				        		"Total number of jobs executed: " + len + " jobs \n" +
				        		"Average process waiting time: " + w/len + " cycles \n" + 
				        		"Total number of priority changes: " + unsortedPQ.prioritychanges + "\n" + 
				        		"Actual system time needed to execute all jobs: " + elapsed1 +"ms");
				        p.write("Current systemtime(cycles): " + unsortedPQ.systemcycles + "\n" + 
				        		"Total number of jobs executed: " + len + " jobs \n" +
				        		"Average process waiting time: " + w/len + " cycles \n" + 
				        		"Total number of priority changes: " + unsortedPQ.prioritychanges + "\n" + 
				        		"Actual system time needed to execute all jobs: " + elapsed1 +"ms");
		            }
			        
		            System.out.println();
		            System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		            System.out.println("On to heap based arraylist...");
		            p.println();
		            p.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		            p.println("On to heap based arraylist...");
		            int k2=0;
		  			double et,elapsed,st; 
			        double et3, elapsed3;
		  	        st = System.currentTimeMillis();
		  	        ArrayListBasedHeapPQ pq = new ArrayListBasedHeapPQ();
		        	          
			        while (k2<j2){            
			            pq.insert(jobsInputArray[k2],  (int) jobsInputArray[k2].waitTime);            
			            k2++;
			        }   
			        
		  			int starvedandprioritized=0;
		  	        int waittimecommon=0;
		  	        long waittime=0;
		  			while(!pq.isEmpty()) {
		  				pq.callcount++;					  									
		  				if(pq.callcount%30==0) {
		  					if(pq.heap.get(0).jobLength==pq.heap.get(0).currentJobLength) {
		  							pq.heap.get(0).finalPriority=1;
		  							starvedandprioritized++; 
		  							Job r = pq.removemin();
		  						}					
		  					}				
		  					else if(pq.min().currentJobLength==0) {
		  						pq.min().waitTime=waittimecommon;
		  						Job r = pq.removemin();
		  						waittime+=r.waitTime;
		  					}
		  					else {				
		  						pq.min().currentJobLength-=1;
		  						if(pq.heap.size()>1) {			
		  							Job tempo = pq.removemin();	
		  							tempo.waitTime=waittimecommon;  							
		  							tempo.finalPriority=pq.heap.get(pq.heap.size()-1).finalPriority<40?pq.heap.get(pq.heap.size()-1).finalPriority+1:40;
		  							pq.insert(tempo, waittimecommon);														
		  						}
		  					}
		  				et3 = System.currentTimeMillis();
				        elapsed3 = et3  - st;			            
		  				if(elapsed3>120000) {
				  	        ArrayListBasedHeapPQ smallpq = new ArrayListBasedHeapPQ();
				  	      for(int shift=0; shift<100; shift++)
				  	    	smallpq.heap.add(pq.heap.get(shift));
				  	      	pq=smallpq;
				  	      	st = System.currentTimeMillis();
		  				}		  					
		  				waittimecommon++;
		  			}					
		  			
		  			et = System.currentTimeMillis();			
		  	        elapsed = et - st;        
		  			System.out.println("Current systemtime(cycles): " + pq.callcount+ "\n" + 
		  					"Total number of jobs executed: " + len + " jobs \n" + 
		  					"Average process waiting time: " + waittime/len +" cycles \n" + 
		  					"Total number of priority changes: " + starvedandprioritized + "\n" + 
		  					"Actual system time needed to execute all jobs: " + elapsed + "ms\n" + 
		  					"");	 
		  			p.write("Current systemtime(cycles): " + pq.callcount+ "\n" + 
		  					"Total number of jobs executed: " + len + " jobs \n" + 
		  					"Average process waiting time: " + waittime/len +" cycles \n" + 
		  					"Total number of priority changes: " + starvedandprioritized + "\n" + 
		  					"Actual system time needed to execute all jobs: " + elapsed + "ms\n" + 
		  					"");
		        i++;
		        System.out.println(";;;;;;;;;;;;;;;;;;; Round " + i + " Over ;;;;;;;;;;;;;;;;;;;");
		        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
		        p.println(";;;;;;;;;;;;;;;;;;; Round " + i + " Over ;;;;;;;;;;;;;;;;;;;");
		        p.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
		        p.println(" ");
		        }
		        p.flush();
		}catch(IOException i) {	//In case there is some error with the file handling. 		
			System.out.println("Error in file handling.");
			System.out.println("Try resolving the issue and come again, bye! :(");
			System.exit(0);
		}
	}
}
