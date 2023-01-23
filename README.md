# This is an approach tot the bin packing problem using while testing 2 different methods

## Part A: MaxPQ.java

We used the priority queue that was presented in class but adjusted it accordingly to use the class “Disks” instead of just Integers. The main difference between the PQ that was presented to us and the PQ we use is inside the “less” method where instead of comparing two “nodes” from the queue, we compare the available free space of the nodes (Disks). The methods “exch”,”swim”,”sink”,etc. remain the same. We also added two new methods, the “root” method which returns the top of the queue (the disk with the largest available space) and the “leaf” method which returns the position of the newest addition to the queue (the newest disk’s place).

## Part B: Greedy.java

The MaxPQ was used in the Greedy class by storing all the disks that are needed to store the given folders. Each new disks that is created, is added to the end of the queue and then using the methods of the MaxPQ is moved to its correct place within the PQ. More specifically, the Greedy class first reads the txt file and determines how many folders there are (n). It then creates a PQ that is the size of n (so it is surely able to store all the disks/folders) and then starts reading the txt file again. Then depending on the size of the folders read, the folder is stored either in the top disk of the PQ and then the top disks is “sank” into the correct place or a new disk is created to fit the folder read, the new disk is added to the PQ and swims towards its correct place. Or if the PQ is empty then a new disk is created and added into the PQ. Finally the sum of the size of the folders read is printed together with the total number of disks used.
The txt file that is given must not have any characters other than numbers and each number must be in a different line than any other number.

## Part C: Sort.java

The Sort.java program is used to solve the bin packing problem more efficiently. It does that while being slower to run but in uses far less disks that the Greedy.java by itself.
Firstly read the file in which there are the capacities of the folders inside. Then we implement the Quick Sort algorithm to sort them. After that re-write the txt file with the sorted numbers. Finally the folders are arranged from the highest to lowest. This way the bin packing problem can be approached in another way that uses less disks.

## Part D: Differentiate.java

The purpose of Differentiate.java is to create txt files containing “folders” (integers from 0 to 1000000) and then run these files through the Greedy.java algorithm, once without sorting and once with the “folders” sorted to their size, using the Sort.java algorithm.
To create the txt files we make use of the Random class of java. First we randomly select a number (N) from 0 to 1000, which represents the amount of “folders” (integers) will be in each txt file. And then for each of those “folders” we select its random value from 0 to 1000000. We do this three times, one for each random value of N. Depending on the value of the variable “files_to_create”, we will have 3 times as many total txt files.
After creating all the test files, we run each txt file through the Greedy.java main function and record the results. Then we sort the txt file and run the Greedy.java main again with the sorted txt file, recording the results at the end.
When we finish going through each txt file, we put all our recorded results into a txt file named “results”. Lastly we calculate the average disks used for each number of folders (N) for both the random and sorted algorithms.
We run the algorithm multiple times and the results were always the same: The sorted algorithm uses significantly less disks than the random algorithm. We also noticed that when the number of folders (N) is larger, the difference between the two algorithms is more noticeable.

### Below we present one of the tests we run using random values of N:

**For N = 925:**<br />
The average amount of disks used without sorting is: 543,969971.<br />
The average amount of disks used with sorting is: 472,519989. <br />

**For N = 499:**<br />
The average amount of disks used without sorting is: 294,809998.<br />
The average amount of disks used with sorting is: 257,429993.<br />

**For N = 123:**<br />
The average amount of disks used without sorting is: 72,449997.<br />
The average amount of disks used with sorting is: 64,239998.<br />
