/**
 * 
 */
package jcarro.Jondor.Condor;

/**
 * @author jcarro
 * 
 */
public class CondorSubmitResult {

	public int clusterID;
	public int numJobs;

	/**
	 * @param clusterID
	 * @param numJobs
	 */
	public CondorSubmitResult(int clusterID, int numJobs) {
		this.clusterID = clusterID;
		this.numJobs = numJobs;
	}
	
	@Override
	public String toString()
	{
		return "Cluster ID: " + clusterID + "\nNum Jobs: " + numJobs;
	}

}
