package com.wsiggs.trajectories;

import java.util.ArrayList;

/**
 * @author Siggy
 *         $
 */
public class Trajectory
{
    private Spline spline1;
    private ArrayList<Setpoint> setPoints;
    private double sIncrement;

    public Trajectory(double time, ReferencePoint... refPoints)
    {
        sIncrement = 1.0/(50.0*time);
        spline1 = new Spline(time, sIncrement, refPoints);

        setPoints = new ArrayList<>();

        for(double s = 0; s <= 1.0; s += sIncrement)
        {
            setPoints.add(new Setpoint(s, spline1.getX(s), spline1.getY(s), spline1.getDX(s),  spline1.getDY(s), spline1.getdYdX(s), spline1.getH(s),spline1.getDH(s), spline1.getdHdS(s), s*time));
        }
    }

    public void spew()
    {
        for (Setpoint sp : setPoints)
            System.out.println(sp);
    }

}
