/**
 * Created by Khiem on 1/9/2017.
 */
public class Constraint {
    public String getFirstVar() {
        return firstVar;
    }

    public void setFirstVar(String firstVar) {
        this.firstVar = firstVar;
    }

    public String getSecondVar() {
        return secondVar;
    }

    public void setSecondVar(String secondVar) {
        this.secondVar = secondVar;
    }

    private String firstVar;
    private String secondVar;

    public Constraint(String first, String second) {
        firstVar = first;
        secondVar = second;
    }
    public Constraint getOppositeConstraint() {
        return new Constraint(firstVar, secondVar);
    }
}
