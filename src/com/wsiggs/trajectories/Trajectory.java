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

    private double[] rotationVals;
    private double[] velocityVals;

    private int index = 0;

    public Trajectory(double[] rotationVals, double[] velocityVals, double time, ReferencePoint... refPoints)
    {
        for(int i = 0; i < velocityVals.length; i++)
        {
            velocityVals[i] *= time;
        }

        for(int i = 0; i < rotationVals.length; i++)
        {
            rotationVals[i] *= time;
        }

        this.rotationVals = rotationVals;
        this.velocityVals = velocityVals;

        sIncrement = 1.0/(50.0*time);
        spline1 = new Spline(time, sIncrement, refPoints);


        setPoints = new ArrayList<>();

        for(double s = 0; s <= 1.0; s += sIncrement)
        {
            index++;
            setPoints.add(new Setpoint(s, spline1.getX(s), spline1.getY(s), spline1.getDX(s),  spline1.getDY(s), spline1.getdYdX(s), spline1.getRobotVel(velocityVals, s), spline1.getH(s), spline1.getDH(s), spline1.getdHdS(s), spline1.getRobotRot(rotationVals, s), s*time));
        }
    }

    public void spew()
    {
        for (Setpoint sp : setPoints)
            System.out.println(sp);
    }

    public ArrayList<Setpoint> getSetpoints()
    {
        return setPoints;
    }
}
