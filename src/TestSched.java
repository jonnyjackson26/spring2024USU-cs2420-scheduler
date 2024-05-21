import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestSched {


    public static void readTasks(String filename,
                          ArrayList<Task> task1, ArrayList<Task> task2,
                                 ArrayList<Task> task3) {
        // Create lists where base type is different
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int idCntr=0; //counter for id
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] values = data.split("\\s+"); // Split the line into individual values

                if(values.length==3) {//to protect against empty lines in the text file
                    int[] vals = new int[3];

                    // for each value as an integer and store in array
                    for (int i = 0; i < 3; i++) {
                        vals[i] = Integer.parseInt(values[i]);
                    }
                    task1.add(new Task1(idCntr, vals[0], vals[1], vals[2]));
                    task2.add(new Task2(idCntr, vals[0], vals[1], vals[2]));
                    task3.add(new Task3(idCntr, vals[0], vals[1], vals[2]));

                    idCntr++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in reading the file.");
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        Scheduler s = new Scheduler();
        String [] files = {"tasksets/taskset1.txt","tasksets/taskset2.txt","tasksets/taskset3.txt","tasksets/taskset4.txt","tasksets/taskset5.txt" };
        for (String f : files) {
            ArrayList<Task> t1 = new ArrayList();    // elements are Task1
            ArrayList<Task> t2 = new ArrayList();    // elements are Task2
            ArrayList<Task> t3 = new ArrayList();    // elements are Task3
            readTasks(f, t1, t2, t3);
            s.makeSchedule("Earliest start time (if tied, earliest deadline) Priority "+ f, t1);
            s.makeSchedule("Earliest deadline Priority " + f, t2);
            s.makeSchedule("Urgency priority, which i define as deadline-start time" + f, t3);
       }

        //BONUS - - - - -- - - - - - - - - - - -- - -

//        SchedulerBONUS s = new SchedulerBONUS();
//        String [] files = {"tasksets/taskset1.txt","tasksets/taskset2.txt","tasksets/taskset3.txt","tasksets/taskset4.txt","tasksets/taskset5.txt" };
//        for (String f : files) {
//            ArrayList<Task> t1 = new ArrayList();    // elements are Task1
//            ArrayList<Task> t2 = new ArrayList();    // elements are Task2
//            ArrayList<Task> t3 = new ArrayList();    // elements are Task3
//            readTasks(f, t1, t2, t3);
//            s.makeSchedule("Earliest start time (if tied, earliest deadline) Priority "+ f, t1);
//            s.makeSchedule("Earliest deadline Priority " + f, t2);
//            s.makeSchedule("Urgency priority, which i define as deadline-start time" + f, t3);
//        }

        //The goal that we are trying to achieve is to minimize the amount of time jobs spend in
        //the ready queue. Consider a scheduling method that performs well under this stipulation.

        //THE BEST METHOD TO TO HAVE TASKS SPEND THE LEAST AMOUNT OF TIME IN THE QUEUE WOULD BE
        //MY IMPLEMENTATION OF TASK3, WHICH SCHEDUELS BASED ON deadline-start time. THIS PRIORITIZES
        //TASKS THAT NEED TO BE STARTED SOONER TO BE FINISHED BEFORE THE DEADLINE, IF POSSIBLE. AND
        //WHEN RUNNING THE TEST ABOVE, YOU SEE THAT THE Task3 CLASS (ALMOST) ALWAYS GIVES THE LOWEST
        //NUMBER OF TOTAL TASKS LATE AND TOTAL HOURS LATE.


    }
}
