package com.wsiggs.trajectories;

import java.text.DecimalFormat;

/**
 * @author Siggy
 *         $
 */
public class Setpoint
{
    private double sVal;        // "s" value, percent completion
    private double x, y;        // X and Y position
    private double dX, dY;      // dX/ds and dY/ds
    private double dYdX;        // Linear speed
    private double robotVel;    // Robot speed values, [0,1]
    private double h;           // Current heading in degrees
    private double dH;          // change in heading between points
    private double dHdS;        // change in heading with respect to time
    private double robotRot;    // Rotation value for the robot [-1, 1]
    private double timeStamp;   // Time in seconds

    private DecimalFormat df;

    public Setpoint(double sVal, double x, double y, double dX, double dY, double dYdX, double robotVel, double h, double dH, double dHdS, double robotRot, double timeStamp)
    {
        this.sVal = sVal;
        this.x = x;
        this.y = y;
        this.dX = dX;
        this.dY = dY;
        this.dYdX = dYdX;
        this.robotVel = robotVel;
        this.h = h;
        this.dH = dH;
        this.dHdS = dHdS;
        this.robotRot = robotRot;
        this.timeStamp = timeStamp;

        df = new DecimalFormat("#.###");
    }

    public double getsVal()
    {
        return sVal;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getdX()
    {
        return dX;
    }

    public double getdY()
    {
        return dY;
    }

    public double getdYdX()
    {
        return dYdX;
    }

    public double getRobotVel() { return robotVel; }

    public double getH()
    {
        return h;
    }

    public double getdH()
    {
        return dH;
    }

    public double getdHdS()
    {
        return dHdS;
    }

    public double getRobotRot() { return robotRot; }

    public double getTimeStamp()
    {
        return timeStamp;
    }

    @Override
    public String toString()
    {
        return ("S: " + df.format(sVal) + "\t\tX: " + df.format(x) +
                "\t\tY: " + df.format(y) + "\t\tdX: " + df.format(dX) +
                "\t\tdY: " + df.format(dY) + "\t\tdYdX: " + df.format(dYdX) +
                "\t\tH: " + df.format(Math.toDegrees(h)) + "\t\tdH: " + df.format(dH) +
                "\t\tdHdS: " + df.format(dHdS) + "\t\tTime: " + df.format(timeStamp) +
                "\t\tRV: " + df.format(robotVel) + "\t\tRR: " + df.format(robotRot));
    }
}
