// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/** Add your docs here. */
public class RobotContainer {

    WPI_TalonSRX LF = new WPI_TalonSRX(1);
    WPI_TalonSRX LR = new WPI_TalonSRX(2);
    WPI_TalonSRX RF = new WPI_TalonSRX(3);
    WPI_TalonSRX RR = new WPI_TalonSRX(4);
    public MecanumDrive driveTrain = new MecanumDrive(LF, LR, RF, RR);
    public Joystick driveStick = new Joystick(0);
    public DigitalInput ballSwitch = new DigitalInput(0);



    public RobotContainer(){

    }

    
}
