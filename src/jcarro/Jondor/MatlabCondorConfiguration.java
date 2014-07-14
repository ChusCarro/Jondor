/**
 * 
 */
package jcarro.Jondor;

import java.io.*;
import java.util.ArrayList;

/**
 * @author jcarro
 * 
 */
public class MatlabCondorConfiguration {

	private boolean shortJob = false;
	private boolean longJob = false;
	private boolean niceJob = false;

	private String executable = null;
	private String initialDir = null;
	private String errFile = null;
	private String outFile = null;
	private String logFile = null;
	private String arguments = null;

	private ArrayList<String> inputFiles = new ArrayList<String>();

	private int requestCPUs = -1;
	private int requestMemory = -1;
	private int numSimulations = -1;

	public boolean saveToFile(String fileName) {
		File file = new File(fileName);

		PrintWriter output = null;
		try {
			output = new PrintWriter(file);

			output.println("##");
			output.println("## .condor file for matlab simulation");
			output.println("##");
			output.println();
			output.println("Universe = Vanilla");
			output.println();
			output.println("InitialDir = " + initialDir);
			output.println();
			output.println("Executable = " + executable);
			output.println();
			if (errFile != null)
				output.println("Error = " + errFile);
			if (logFile != null)
				output.println("Log = " + logFile);
			if (outFile != null)
				output.println("Output = " + outFile);
			output.println();
			output.println("Arguments = " + arguments);
			output.println();
			if (requestCPUs > 0)
				output.println("request_cpus = " + requestCPUs);
			if (requestMemory > 0)
				output.println("request_memory = " + requestMemory);
			output.println();

			if (inputFiles != null && inputFiles.size() > 0) {
				output.print("transfer_input_files = ");
				output.print(inputFiles.get(0));
				for (int i = 1; i < inputFiles.size(); i++) {
					output.print(", " + inputFiles.get(i));
				}
				output.println();
			}

			output.println();
			output.println("GetEnv = true");
			output.println("WhenToTransferOutput = ON_EXIT_OR_EVICT");
			output.println();

			if (shortJob)
				output.println("+ShortJob = TRUE");
			if (longJob)
				output.println("Concurrency_Limits=longjobs");
			if (niceJob)
				output.println("NiceUser = TRUE");

			output.println();
			output.println("Queue " + numSimulations);

			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * @return the requestCPUs
	 */
	public int getRequestCPUs() {
		return requestCPUs;
	}

	/**
	 * @param requestCPUs
	 *            the requestCPUs to set
	 */
	public void setRequestCPUs(int requestCPUs) {
		this.requestCPUs = requestCPUs;
	}

	/**
	 * @return the requestMemory
	 */
	public int getRequestMemory() {
		return requestMemory;
	}

	/**
	 * @param requestMemory
	 *            the requestMemory to set
	 */
	public void setRequestMemory(int requestMemory) {
		this.requestMemory = requestMemory;
	}

	/**
	 * @return the shortJob
	 */
	public boolean isShortJob() {
		return shortJob;
	}

	/**
	 * @param shortJob
	 *            the shortJob to set
	 */
	public void setShortJob(boolean shortJob) {
		this.shortJob = shortJob;
	}

	/**
	 * @return the longJob
	 */
	public boolean isLongJob() {
		return longJob;
	}

	/**
	 * @param longJob
	 *            the longJob to set
	 */
	public void setLongJob(boolean longJob) {
		this.longJob = longJob;
	}

	/**
	 * @return the niceJob
	 */
	public boolean isNiceJob() {
		return niceJob;
	}

	/**
	 * @param niceJob
	 *            the niceJob to set
	 */
	public void setNiceJob(boolean niceJob) {
		this.niceJob = niceJob;
	}

	/**
	 * @return the executable
	 */
	public String getExecutable() {
		return executable;
	}

	/**
	 * @param executable
	 *            the executable to set
	 */
	public void setExecutable(String executable) {
		this.executable = executable;
	}

	/**
	 * @return the initialDir
	 */
	public String getInitialDir() {
		return initialDir;
	}

	/**
	 * @param initialDir
	 *            the initialDir to set
	 */
	public void setInitialDir(String initialDir) {
		this.initialDir = initialDir;
	}

	/**
	 * @return the errFile
	 */
	public String getErrFile() {
		return errFile;
	}

	/**
	 * @param errFile
	 *            the errFile to set
	 */
	public void setErrFile(String errFile) {
		this.errFile = errFile;
	}

	/**
	 * @return the outFile
	 */
	public String getOutFile() {
		return outFile;
	}

	/**
	 * @param outFile
	 *            the outFile to set
	 */
	public void setOutFile(String outFile) {
		this.outFile = outFile;
	}

	/**
	 * @return the logFile
	 */
	public String getLogFile() {
		return logFile;
	}

	/**
	 * @param logFile
	 *            the logFile to set
	 */
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	/**
	 * @return the inputFiles
	 */
	public ArrayList<String> getInputFiles() {
		return inputFiles;
	}

	public boolean addInputFile(String inputFile) {
		return inputFiles.add(inputFile);
	}

	public boolean addInputFile(ArrayList<String> inputFiles) {
		boolean state = true;

		for(int i=0;i<inputFiles.size();i++)
			state=state & this.inputFiles.add(inputFiles.get(i));
		
		return state;
	}
	/**
	 * @param inputFiles
	 *            the inputFiles to set
	 */
	public void setInputFiles(ArrayList<String> inputFiles) {
		this.inputFiles = inputFiles;
	}

	/**
	 * @return the arguments
	 */
	public String getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the numSimulations
	 */
	public int getNumSimulations() {
		return numSimulations;
	}

	/**
	 * @param numSimulations
	 *            the numSimulations to set
	 */
	public void setNumSimulations(int numSimulations) {
		this.numSimulations = numSimulations;
	}
}
