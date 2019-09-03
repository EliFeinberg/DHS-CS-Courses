//Implement age increment after year passed
//Fix Timer(Not happening every 20 min)
//Correct Algorithm for Chance of Death (Too high right now)
//Decrease frequency with which patients come back
//Implement Doctors
//Max speed on slider ends with more deaths than there are people
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Timer;
import java.text.*;
import java.util.*;

public class Hospital extends JFrame implements ActionListener, ChangeListener
{   
    private int multiplier;
    private int lifeCount = 0;
    private int deathCount = 0;
    private Time time;
    private JLabel timeLabel;
    private JLabel lifeLabel;
    private JLabel deathLabel;
    private Timer timer;
    private JButton button;
    private JSlider speedSlider;
    private JTextArea waitlistArea;
    private ArrayList<Patient> availablePatientArr;
    private ArrayList<Disease> diseaseArr;
    private PriorityQueue<Patient> waitlist = new PriorityQueue<Patient>();
    PriorityQueue<Patient> waitlistCopy = new PriorityQueue<Patient>(waitlist);
    private ArrayList<Patient> rooms = new ArrayList<Patient>();
    ArrayList<Patient> dead = new ArrayList<Patient>();
    JPanel[] roomSubPanelArr = new JPanel[16];
    private final String DEFUALT_ROOM_TEXT = "Name:\nAge:\nInsurance:\nDisease:\nSeverity:\nPriority:";

    public Hospital(String name) {
        super(name);
        constructFrame();
        setFrameEssentials();
        readIn();
    }

