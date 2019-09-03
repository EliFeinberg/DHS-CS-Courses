
public class Disease
{
    private String name;
    private int severity;
    private int untreatedTimeToDeath;
    private int treatmentTime;
    
    public Disease(String name, int severity, int untreatedTimeToDeath, int treatmentTime) {
        this.name = name;
        this.severity = severity;
        this.untreatedTimeToDeath = untreatedTimeToDeath;
        this.treatmentTime = treatmentTime;
    }
    
    public int getSeverity() {
        return severity;
    }
    
    public int getUntreatedTimeToDeath() {
        return untreatedTimeToDeath;
    }
    
    public int getTreatmentTime() {
        return treatmentTime;
    }
    
    @Override
    public String toString() {
        return "Disease: " + name + "\nSeverity: " + severity;
    }
}
