// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


/** Add your docs here. */
public class RobotContainer {

    WPI_TalonSRX FrontL = new WPI_TalonSRX(1);
    WPI_TalonSRX RearL = new WPI_TalonSRX(2);
    WPI_TalonSRX FrontR = new WPI_TalonSRX(3);
    WPI_TalonSRX RearR = new WPI_TalonSRX(4);
    MotorControllerGroup RightSide = new MotorControllerGroup(FrontL,RearL);
    MotorControllerGroup LeftSide = new MotorControllerGroup(FrontR, RearR);

    public DifferentialDrive driveTrain = new DifferentialDrive(LeftSide, RightSide);
    public Joystick tankStickL = new Joystick(0);
    public Joystick tankStickR = new Joystick(1);
    public DigitalInput ballSwitch = new DigitalInput(0);



    public RobotContainer(){

    }

    
}
