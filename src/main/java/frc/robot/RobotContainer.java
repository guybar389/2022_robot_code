// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;


/** Add your docs here. */
public class RobotContainer {

    //////////////////// TANK DRIVE ///////////////////////////////

    WPI_TalonSRX FrontL = new WPI_TalonSRX(1);
    WPI_TalonSRX RearL = new WPI_TalonSRX(2);
    WPI_TalonSRX FrontR = new WPI_TalonSRX(3);
    WPI_TalonSRX RearR = new WPI_TalonSRX(4);
    MotorControllerGroup RightSide = new MotorControllerGroup(FrontL,RearL);
    MotorControllerGroup LeftSide = new MotorControllerGroup(FrontR, RearR);
    public DifferentialDrive driveTrain = new DifferentialDrive(LeftSide, RightSide);
    public Joystick tankStickL = new Joystick(0);
    public Joystick tankStickR = new Joystick(1);
    public JoystickButton reverseDriveGear = new JoystickButton(tankStickL, 0);

    //////////////////// INTAKE System ///////////////////////////////

    WPI_TalonSRX intakeA = new WPI_TalonSRX(5);
    WPI_TalonSRX intakeB = new WPI_TalonSRX(6);

    //////////////////// CLIMB System ///////////////////////////////

    WPI_TalonSRX climbA = new WPI_TalonSRX(7);
    WPI_TalonSRX climbB = new WPI_TalonSRX(8);
    WPI_TalonSRX climbTweakA = new WPI_TalonSRX(9);
    WPI_TalonSRX climbTweakB = new WPI_TalonSRX(10);

    //////////////////// CANNON System ///////////////////////////////

    WPI_TalonSRX cannonShooter = new WPI_TalonSRX(11);
    WPI_TalonSRX towerRotator = new WPI_TalonSRX(12);



    //////////////////// SENSORS INIT ///////////////////////////////

    public DigitalInput ballSwitch = new DigitalInput(0);
    public Pixy2 pixyCamera = Pixy2.createInstance(new SPILink());
    PIDController turnController = new PIDController(0.1, 0, 0);


    public RobotContainer(){
        RightSide.setInverted(true);
    }

    
}

/**
חיישנים וכו':
מצלמה צריח
חיישן שיגלה איסוף כדור- צריך למצוא חיישן מתאים
אנקודר למנוע שוטר
תוכנת בטיחות - שימוש בNavX בתור תוכנת בטיחות אם רובוט הולך ליפול מהמוט./ 
*/