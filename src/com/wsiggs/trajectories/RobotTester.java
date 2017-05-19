package com.wsiggs.trajectories;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.*;

public class RobotTester extends IterativeRobot
{
    ReferencePoint r0, r1, r2, r3;
    Trajectory path;

    int index = 0;

    final double[] rotationValues =
            {

            };

    final double[] velocityValues =
            {

            };


    Joystick driverStick;

    SpeedController frontRight;
    SpeedController rearRight;
    SpeedController frontLeft;
    SpeedController rearLeft;

    RobotDrive rd;

    public RobotTester()
    {
        r0 = new ReferencePoint(0.0, 0.0);
        r1 = new ReferencePoint(0.0, 2.0);
        r2 = new ReferencePoint(3.0, 6.0);
        r3 = new ReferencePoint(6.0, 6.0);

        path = new Trajectory(rotationValues, velocityValues, 5.0, r0, r1, r2, r3);

        driverStick = new Joystick(0);

        frontRight = new CANTalon(1);
        rearRight = new CANTalon(2);
        frontLeft = new CANTalon(3);
        rearLeft = new CANTalon(4);

        rd = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void robotInit()
    {

    }

    @Override
    public void teleopPeriodic()
    {
        if(driverStick.getRawButton(1) && index < path.getSetpoints().size())
        {
            rd.arcadeDrive(path.getSetpoints().get(index).getRobotVel(), path.getSetpoints().get(index).getRobotRot());
            index++;
        }
        else
        {
            rd.arcadeDrive(driverStick.getRawAxis(0), driverStick.getRawAxis(2));
            index = 0;
        }

    }
}
