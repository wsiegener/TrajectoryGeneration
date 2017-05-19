package com.wsiggs.trajectories;

/**
 * @author Siggy
 *         $
 */
public class Tester
{
    static double[] rotVals;
    static double[] velVals;

    public static void main(String[] args)
    {
        rotVals = new double[] // n entries, n
                {
                        0.0,
                        40.0,
                        80.0,
                        120.0,
                        160.0,
                        200.0,
                        240.0,
                        280.0,
                        320.0,
                        360.0,
                        400.0
                };

        velVals = new double[]
                {
                        0.0,
                        1.0,
                        2.0,
                        3.0,
                        4.0,
                        5.0,
                        6.0,
                        7.0,
                        8.0,
                        9.0,
                        10.0
                };

        ReferencePoint r0 = new ReferencePoint(0.0,0.0);
        ReferencePoint r1 = new ReferencePoint(2.0,0.0);
        ReferencePoint r2 = new ReferencePoint(4.0,0.0);
        ReferencePoint r3 = new ReferencePoint(4.0,2.0);

        Trajectory x = new Trajectory(rotVals, velVals, 4.0, r0, r1, r2, r3);

        x.spew();
    }
}
