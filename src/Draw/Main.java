package Draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class Main extends JFrame {

    private final JPanel mainPanel;
    private DrawingPanel drawingPanel;
    private JButton start;
    private JButton stop;
    private JButton clear;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        super("B3/S23");
        int w = 1200;
        int h = 800;
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        setContentPane(mainPanel);

        setLeftSide();
        setRightSide();
        init();
        setOnClicks();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(w, h));
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setLeftSide() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(800, 800));
        int RESOLUTION = 100;
        drawingPanel = new DrawingPanel(800, 800, RESOLUTION);
        panel.add(drawingPanel);
        mainPanel.add(panel);
    }

    private void setRightSide() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(200, 800));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        start = new JButton("Start");
        centerPanel.add(start, gbc);

        centerPanel.add(Box.createVerticalStrut(50));

        stop = new JButton("Stop");
        centerPanel.add(stop, gbc);

        centerPanel.add(Box.createVerticalStrut(50));

        clear = new JButton("Clear");
        centerPanel.add(clear, gbc);

        mainPanel.add(centerPanel);
    }

    private void init() {
        for (int i = 0; i < 10000; i++) {
            double x = Math.random();
            boolean eww = false;
            if (x > 0.5f) {
                eww = true;
            }
            DrawingPanel.setSection(i, eww);
        }
    }

    private void setOnClicks() {
        ActionListener taskPerformer = evt -> {
            boolean[] arr = DrawingPanel.getSectionsValues();
            for (int i = 101; i < 9899; i++) {
                if (DrawingPanel.getSectionValue(i) == 0){
                    if (willBorn(i)) {
                        arr[i] = true;
                    }
                }
                if (DrawingPanel.getSectionValue(i) == 1) {
                    if (!willSurvive(i)) {
                        arr[i] = false;
                    }
                }
            }
            for (int i = 0; i < 10000; i++) {
                DrawingPanel.setSection(i, arr[i]);
            }
            repaint();
        };
        Timer timer = new Timer(100, taskPerformer);
        start.addActionListener(e -> {
            timer.start();
        });
        stop.addActionListener(e -> {
            timer.stop();
        });
        clear.addActionListener(e -> drawingPanel.clear());
    }

    private boolean willBorn(int index) {
        boolean willBorn = false;
        int count = 0;
        if (DrawingPanel.getSectionValue(index-1) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+1) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index-101) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index-100) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index-99) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+101) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+100) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+99) == 1) count+=1;

        if (count == 3) {
            willBorn = true;
        }
        return willBorn;
    }

    private boolean willSurvive(int index) {
        boolean willLive = false;
        int count = 0;
        if (DrawingPanel.getSectionValue(index-1) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+1) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index-101) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index-100) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index-99) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+101) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+100) == 1) count+=1;
        if (DrawingPanel.getSectionValue(index+99) == 1) count+=1;

        if (count == 2 || count == 3) {
            willLive = true;
        }
        return willLive;
    }
}

