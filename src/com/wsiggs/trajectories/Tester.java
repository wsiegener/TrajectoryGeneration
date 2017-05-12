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
                        10.0,
                        20.0,
                        30.0,
                        40.0,
                        50.0,
                        60.0,
                        70.0,
                        80.0,
                        90.0,
                        100.0
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
        ReferencePoint r1 = new ReferencePoint(0.5,0.5);
        ReferencePoint r2 = new ReferencePoint(2.5,0.5);
        ReferencePoint r3 = new ReferencePoint(3.0,1.0);

        Trajectory x = new Trajectory(rotVals, velVals, 4.0, r0, r1, r2, r3);

        x.spew();
    }
}
