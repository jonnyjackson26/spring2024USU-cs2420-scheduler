
public class Task2 extends Task {
    public Task2(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    // Prioirity is deadline
    @Override
    public int compareTo(Task t2) { //earliest deadline
        //System.out.println("Using Task1 compareTo");
        return deadline-t2.deadline;
    }

    @Override
    public String toString() {
        return super.toStringL();
    }

}
