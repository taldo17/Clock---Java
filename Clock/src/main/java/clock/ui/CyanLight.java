package clock.ui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyanLight {
    private JPanel jPanel;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public CyanLight(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public void lightOn() {
        executorService.submit(() -> {
                    synchronized (Object.class) {
                        Color initialColor = jPanel.getBackground();
                        jPanel.setBackground(Color.CYAN);
                        try {
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        jPanel.setBackground(initialColor);
                    }
                }
        );
    }


}
