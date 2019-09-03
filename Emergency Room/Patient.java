
public class Patient implements Comparable<Patient>
{
    private String name;
    private int age;
    private boolean insurance;
    private double intangibles;
    private int time;
    private Disease disease;
    private int timeOfDeath;
    private int priority;

    public Patient(String name, int age, boolean insurance, double intangibles) {
        this.name = name;
        this.age = age;
        this.insurance = insurance;
        this.intangibles = intangibles;
        time = 0;
        disease = null;
        timeOfDeath = -1;
        priority = 10;
    }
    
    public void incrementAge() {
        age++;
    }
    
    public Disease setDisease(Disease dis) {
        disease = dis;
        return dis;
    }
    
    public double chanceOfDeath() {
        return (Math.sqrt(Math.abs(age - 25)) + disease.getSeverity()/10 + intangibles);    
    }
    
    public void setTimeOfDeath() {
        if(Math.random() < chanceOfDeath() / 100.0)
            timeOfDeath = (int)(Math.random() * time);
        else
            timeOfDeath = -1;
    }
    
    public int getTimeOfDeath() {
        return timeOfDeath;
    }

    public int setTime(boolean waitlist) {
        if(waitlist)
            time = disease.getUntreatedTimeToDeath();
        else
            time = disease.getTreatmentTime();
        return time;
    }

    public int decreaseTime(int multiplier) {
        time = time - multiplier;
        return time;
    }
    
    public int getTime() {
        return time;
    }

    public void setPriority() {
        priority = 11 - (int)(Math.round((chanceOfDeath() + 5) / 10));
        if(priority < 1)
            priority = 1;
        if(priority < 9 && !insurance)
            priority += 2;
    }
    
    public int getPriority() {
        return priority;
    }

    public int compareTo(Patient patient) {
        int priority = getPriority()  - patient.getPriority();
        return priority;
    }

    @Override
    public String toString() {
        String in;
        if(insurance)
            in = "Yes";
        else 
            in = "No";
        return "Name: " + name + "\nAge: " + age + "\nInsurance: " + in + "\n" + disease.toString() + "\nPriority: " + getPriority();
    }
}
