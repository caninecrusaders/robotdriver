   /*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Object;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot
{
    //public SmartDashoard(); 
    
    RobotDrive chassis;
    Talon frontLeft, frontRight, rearLeft, rearRight;
    Joystick extreme3D;
     
    public void robotInit()
    {
        frontRight = new Talon(1);
        rearRight = new Talon(2);
        frontLeft = new Talon(3);
        rearLeft = new Talon(4);
        //(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor)
        chassis = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
        extreme3D = new Joystick(1);
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        //chassis.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        chassis.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        //RobotDrive robotDrive41; 
        //RobotDrive chassis = new RobotDrive(1, 2, 3, 4);
    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() 
    {
        chassis.setSafetyEnabled(false);
        double x=accelerate();
        Timer.delay(3);
        decelerate(x);
        chassis.drive(0.0, 0.0);  
    }
    public double accelerate()
    {
        double x=-.1;
        for (;x>=-.5; x-=.001)
        {
            chassis.drive(x, 0.0);
            Timer.delay(.01);
        }
        return x;
    }
    public void decelerate(double x)
    {
         for (;x<=-.001; x+=.001)
        {
            chassis.drive(x, 0.0);
            Timer.delay(.01);
        }
    }
    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() 
    {
        chassis.setSafetyEnabled(true);
        //chassis.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        //chassis.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        while(isOperatorControl() && isEnabled())
        {   // z = rotation, m = magnitude, d = direction
            
            double x = extreme3D.getAxis(Joystick.AxisType.kX);
            double y = extreme3D.getAxis(Joystick.AxisType.kY);
            double z = extreme3D.getTwist();
            double m = extreme3D.getMagnitude(); 
            double d = extreme3D.getDirectionDegrees();
            //chassis.tankDrive(leftStick, rightStick);
            //magnitude, direction, rotation
            Timer.delay(0.01);
            chassis.mecanumDrive_Polar( m, d, z);
            //frontLeft.set(.5);
            //frontRight.set(.5);
            //rearLeft.set(.5);
            //rearRight.set(.5);
        }
    }
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() 
    {
       
    }
}