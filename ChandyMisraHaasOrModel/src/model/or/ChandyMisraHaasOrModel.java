package model.or;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChandyMisraHaasOrModel {

	private static Set<Integer> blockedProcessesSet = null;

	private static CMHProcess[] processArray = null;

	private static Boolean deadlockFlag = false;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int aa, bb, x = 0, end = 5;
		String[] dataSet = null;
		ArrayList<Integer> dependentSet = null;
		ChandyMisraHaasOrModel orModel = new ChandyMisraHaasOrModel();
		orModel.initialiseGlobalVariables(end);
		File input = null;
		BufferedReader in = null;
		BufferedReader ob = null;
		String fileName = null;
		System.out.println("_____________________________________________________________________________");
		System.out.println();
		System.out.println(" CHANDY-MISRA-HAAS DISTRIBUTED DEADLOCK DETECTION ALGORITHM FOR THE OR MODEL ");
		System.out.println();
		try {
			ob = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Option 1: Test input processes which has no deadlock");
			System.out.println("Option 2: Test input processes which has deadlock");
			System.out.println("Select one of the below options:");
			int testOption = Integer.parseInt(ob.readLine());
			dataSet = new String[end];
			String line;
			int[][] a = new int[end][end];
			if (testOption == 1) {
				input = new File(".");
				fileName = "2016ht12400_dc_assignment/src/model/or/NoDeadlockInput.txt";
			} else if (testOption == 2) {
				fileName = "2016ht12400_dc_assignment/src/model/or/DeadlockInput.txt";
			}
			input = new File(fileName);
			in = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			line = in.readLine();
			line = in.readLine();
			while ((line = in.readLine()) != null) {
				aa = 3;
				bb = 4;
				dependentSet = new ArrayList<Integer>(end - 1);
				for (int y = 0; y < end; y++) {
					int cellValue = Integer.parseInt(line.substring(aa, bb));
					if (cellValue > 0) {
						dependentSet.add(y);
					}
					a[x][y] = cellValue;
					aa += 2;
					bb += 2;
				}
				if (dataSet[x] == null) {
					dataSet[x] = "Not dependent on any process.";
				}
				processArray[x].setDependentSet(dependentSet);
				x++;
			}
			System.out.println("\tS0\tS1\tS2\tS3\tS4");
			for (int i = 0; i < end; i++) {
				System.out.print("S" + (i) + "\t");
				for (int j = 0; j < end; j++) {
					System.out.print(a[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("Dependent set of each process:");
			int count = 0;
			do {
				System.out.print("S" + count + "=");
				ArrayList<Integer> extractDS = processArray[count].getDependentSet();
				for (Integer dependentProcess : extractDS) {
					System.out.print("S" + dependentProcess + "\t");
				}
				if (extractDS != null && extractDS.isEmpty()) {
					System.out.print("Not dependent on any process.");
				}
				System.out.println();
				count++;
			} while (count < end);
			System.out.println();
			orModel.initialiseAlgorithm(ob, end);
			if (deadlockFlag) {
				System.out.println("Deadlock has been detected among the processes.");
			} else {
				System.out.println("No deadlock has been detected among the processes.");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ob.close();
			in.close();
		}

	}

	private void initialiseGlobalVariables(int totalProcesses) {
		// Initialize CHMProcess Object Array
		processArray = new CMHProcess[totalProcesses];
		// Initialize Each CHMProcess Object
		int count = 0;
		do {
			processArray[count] = new CMHProcess(new Integer[totalProcesses], new Boolean[totalProcesses],
					new Integer[totalProcesses]);
			count++;
		} while (count < totalProcesses);

	}

	private void initialiseAlgorithm(BufferedReader ob, int totalProcesses) throws NumberFormatException, IOException {
		int init = 0, totalBP;
		System.out.print("How many processes are blocked?:");
		totalBP = Integer.parseInt(ob.readLine());
		int totalBlockedProcesses = 0;
		// Initialize Blocked Process Array
		blockedProcessesSet = new HashSet<Integer>(totalProcesses);
		do {
			System.out.print("Which process to block?: ");
			init = Integer.parseInt(ob.readLine());
			blockedProcessesSet.add(init);
			totalBlockedProcesses++;
		} while (totalBlockedProcesses < totalBP);
		System.out.println("At which process should diffusion computation start?");
		init = Integer.parseInt(ob.readLine());
		System.out.println();
		initiateDC(init);

	}

	private void initiateDC(int init) {
		// Fetch dependent set of destination process
		ArrayList<Integer> dependentSet = processArray[init].getDependentSet();
		if (dependentSet.isEmpty()) {
			System.out.println("No dependent processes to send Query message.");
		} else {
			for (Integer depProcess : processArray[init].getDependentSet()) {
				if (deadlockFlag) {
					break;
				}
				processArray[depProcess].setEngagingQuery(init);
				if (processArray[depProcess].getEngagingQueryFlag() == null) {
					processArray[depProcess].setEngagingQueryFlag(Boolean.TRUE);
				} else {
					processArray[depProcess].setEngagingQueryFlag(Boolean.FALSE);
				}
				processArray[init].getNum()[init] = dependentSet.size();
				processArray[init].getWait()[init] = true;
				// Initiate Diffusion Computation on blocked process
				query(init, init, depProcess);
			}
		}

	}

	private void query(int initiator, int source, int destination) {
		Boolean engagingQueryFlag = processArray[destination].getEngagingQueryFlag();
		if (blockedProcessesSet.contains(destination)) {
			if (engagingQueryFlag) {
				System.out.println("S" + source + " --> S" + destination + ".");
				// Fetch dependent set of destination process
				ArrayList<Integer> dependentSet = processArray[destination].getDependentSet();
				if (dependentSet.isEmpty()) {
					System.out.println("No dependent processes exist to send Query messages.");
				} else {
					for (Integer depProcess : processArray[destination].getDependentSet()) {
						if (deadlockFlag) {
							break;
						}
						processArray[depProcess].setEngagingQuery(source);
						if (processArray[depProcess].getEngagingQueryFlag() == null) {
							processArray[depProcess].setEngagingQueryFlag(Boolean.TRUE);
						} else {
							processArray[depProcess].setEngagingQueryFlag(Boolean.FALSE);
						}
						processArray[destination].getNum()[initiator] = dependentSet.size();
						processArray[destination].getWait()[initiator] = true;
						// Send Query message to all depended processes
						query(initiator, destination, depProcess);
					}
				}
			} else {
				// Send Reply message back to process which sent Engaging Query message
				if (processArray[destination].getWait()[initiator]) {
					reply(initiator, destination, source);
				}
			}
		} else {
			System.out.println("Process S" + destination + " is active. Hence, Query message sent by S" + source
					+ " is discarded.");
		}

	}

	private void reply(int initiator, int source, int destination) {
		if (blockedProcessesSet.contains(destination)) {
			if (processArray[destination].getWait()[initiator]) {
				processArray[destination].getNum()[initiator]--;
				if (processArray[destination].getNum()[initiator] == 0) {
					if (initiator == destination) {
						System.out.println("Deadlock has been detected.");
						deadlockFlag = true;
					} else {
						reply(initiator, destination, processArray[destination].getEngagingQuery());
					}
				}
			}
		} else {
			System.out.println("Process S" + destination + " is active. Hence, Reply message sent to it is discarded.");
		}

	}

}
