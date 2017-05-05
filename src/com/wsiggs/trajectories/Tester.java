package com.wsiggs.trajectories;

/**
 * @author Siggy
 *         $
 */
public class Tester
{
    public static void main(String[] args)
    {
        ReferencePoint r0 = new ReferencePoint(0.0,5.0);
        ReferencePoint r1 = new ReferencePoint(6.0,9.0);
        ReferencePoint r2 = new ReferencePoint(9.0,10.0);
        ReferencePoint r3 = new ReferencePoint(4.0,5.0);

        Trajectory x = new Trajectory(5.0, r0, r1, r2, r3);

        x.spew();
    }
}
