package com.wsiggs.trajectories;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.*;

public class RobotTester extends IterativeRobot
{
    ReferencePoint r0, r1, r2, r3;
    Trajectory path;
    Util u;

    int index = 0;

    Joystick driverStick;

    CANTalon frontRight;
    CANTalon rearRight;
    CANTalon frontLeft;
    CANTalon rearLeft;

    RobotDrive rd;

    public RobotTester()
    {
        r0 = new ReferencePoint(0.0, 0.0);
        r1 = new ReferencePoint(0.0, 2.0);
        r2 = new ReferencePoint(3.0, 6.0);
        r3 = new ReferencePoint(6.0, 6.0);

        path = new Trajectory(5.0, r0, r1, r2, r3);

        driverStick = new Joystick(0);

        frontRight = new CANTalon(1);
        rearRight = new CANTalon(2);
        frontLeft = new CANTalon(3);
        rearLeft = new CANTalon(4);

        rd = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

        frontRight.changeControlMode(CANTalon.TalonControlMode.Speed);
        frontLeft.changeControlMode(CANTalon.TalonControlMode.Speed);
        rearRight.changeControlMode(CANTalon.TalonControlMode.Follower);
        rearLeft.changeControlMode(CANTalon.TalonControlMode.Follower);

    }

    public void robotInit()
    {

    }

    @Override
    public void teleopPeriodic()
    {
        Util.DriveState d;
        if(driverStick.getRawButton(1) && index < path.getSetpoints().size())
        {
            driveMotors(u.calcKinematics(path.getSetpoints().get(index).getdYdX(), path.getSetpoints().get(index).getdHdS()));


            index++;
        }
        else
        {
            index = 0;
        }
    }


    public void driveMotors(Util.DriveState d)
    {

    }
}
