 
<h1 align="center">RFID-Counting</h1>
 
 
## Introduction
This artifact presents the implemental details of ATD. ATD is an efficient RFID counting algorithm based on low-cost commodity RFID devices. Instead of collecting all tag IDs one by one, ATD leverages the Average Time Durations (ATD) between a few tag responses as the metric to counting the number of tags, which can make RFID counting efficient. Since this metric is C1G2 compatible, ATD is able to be implemented by COTS RFID devices. In this artifact appendix, we give the source code and introduce how to run it with an F800 RFID reader from Alien Inc.
 
## Getting Started
#### 1. Environment settings:
The software development of the system is Java. The JDK version is 1.80\_121 and [Intellij IDEA](https://www.jetbrains.com/idea/) is severed as the compiler for editing and running these codes. The Java codes are based on [Alien SDK](https://www.alientechnology.com/products/readers/alr-f800/) and are used to measure the Average Time Durations (ATD) from tag responses. The measurement of ATD will be used to predicte the number of tags within a reader's coverage zone.

#### 2. Measure Average Time Duration:
Connect an RFID reader to the computer. The method to use Alien-F800 reader can be found in [Alien-F800](https://www.alientechnology.com/products/readers/alr-f800/).

After that, run AlienUtil.java to initialize the setting of RFID reader. 

Finally, start CountingDemo.java and the number of tags will be printed in the command line. The data format is as follows:

    Best Q Value： 7
    Average Time Duration: 9
    Estimated Number of Tags: 700

The Average Time Duration of a tag is the average time difference between adjacent tag replies.
 
#### 3. Data processing:
The method used in our paper is given in ATDMethods.java. We show how to estimate the number of tags by the measurement of ATD in CountingDemo.java. The result is printed in the command line.
 
#### 4. Evaluation:
You can calculate the estiamtion error of ATD by running GroundTruth.java. In this class, we obtain the true number of tags by esclusively collecting all tag IDs.

In folder Matlab-Code, we plot the estimation error and the time cost of ATD. We also compare the performance of ATD with the state of the arts through simulations.

In folder Video, we use a video example to show how to run ATD in practice .
 
## Project Structure 
    code
     └─Alien
     │   ├─.idea
     │   │  └─libraries  
     │   ├─lib
     │   └─src
     └─Matlab-Code
     │    ├─data
     │    └─functions
     └─Video

The measurement codes are in folder Alien\src. The evaluation codes are in folder Matlab-Code.
