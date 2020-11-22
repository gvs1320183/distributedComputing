# distributedComputing
This repository contains artefacts required for implementing Distributed Computing algorithms.

Algorithms:
-----------
1. CHANDY-MISRA-HAAS DISTRIBUTED DEADLOCK DETECTION ALGORITHM FOR THE OR MODEL

In below platform configuration, ChandyMisraHaasOrModel.jar has been developed and tested:
Operating System = macOS Big Sur
JDK Version = 15.0.1
Location of Jar File = distributedComputing/ChandyMisraHaasOrModel/deploy/

Program's Input Details:
------------------------
The jar file has two input files.
One for testing the program to declare that no deadlock has been detected among the processes. File name is NoDeadlockInput.txt.
Another for testing the program to declare that deadlock has been detected among the processes. File name is DeadlockInput.txt.
Both the input files contains dependency matrix of 5 processes.
Program can run on any number of processes, analyse their dependency and can identify deadlock situation.
To facilitate the evaluator, processes and their dependencies have been fixed.
Evaluator can decide on which processes shown on console (after execution of jar) can be considered blocked.
Diffusion Computation's Initiator process among the blocked processes can also be picked up by the evaluator.

Steps for Execution:
--------------------
1. Make sure required JDK version has been installed
=============Test No Deadlock================
2. Execute command java -jar ChandyMisraHaasOrModel.jar in Mac OS Terminal
3. Give input "1" to select Option 1
4. Give input "3" for How many processes are blocked? question
5. Enter "0" for Which process to block? question
6. Enter "1" for Which process to block? question
7. Enter "2" for Which process to block? question
8. Give input "0" for At which process should diffusion computation start? question
9. Output is shown on the console.
=============Test Deadlock================
10. Execute command java -jar ChandyMisraHaasOrModel.jar in Mac OS Terminal
11. Give input "2" to select Option 2
12. Give input "3" for How many processes are blocked? question
13. Enter "0" for Which process to block? question
14. Enter "1" for Which process to block? question
15. Enter "2" for Which process to block? question
16. Give input "0" for At which process should diffusion computation start? question
17. Output is shown on the console.
