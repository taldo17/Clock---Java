package clock.ui;

import clock.logic.Clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Desktop {
    JFrame frame = new JFrame("Clock");
    JPanel panel = new JPanel(new GridBagLayout());
    CyanLight cyanLight = new CyanLight(panel);
    Clock clock = new Clock(cyanLight);
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    JButton getUpdatedTime = new JButton("Time");
    JButton modeButton = new JButton("Mode");
    JButton actionButton = new JButton("Action");
    JLabel timeLabel = new JLabel(clock.toString());
    JLabel modeLabel = new JLabel(clock.getMode().toString());
    JTextField schedulingTextField = new JTextField("00:00:00");

    public Desktop() {
    }

    public void init() {
        configureJFrameSettings();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        setEventListeners();
        schedulingTextField.setEnabled(false);
        addComponentToPanel(0, 1, this.getUpdatedTime);
        addComponentToPanel(0, 2, modeButton);
        addComponentToPanel(1, 1, timeLabel);
        addComponentToPanel(2, 1, actionButton);
        addComponentToPanel(1, 2, modeLabel);
        addComponentToPanel(2, 2, schedulingTextField);
        frame.add(panel, BorderLayout.WEST);
        setIcon();

        clock.start();
        frame.setVisible(true);
    }

    private void setEventListeners() {
        getUpdatedTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timeLabel.setText(clock.toString());
            }
        });
        modeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clock.changeMode();
                modeLabel.setText(clock.getMode().toString());
            }
        });
        actionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clock.action();
            }
        });
    }

    private void setIcon() {
        URL url = getClass().getResource("/clock.jpg");
        ImageIcon imgicon = new ImageIcon(url);
        frame.setIconImage(imgicon.getImage());
    }

    private void configureJFrameSettings() {
        frame.setSize(260, 200);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addComponentToPanel(int gridx, int gridy, JComponent component) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        panel.add(component, gridBagConstraints);
    }

}
