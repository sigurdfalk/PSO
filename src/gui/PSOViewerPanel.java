package gui;

import core.Particle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * User: sigurd
 * Date: 16/11/13
 * Time: 15:05
 */
public class PSOViewerPanel extends JPanel {
    private ArrayList<Particle> particles;
    private double globalBestFitness;
    private double moveY;
    private double scale;

    public PSOViewerPanel(double scale) {
        this.particles = new ArrayList<Particle>();
        this.globalBestFitness = 0;
        this.moveY = 0;
        this.scale = scale;
    }

    public void update(ArrayList<Particle> particles, double globalBestFitness, double moveY) {
        this.particles = particles;
        this.globalBestFitness = globalBestFitness;
        this.moveY = moveY;
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.scale(1.0, 1.0);
        graphics2D.translate(0, moveY);

        graphics2D.setColor(Color.BLACK);
        Font textFont = new Font("Arial", Font.BOLD, 10);
        g.setFont(textFont);
        graphics2D.draw(new Line2D.Double(35, -500, 35, 1500));

        for (int i = -500; i < 1500; i++) {
            if (i % 10 == 0) {
                graphics2D.draw(new Line2D.Double(32, i, 38, i));
            }

            if (i % 50 == 0) {
                graphics2D.draw(new Line2D.Double(30, i, 40, i));
                graphics2D.drawString(Double.toString(i / scale), 1, i - 1);
            }
        }

        graphics2D.setColor(Color.RED);
        graphics2D.draw(new Line2D.Double(30.0, globalBestFitness * scale, this.getWidth(), globalBestFitness * scale));

        graphics2D.setColor(Color.BLACK);

        for (int i = 0; i < particles.size(); i++) {
            graphics2D.fill(new Ellipse2D.Double(40.0 + i * (((double) this.getWidth() - 40.0) / particles.size()), particles.get(i).getFitness() * scale, 2, 2));
        }
    }
}
