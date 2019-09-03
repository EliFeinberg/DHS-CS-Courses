import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrontEnd extends JFrame implements ActionListener
{
    private static final Font FONT = new Font("Arial", Font.PLAIN, 15);
    private JPanel mainPanel;
    private JTextField entryField;
    public FrontEnd(String name) {
        super(name);  
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  
        setLayout(new FlowLayout());
        constructEntryPanel();
        constructMainPanel();
        pack();
        setLocationRelativeTo(null);
    }

    private void constructEntryPanel() {
        JPanel entryPanel = new JPanel();
        entryField = new JTextField();
        JButton goButton = new JButton("Baconate");
        entryPanel.setPreferredSize(new Dimension(100, 100));
        entryField.setPreferredSize(new Dimension(100, 30));
        goButton.setPreferredSize(new Dimension(100, 45));
        goButton.setFont(FONT);
        goButton.addActionListener(this);
        entryPanel.add(entryField);
        entryPanel.add(goButton);
        add(entryPanel);
    }

    private void constructMainPanel() {
        mainPanel = new JPanel();
        JLabel bacon = new JLabel(new ImageIcon(".//Bacon.jpg"));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(bacon);
        add(mainPanel);
    }

    public void update(String[] names) {
        mainPanel.removeAll();
        entryField.setText("");
        for(int i = 0; i < names.length; i++) {
            JTextArea item = new JTextArea();
            item.setPreferredSize(new Dimension(100, 100));
            item.setEditable(false);
            item.setMargin(new Insets(3, 3, 3, 3));
            item.setFont(FONT);
            item.setText(names[i]);
            item.setLineWrap(true);
            item.setWrapStyleWord(true);
            mainPanel.add(item);
            if(i != names.length - 1) {
                JLabel arrow = new JLabel(new ImageIcon(".//arrow.png"));
                mainPanel.add(arrow);
            }
        }
        pack();
        setLocationRelativeTo(null);
    }
    public String getActor(){return entryField.getText();}
    public void actionPerformed(ActionEvent e) {
        String name = entryField.getText();
        //Process name, get the array, call update() with it
    }
}
