
public class Task1 extends Task {
    public Task1(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    // Prioirity is deadline
    @Override
    public int compareTo(Task t2) { //earliest starttime, if tied, earliest deadline
         //System.out.println("Using Task1 compareTo");
        if(start-t2.start==0) {
            return deadline-t2.deadline;
        }
        return start-t2.start;
    }

    @Override
    public String toString() {
        return super.toStringL();
    }

}
