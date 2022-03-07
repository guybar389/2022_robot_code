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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;


/** Add your docs here. */
public class Data_Container {

    /////////////////// USER INPUT ////////////////////////////////
    
    public Joystick driverStickL = new Joystick(0);
    public Joystick driverStickR = new Joystick(1);
    public JoystickButton initiateOverrideDriver_Butt = new JoystickButton(driverStickL, 10);
    public JoystickButton reverseDrive_Butt = new JoystickButton(driverStickL, 0);

    public XboxController gunnerStick = new XboxController(2);
    public Button initiateOverrideGunner_Butt = Button.kStart;
    public Button activateIntk_Butt = Button.kB;
    public Button intakeState_Butt = Button.kBack;
    public Button fireCannon_Butt = Button.kA;

    //////////////////// TANK DRIVE ///////////////////////////////

    private WPI_TalonSRX frontL = new WPI_TalonSRX(1);  //make sure port 1&2 are for left side
    private WPI_TalonSRX rearL = new WPI_TalonSRX(2);
    private WPI_TalonSRX frontR = new WPI_TalonSRX(3);  //make sure port 3&4 are for right side
    private WPI_TalonSRX rearR = new WPI_TalonSRX(4);
    private MotorControllerGroup rightTrack = new MotorControllerGroup(frontL,rearL);
    private MotorControllerGroup leftTrack = new MotorControllerGroup(frontR, rearR);

    public PIDController turnController = new PIDController(0.1, 0, 0);
    public DifferentialDrive driveTrain = new DifferentialDrive(leftTrack, rightTrack);

    //////////////////// PNEUMATICS System ///////////////////////////////

    public PneumaticsControlModule pcm = new PneumaticsControlModule();
    
    //////////////////// INTAKE System ///////////////////////////////

    public WPI_TalonSRX intakeA = new WPI_TalonSRX(5);
    public DoubleSolenoid intakeSolenoid_Left = pcm.makeDoubleSolenoid(0, 1);
    public DoubleSolenoid intakeSolenoid_Right = pcm.makeDoubleSolenoid(2, 3);
    public double intakeRotationSpeed = 1.0;
    public DigitalInput ballSwitch = new DigitalInput(0);

    

    //////////////////// BALLGRIP System ///////////////////////////////

    public WPI_TalonSRX Gripper = new WPI_TalonSRX(6);


    //////////////////// CLIMB System ///////////////////////////////

    public WPI_TalonSRX climbSRX_Left = new WPI_TalonSRX(7);   
    public WPI_TalonSRX climbSRX_Right = new WPI_TalonSRX(8);

    public DoubleSolenoid climbSolenoid_Left = pcm.makeDoubleSolenoid(4, 5);
    public DoubleSolenoid climbSolenoid_Right = pcm.makeDoubleSolenoid(6, 7);

    //////////////////// CANNON System ///////////////////////////////

    //no need to declare these three shooter motors seperately 
    //*CONTAINS BOTH DECIMATE GEARBOX AND 775 MOTOR, PAY ATTENTION IF IT NEEDED TO BE INVERTED*
    public MotorControllerGroup cannonSRX = new MotorControllerGroup
    (new WPI_TalonSRX(9), new WPI_TalonSRX(10), new WPI_TalonSRX(11)); 
    public WPI_TalonSRX towerSRX = new WPI_TalonSRX(11);
    public double cannonFireSpeed = 1.0;

    //////////////////// IMANGE RECOGNITION System ///////////////////////////////

    public Pixy2 pixyCamera = Pixy2.createInstance(new SPILink());


    public Data_Container(){
        rightTrack.setInverted(true);
        
    }
    

    public double GetDriverSwitchPosition_L(){         //needs to be changed regarding to xbox controller for gunner
        return driverStickL.getThrottle();
    }

    
    public boolean CheckForManualOverrideInput(){
        return initiateOverrideDriver_Butt.getAsBoolean() && 
        initiateOverrideGunner_Butt.value == 1;
    }
}

/**
חיישנים וכו':
מצלמה צריח
חיישן שיגלה איסוף כדור- צריך למצוא חיישן מתאים
אנקודר למנוע שוטר
תוכנת בטיחות - שימוש בNavX בתור תוכנת בטיחות אם רובוט הולך ליפול מהמוט./ 
*/