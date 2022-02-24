// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;


/** Add your docs here. */
public class RobotContainer {

    /////////////////// USER INPUT ////////////////////////////////
    
    Joystick driverStickL = new Joystick(0);
    Joystick driverStickR = new Joystick(1);
    JoystickButton reverseDriveGear = new JoystickButton(driverStickL, 0);
    Joystick gunnerStick = new Joystick(2);
    JoystickButton CannonFire = new JoystickButton(gunnerStick, 0);
    JoystickButton IntakeSet = new JoystickButton(gunnerStick, 1);

    //////////////////// TANK DRIVE ///////////////////////////////

    WPI_TalonSRX FrontL = new WPI_TalonSRX(1);  //make sure port 1&2 are for left side
    WPI_TalonSRX RearL = new WPI_TalonSRX(2);
    WPI_TalonSRX FrontR = new WPI_TalonSRX(3);  //make sure port 3&4 are for right side
    WPI_TalonSRX RearR = new WPI_TalonSRX(4);
    MotorControllerGroup RightSide = new MotorControllerGroup(FrontL,RearL);
    MotorControllerGroup LeftSide = new MotorControllerGroup(FrontR, RearR);
    DifferentialDrive driveTrain = new DifferentialDrive(LeftSide, RightSide);

    //////////////////// PNEUMATICS System ///////////////////////////////

    PneumaticsControlModule pcm = new PneumaticsControlModule();
    
    //////////////////// INTAKE System ///////////////////////////////

    WPI_TalonSRX intakeA = new WPI_TalonSRX(5);
    DoubleSolenoid LeftIntakeSolenoid = pcm.makeDoubleSolenoid(0, 1);
    DoubleSolenoid RightIntakeSolenoid = pcm.makeDoubleSolenoid(2, 3);

    


    //////////////////// BALLGRIP System ///////////////////////////////

    WPI_TalonSRX Gripper = new WPI_TalonSRX(6);


    //////////////////// CLIMB System ///////////////////////////////

    WPI_TalonSRX LeftClimb = new WPI_TalonSRX(7);   
    WPI_TalonSRX RightClimb = new WPI_TalonSRX(8);

    DoubleSolenoid LeftClimbSolenoid = pcm.makeDoubleSolenoid(4, 5);
    DoubleSolenoid RightClimbSolenoid = pcm.makeDoubleSolenoid(6, 7);

    //////////////////// CANNON System ///////////////////////////////

    //no need to declare these three shooter motors seperately 
    //*CONTAINS BOTH DECIMATE GEARBOX AND 775 MOTOR, PAY ATTENTION IF IT NEEDED TO BE INVERTED*
    MotorControllerGroup CannonShooter = new MotorControllerGroup
    (new WPI_TalonSRX(9), new WPI_TalonSRX(10), new WPI_TalonSRX(11)); 

    WPI_TalonSRX CannonSiding = new WPI_TalonSRX(11);
    

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