    private void setFrameEssentials() {
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void constructFrame() {
        setLayout(new BorderLayout(5, 10));
        constructWaitlistPanel();
        constructSpeedPanel();
        constructRoomPanel();
        constructInfoPanel();
        pack();
    }

    private void constructInfoPanel() {
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        time = new Time();
        timer = new Timer(1000, this);
        timer.start();
        timeLabel = new JLabel(time.getTime(), JLabel.CENTER);
        timeLabel.setPreferredSize(new Dimension(200, 20));
        infoPanel.add(timeLabel);
        lifeLabel = new JLabel("Survived: " + lifeCount, JLabel.CENTER);
        infoPanel.add(lifeLabel);
        lifeLabel.setPreferredSize(new Dimension(192, 20));
        deathLabel = new JLabel("Deaths: " + deathCount, JLabel.LEFT);
        deathLabel.setPreferredSize(new Dimension(120, 20));
        infoPanel.add(deathLabel);
        button = new JButton("Stop");
        button.addActionListener(this);
        infoPanel.add(button);
        add(infoPanel, BorderLayout.NORTH);
    }

    private void constructWaitlistPanel() {
        JPanel waitlistPanel = new JPanel(new BorderLayout(0, 5));
        JLabel waitlistLabel = new JLabel("Waitlist", JLabel.CENTER);
        waitlistArea = new JTextArea();
        waitlistArea.setEditable(false);
        waitlistArea.setMargin(new Insets(4, 3, 0, 0));
        JScrollPane scroll = new JScrollPane(waitlistArea);
        waitlistPanel.add(waitlistLabel, BorderLayout.NORTH);
        waitlistPanel.add(scroll, BorderLayout.CENTER);
        waitlistPanel.setPreferredSize(new Dimension(200, 600));
        add(waitlistPanel, BorderLayout.WEST);
    }

    private void constructSpeedPanel() {
        JPanel speedPanel = new JPanel();
        speedSlider = new JSlider(0, 10, 0);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.addChangeListener(this);
        speedSlider.setPreferredSize(new Dimension(850, 50));
        speedPanel.add(speedSlider);
        add(speedPanel, BorderLayout.SOUTH);
    }

    private void constructRoomPanel() {
        GridLayout grid = new GridLayout(4, 4, 10, 10);
        JPanel roomPanel = new JPanel();  
        roomPanel.setLayout(grid);
        for(int i = 0; i < 16; i++) {
            JPanel roomSubPanel = new JPanel(new BorderLayout(0, 5));
            roomSubPanelArr[i] = roomSubPanel;
            JLabel roomTitle = new JLabel("Room " + (i + 1), JLabel.CENTER);
            JTextArea area = new JTextArea();
            area.setMargin(new Insets(10, 3, 0, 0));
            area.setText(DEFUALT_ROOM_TEXT);
            area.setEditable(false);
            roomSubPanel.add(roomTitle, BorderLayout.NORTH);
            roomSubPanel.add(area, BorderLayout.CENTER);
            roomPanel.add(roomSubPanel);
            rooms.add(null);
        }
        roomPanel.setPreferredSize(new Dimension(800, 600));
        add(roomPanel, BorderLayout.EAST);
    }

    private void update(){
        for(int i = 0; i < 16; i++) {
            String roomText = "Room " + (i + 1);
            JPanel subPanel = roomSubPanelArr[i];
            JLabel label = (JLabel)(subPanel.getComponent(0));
            JTextArea area = (JTextArea)(subPanel.getComponent(1));
            Patient patient;
            if(rooms.get(i) == null) {
                area.setBackground(Color.WHITE);
                if(!waitlist.isEmpty()) {
                    patient = waitlist.poll();
                    label.setText(roomText + " - " + patient.setTime(false));
                    patient.setTimeOfDeath();
                    rooms.set(i, patient);
                    area.setText(patient.toString());
                }
            }
            else if(rooms.get(i) != null) {
                patient = rooms.get(i);
                int time = patient.decreaseTime(multiplier);
                if(time < 0) {
                    area.setBackground(Color.WHITE);
                    label.setText(roomText);
                    area.setText(DEFUALT_ROOM_TEXT);
                    rooms.set(i, null);
                    lifeCount++;
                    lifeLabel.setText("Survived: " + lifeCount);
                    availablePatientArr.add(patient);
                }
                else if(time < patient.getTimeOfDeath()) {
                    area.setBackground(Color.WHITE);
                    label.setText(roomText);
                    area.setText(DEFUALT_ROOM_TEXT);
                    rooms.set(i, null);
                    deathCount++;
                    deathLabel.setText("Deaths: " + deathCount);
                }
                else {
                    area.setBackground(Color.GREEN);
                    label.setText(roomText + " - " + time);
                    area.setText(patient.toString());
                }
            }
        }
        waitlistArea.setText("");
        waitlistCopy.addAll(waitlist);
        int time = 0;
        for(Patient patient : waitlist) {
            time = patient.decreaseTime(multiplier);
            if(time < 0) {
                deathCount++;
                deathLabel.setText("Deaths: " + deathCount);
                waitlistCopy.remove(patient);
                dead.add(patient);
            }
        }
        waitlist.remove(dead);
        while(!waitlistCopy.isEmpty())
            waitlistArea.append(waitlistCopy.poll().toString() + "\nTime: " + time + "\n\n");
    }

    private void readIn(){
        try {
            Scanner diseaseList = new Scanner(new File("DISEASE.txt"));
            Scanner patientList = new Scanner(new File("PATIENTS.txt"));
            diseaseArr = new ArrayList<Disease>();
            availablePatientArr = new ArrayList<Patient>();
            while(diseaseList.hasNextLine()){
                String name = diseaseList.nextLine();
                int severity = Integer.parseInt(diseaseList.nextLine());
                int timeUntreated = Integer.parseInt(diseaseList.nextLine());
                int timeTreated = Integer.parseInt(diseaseList.nextLine());
                diseaseArr.add(new Disease(name, severity, timeUntreated, timeTreated));
            }
            while(patientList.hasNextLine()){
                String name = patientList.nextLine();
                int age = Integer.parseInt(patientList.nextLine());
                boolean  insurance;
                if(Math.random() < .1)
                    insurance = false;
                else
                    insurance = true;
                double intangibles = Math.random() * 10;
                availablePatientArr.add(new Patient(name, age, insurance, intangibles));
            }
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Insets getInsets() {
        return new Insets(30, 10, 10, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)) {
            if(!timer.isRunning()) {
                timer.start();
                button.setText("Stop");
            }
            else {
                timer.stop();
                button.setText("Start");
            }
        }
        else {
            int val = speedSlider.getValue();
            int field;
            double randomVal;
            if(val < 3) {
                field = GregorianCalendar.SECOND; 
                multiplier = 1;
                randomVal = .000833;
            }
            else if (val >= 3 && val < 7) {
                field = GregorianCalendar.MINUTE;
                multiplier = 60;
                randomVal = 0.05;
            }
            else {
                field = GregorianCalendar.HOUR;
                multiplier = 3600;
                randomVal = 1;
            }
            timeLabel.setText(time.increment(field));
            for(int i = 0; i < 3 * randomVal; i++) {
                if(Math.random() < randomVal && availablePatientArr.size() != 0) {
                    Patient patient = availablePatientArr.get((int)(Math.random() * availablePatientArr.size()));
                    patient.setDisease(diseaseArr.get((int)(Math.random() * diseaseArr.size())));
                    patient.setTime(true);
                    patient.setPriority();
                    availablePatientArr.remove(patient);
                    waitlist.add(patient);
                }
            }
            update();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int subtract;
        if(speedSlider.getValue() < 3)
            subtract = 0;
        else if(speedSlider.getValue() < 7)
            subtract = 3;
        else
            subtract = 7;
        timer.setDelay((int)(1000 / Math.pow(3, speedSlider.getValue() - subtract)));
    }
}