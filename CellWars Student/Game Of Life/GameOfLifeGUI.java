import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameOfLifeGUI extends JFrame {

  private static final long serialVersionUID = 1L;

  public static void main(String[] args) {
    new GameOfLifeGUI().setVisible(true);
  }

  private GameOfLife society;
  private JButton stepButton = new JButton("Step");
  private JButton animationButton = new JButton("Start");
  private JButton clearButton = new JButton("Clear");
  private JComboBox<?> selections;
  private JLabel updateCounterLabel = new JLabel("Updates: ");
  private Timer timer;
  private int delay = 500; // Milliseconds
  private int updateCounter = 0;
  private String[] list = { "Pre Made", "Gliders", "Queen", "Mystery" };
  private JSlider sliderBar;
  public static int ROWS = 40;
  public static int COLS = 70;
  private boolean frozen = true;
  private JButton[][] cells;

  public GameOfLifeGUI() {
    setTitle("John Conway's Game of Life");
    setSize(880, 630);
    setLocation(20, 40);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    society = new GameOfLife(ROWS, COLS);
    JPanel panelWithCells = new JPanel();
    panelWithCells.setLayout(new GridLayout(ROWS, COLS));
    cells = new JButton[ROWS][COLS];

    clearButton.addActionListener(new ClearButtonListener());

    ButtonListener buttonListener = new ButtonListener();
    for (int r = 0; r < ROWS; r++)
      for (int c = 0; c < COLS; c++) {
        cells[r][c] = new JButton();
        cells[r][c].setBackground(Color.LIGHT_GRAY);
        cells[r][c].addActionListener(buttonListener);
        cells[r][c].setBorderPainted(true);
        cells[r][c].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
            Color.BLACK));
        panelWithCells.add(cells[r][c]);
      }

    Container cp = getContentPane();
    cp.add(new JLabel("     "), BorderLayout.WEST);
    cp.add(new JLabel(), BorderLayout.EAST);
    cp.add(panelWithCells, BorderLayout.CENTER);
    animationButton.addActionListener(new AnimationButtonListener());
    stepButton.addActionListener(new StepButtonListener());

    selections = new JComboBox<Object>(list);
    selections.addActionListener(new SelectionListener());
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new GridLayout(1, 7, 50, 10));
    topPanel.add(new JLabel(""));
    topPanel.add(stepButton);
    topPanel.add(animationButton);
    topPanel.add(selections);
    topPanel.add(clearButton);
    topPanel.add(updateCounterLabel);
    topPanel.add(new JLabel(""));
    cp.add(topPanel, BorderLayout.NORTH);
    sliderBar = new JSlider();
    sliderBar = new JSlider(JSlider.HORIZONTAL, 0, 2000, delay);
    sliderBar.addChangeListener(new SliderListener());

    // Turn on labels at major tick marks.
    sliderBar.setMajorTickSpacing(100);
    sliderBar.setMinorTickSpacing(1);
    sliderBar.setPaintTicks(true);
    sliderBar.setPaintLabels(true);
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(sliderBar, BorderLayout.CENTER);
    p.add(new JLabel(" Delay in milliseconds ", JLabel.CENTER),
        BorderLayout.SOUTH);
    cp.add(p, BorderLayout.SOUTH);

    setUpTimer();
  }

  public void setUpTimer() {
    timer = new Timer(delay, new SocietyUpdater());
    timer.setInitialDelay(0);
    timer.setCoalesce(true);
  }

  // From the Java Tutorial
  // Can be invoked by any thread (since timer is thread-safe).
  public void startAnimation() {
    if (!frozen && !timer.isRunning()) {
      timer.start();
    }
  }

  // From the Java Tutorial
  // Can be invoked by any thread (since timer is thread-safe).
  public void stopAnimation() {
    // Stop the animating thread.
    if (timer.isRunning())
      timer.stop();
  }

  private class SocietyUpdater implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      updateCounterLabel.setText("Updates: " + ++updateCounter);
      society.update();
      updateButtons();
      readTheButtons();
    }
  }

  // Adapted from the Java Tutorial
  private class AnimationButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      if (frozen) {
        frozen = false;
        animationButton.setText("Stop ");
        readTheButtons();
        startAnimation();
      } else {
        frozen = true;
        animationButton.setText("Start");
        stopAnimation();
      }
    }
  }

  private class SliderListener implements ChangeListener {
    public void stateChanged(ChangeEvent arg0) {
      timer.setDelay(sliderBar.getValue());
    }
  }

  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      JButton clickedButton = (JButton) ae.getSource();
      if (clickedButton.getText().equals(" ") && frozen) {
        clickedButton.setText("O");
      } else {
        clickedButton.setText(" ");
      }
    }
  }

  private class StepButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (!frozen)
        stopAnimation();
      readTheButtons();
      society.update();
      updateButtons();
    }
  }

  private void readTheButtons() {
    for (int r = 0; r < society.numberOfRows(); r++)
      for (int c = 0; c < society.numberOfColumns(); c++)
        if (cells[r][c].getText().equals("O"))
          society.growCellAt(r, c);
  }

  public void updateButtons() {
    for (int r = 0; r < society.numberOfRows(); r++)
      for (int c = 0; c < society.numberOfColumns(); c++)
        if (society.cellAt(r, c)) {
          cells[r][c].setText("O");
        } else {
          cells[r][c].setText(" ");
        }
  }

  private class ClearButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      clearButtons();
    }
  }

  private void clearButtons() {
    society = new GameOfLife(ROWS, COLS);
    this.stopAnimation();
    updateCounter = 0;
    updateCounterLabel.setText("Updates: " + updateCounter);

    for (int r = 0; r < society.numberOfRows(); r++)
      for (int c = 0; c < society.numberOfColumns(); c++) {
        // cells[r][c].setBackground(Color.LIGHT_GRAY);
        cells[r][c].setText(" ");
      }
  }

  private class SelectionListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      stopAnimation();
      JComboBox<?> theList = (JComboBox<?>) ae.getSource();
      int index = theList.getSelectedIndex();
      String choice = list[index];
      clearButtons();
      if (choice.equals("Queen"))
        queen();
      else if (choice.equals("Gliders"))
        gliders();
      else if (choice.equals("Mystery"))
        mystery();

      readTheButtons();
      updateButtons();
      startAnimation();
    }
  }

  private void queen() {
    // queen
    society = new GameOfLife(ROWS, COLS);
    society.growCellAt(10, 21);
    society.growCellAt(11, 21);
    society.growCellAt(12, 22);
    society.growCellAt(13, 23);
    society.growCellAt(13, 24);
    society.growCellAt(13, 25);
    society.growCellAt(12, 26);
    society.growCellAt(11, 27);
    society.growCellAt(10, 27);
  }

  private void gliders() {
    // gliders
    society = new GameOfLife(ROWS, COLS);
    for (int i = 0; i < 40; i += 8) {
      society.growCellAt(5, 10 + i);
      society.growCellAt(6, 11 + i);
      society.growCellAt(6, 12 + i);
      society.growCellAt(7, 11 + i);
      society.growCellAt(7, 10 + i);
    }
  }

  private void mystery() {
    society = new GameOfLife(ROWS, COLS);
    society.growCellAt(5, 5);
    society.growCellAt(6, 4);
    society.growCellAt(6, 6);
    society.growCellAt(7, 4);
    society.growCellAt(7, 6);
    society.growCellAt(8, 5);
    society.growCellAt(5, 15);
    society.growCellAt(6, 14);
    society.growCellAt(6, 16);
    society.growCellAt(7, 15);
    society.growCellAt(7, 16);
    society.growCellAt(5, 25);
    society.growCellAt(5, 26);
    society.growCellAt(6, 25);
    society.growCellAt(6, 27);
    society.growCellAt(7, 26);
    society.growCellAt(7, 27);
    society.growCellAt(5, 35);
    society.growCellAt(5, 36);
    society.growCellAt(6, 34);
    society.growCellAt(6, 37);
    society.growCellAt(7, 37);
    society.growCellAt(7, 35);
    society.growCellAt(8, 36);
  }
}