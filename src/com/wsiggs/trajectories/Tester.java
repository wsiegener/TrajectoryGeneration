package com.wsiggs.trajectories;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Tester extends JPanel
{
    private static final double PPI = 2.0;
    private static final double TRACK_WIDTH = 12.0;
    private static final int K_WIDTH = 640, K_HEIGHT = 480;

    private DraggablePoint[] editPoints =
            {
                    new DraggablePoint("p1"),
                    new DraggablePoint("p2"),
                    new DraggablePoint("p3"),
                    new DraggablePoint("p4")
            };


    private static ReferencePoint[] refPoints =
            {
                new ReferencePoint(50,50),
                new ReferencePoint(80,400),
                new ReferencePoint(200,300),
                new ReferencePoint(400,250)
            };

    public Trajectory traj;
    public static Tester content;

    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame("Splines");
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            content = new Tester();
            frame.add(content);

            JButton addPointButton = new JButton();
            addPointButton.setText("Add Point");
            addPointButton.addActionListener(e ->
                    {

                    });

            frame.pack();
            frame.setVisible(true);
        });
    }

    public Tester()
    {
        traj = new Trajectory(5.0, refPoints);

        Dimension size = new Dimension(640, 480);
        setSize(size);
        setPreferredSize(size);
        setLayout(null);

        for(int i = 0; i < refPoints.length; i++)
        {
            initRefPoint(editPoints[i], i);
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {
        System.out.println("paintComp tester");

        traj = getUpdatedTrajectory();

        Graphics2D g2d = (Graphics2D) g;

        g2d.clearRect(0,0, K_WIDTH,K_HEIGHT);

        g2d.setStroke(new BasicStroke(2));

        g2d.draw(generateRefPath(traj));

        g2d.setColor(Color.RED);
        g2d.draw(generateSplinePath(traj));

        g2d.setColor(Color.BLUE);
        g2d.draw(generateLeftPath(traj));
        g2d.draw(generateRightPath(traj));

        for(DraggablePoint p : editPoints)
        {
            if(p.isChanged)
            {
                this.repaint();
                p.setUpdated();
            }
        }
    }

    public void initRefPoint(DraggablePoint r, int num)
    {
        r.setOpaque(false);
        r.setContentAreaFilled(false);
        r.setBorderPainted(false);
        r.setFocusPainted(false);

        r.setLocation((int)traj.getReferencePoints()[num].getX(), K_HEIGHT - (int)traj.getReferencePoints()[num].getY());
        r.setSize(10, 10);
        add(r);
    }

    public Trajectory getUpdatedTrajectory()
    {
        ReferencePoint[] newRefs = new ReferencePoint[editPoints.length];

        for(int i = 0; i < editPoints.length; i++)
        {
            newRefs[i] = new ReferencePoint(editPoints[i].getX()-5, K_HEIGHT - editPoints[i].getY() + 5);
        }

        return new Trajectory(5, newRefs);
    }

    public Path2D generateRefPath(Trajectory t)
    {
        Path2D p = new Path2D.Double();
        double ref_x, ref_y;

        for (int i = 0; i < t.getReferencePoints().length; i++)
        {
            ref_x = 10 + t.getReferencePoints()[i].getX();
            ref_y = 10 + K_HEIGHT - t.getReferencePoints()[i].getY();

            if (i == 0)
                p.moveTo(ref_x, ref_y);
            else
                p.lineTo(ref_x, ref_y);
        }

        return(p);
    }

    public Path2D generateSplinePath(Trajectory t)
    {
        Path2D p = new Path2D.Double();
        double set_x, set_y;

        for(int i = 0; i < t.getSetpoints().size(); i++)
        {
            set_x = (10 + t.getSetpoints().get(i).getX());
            set_y = (10 + K_HEIGHT - t.getSetpoints().get(i).getY());

            if(i == 0)
                p.moveTo(set_x, set_y);
            else
                p.lineTo(set_x, set_y);
        }

        return p;
    }


    public Path2D generateLeftPath(Trajectory t)
    {
        Path2D p = new Path2D.Double();
        double set_x, set_y;

        for (int i = 0; i < traj.getSetpoints().size(); i++)
        {
            //if(i % 3 == 0)
            {
                set_x = (10 + t.getSetpoints().get(i).getX()) - (Math.sin(t.getSetpoints().get(i).getH()) * (PPI * TRACK_WIDTH));
                set_y = (10 + K_HEIGHT - t.getSetpoints().get(i).getY()) - (Math.cos(t.getSetpoints().get(i).getH()) * (PPI * TRACK_WIDTH));

                if(i == 0)
                    p.moveTo(set_x, set_y);
                else
                    p.lineTo(set_x, set_y);
            }
        }

        return(p);
    }

    public Path2D generateRightPath(Trajectory t)
    {
        Path2D p = new Path2D.Double();
        double set_x, set_y;

        for (int i = 0; i < traj.getSetpoints().size(); i++)
        {
            set_x = (10 + t.getSetpoints().get(i).getX()) + (Math.sin(t.getSetpoints().get(i).getH()) * (PPI * TRACK_WIDTH));
            set_y = (10 + K_HEIGHT - t.getSetpoints().get(i).getY()) + (Math.cos(t.getSetpoints().get(i).getH()) * (PPI * TRACK_WIDTH));

            if(i == 0)
                p.moveTo(set_x, set_y);
            else
                p.lineTo(set_x, set_y);
        }

        return(p);
    }
}
