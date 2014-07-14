package jcarro.Jondor.Condor;

import java.util.StringTokenizer;

import jcarro.Jondor.Server.ServerController;

import com.jcraft.jsch.*;

/**
 * @author jcarro
 * 
 */
public class CondorController {

	private ServerController serverController;

	public CondorController(Session session) {
		serverController = new ServerController(session);
	}

	/**
	 * @return
	 */
	public String queue() {
		return queue(null);
	}

	/**
	 * @param user
	 * @return
	 */
	public String queue(String user) {

		return serverController.execCommand("condor_q -xml " + user);
	}

	
	/**
	 * @param file
	 * @return
	 */
	public SubmitResult submit(String file) {
		String output = serverController.execCommand("condor_submit " + file);
		
		System.out.println(output);
		
		StringTokenizer tokenizer = new StringTokenizer(output, "\n");

		String lastLine = null;
		while (tokenizer.hasMoreElements())
			lastLine = tokenizer.nextToken();

		
		int numJobs = 0;
		try {
			tokenizer = new StringTokenizer(lastLine, " ");
			numJobs = Integer.parseInt(tokenizer.nextToken());
		} catch (NumberFormatException e) {
			System.out.println(output);
		}catch (NullPointerException e) {
			System.out.println(output);
		}

		String word = null;
		while (tokenizer.hasMoreElements())
			word = tokenizer.nextToken();

		int clusterID = -1;
		try {
			clusterID = Integer.parseInt(word.substring(0, word.length() - 1));
		} catch (NumberFormatException e) {
			System.out.println(output);
		}catch (NullPointerException e) {
			System.out.println(output);
		}

		return new SubmitResult(clusterID, numJobs);
	}

	public boolean waitJob(String logFile, int waitTime) {
		String output = serverController.execCommand("condor_wait -wait "
				+ waitTime + " " + logFile);
		System.out.println(output);
		if(output.compareTo("All jobs done.\n")==0)
			return true;
		return false;
				
	}
}
