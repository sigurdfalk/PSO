package gui;

import core.Particle;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * User: sigurd
 * Date: 16/11/13
 * Time: 15:03
 */
public class PSOViewer extends JFrame {
    private PSOViewerPanel psoViewerPanel;
    private double moveY;
    private ArrayList<Particle> particles;
    private double globalBestFitness;

    public PSOViewer(PSOViewerPanel psoViewerPanel) {
        this.psoViewerPanel = psoViewerPanel;

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(psoViewerPanel, BorderLayout.CENTER);

        addMouseMotionListener(new MouseListener());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PSO Viewer");
        setSize(825, 1000);
    }

    public void drawUI(ArrayList<Particle> particles, double globalBestFitness) {
        this.particles = particles;
        this.globalBestFitness = globalBestFitness;
        psoViewerPanel.update(particles, globalBestFitness, moveY);
    }

    private class MouseListener extends MouseInputAdapter {
        private double x;
        private double y;

        @Override
        public void mouseMoved(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            moveY += (e.getY() - y);

            x = e.getX();
            y = e.getY();

            psoViewerPanel.update(particles, globalBestFitness, moveY);
        }
    }
}
