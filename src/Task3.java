
public class Task3 extends Task {
    public Task3(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    // Prioirity is deadline
    @Override
    public int compareTo(Task t2) { //based off urgency, which i define as deadline-start time
        //System.out.println("Using Task1 compareTo");
        return (deadline-start)-(t2.deadline-t2.start);
    }

}
