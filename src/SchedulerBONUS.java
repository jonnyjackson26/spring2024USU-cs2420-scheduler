import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class SchedulerBONUS {
    public void makeSchedule(String info, ArrayList<Task> t) {
        System.out.println(info);
        System.out.println(t.toString()+"\n");

        int totalTasksCompleted=0;
        int tasksThatNeedToBeCompleted=t.size();

        //make heap and insert my things into it
        //assume t is ordered by starttime, which it is in all the text files.
        Collections.reverse(t); // i need to reverse it so the earliest start times are last in the list. As i parse them, i delete them and therefore change the size of the arraylist. my for loop starts at t.size and decrements
        int earliestStartTime=t.getLast().start; //btw, for the txt files provided this will always be 1. but this logic means that doesnt necesarily need to be the case.
        LeftistHeap<Task> myHeap = new LeftistHeap<>();
        for(int i=t.size()-1; i>0; i--) {
            if(t.get(i).start==earliestStartTime) {
                //System.out.println("hour is 0, added task with start time "+t.get(i).start);
                myHeap.insert(t.get(i));
                t.remove(i);
            }
        }


        int totalHoursLate=0;
        int totalTasksLate=0;

        System.out.println("YOUR SCHECUDLE - - - -- ");
        int hours=1;
        while(totalTasksCompleted<tasksThatNeedToBeCompleted) {
            //add new Tasks with new starttimes to the heap
            for (ListIterator<Task> iterator = t.listIterator(t.size()); iterator.hasPrevious();) { //listIterator class
                Task currentTask = iterator.previous();
                if (currentTask.start <= hours) {
                    //System.out.println("added element to heap, start time is " + currentTask.start + " and hours is " + hours);
                    myHeap.insert(currentTask);
                    iterator.remove();
                } else {
                    break;
                }
            }


            if(myHeap.isEmpty()) {
                System.out.println("Hour "+hours+": break time!");
                hours++;
            } else {
                System.out.print("Hour "+hours+": do Task "+(myHeap.viewMin().ID+1));
                if(myHeap.viewMin().deadline<hours) {
                    System.out.print(" - "+(hours-myHeap.viewMin().deadline)+" hours late");
                    //totalHoursLate=totalHoursLate+(hours-myHeap.viewMin().deadline); //this was my algorithm for finding hours late but its not what the assignment called for
                    if(myHeap.viewMin().duration-1==0) {
                        totalTasksLate++;
                        totalHoursLate=totalHoursLate+(hours-myHeap.viewMin().deadline);
                    }
                }

                if(myHeap.viewMin().duration-1==0) {
                    myHeap.deleteMin();
                    System.out.print(" - Completed");
                    totalTasksCompleted++;
                } else {
                    //the variant that tasks can be split up:
//                    Task min = myHeap.deleteMin();
//                    min.duration--;
//                    myHeap.insert(min);

                    //tasks cant be split up:
                    myHeap.viewMin().duration--;

                    System.out.print(" - then "+myHeap.viewMin().duration+" hours left");
                }

                System.out.println("");//newline
                hours++;
            }
        }
        System.out.println("Total hours late: "+totalHoursLate);
        System.out.println("Total tasks late: "+totalTasksLate);


    }
}