// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  
    RobotContainer container = new RobotContainer();
    DifferentialDrive driveTrain = container.driveTrain;
    Joystick tankStick_L = container.tankStickL;
    Joystick tankStick_R = container.tankStickR;
    Pixy2 pixyCamera = Pixy2.createInstance(new SPILink());
    BallDetectorAuto ballDetector = new BallDetectorAuto(pixyCamera);
    PIDController turnController = new PIDController(0.1, 0, 0);
    DigitalInput ballSwitch = container.ballSwitch;
    JoystickButton reverseDriveGear = container.reverseDriveGear;
    
    boolean isBallInside = false;
    double ballPosition_X;


  @Override
  public void robotInit() {
    SetBallDetectorAlliance();
  }

  

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void autonomousInit() {
    robotInit();
  }

  @Override
  public void autonomousPeriodic() {

    driveTrain.arcadeDrive(0, 0.5);
    ballDetector.execute();
    ballPosition_X = SmartDashboard.getNumber("Ball X", 0.0);


    if (!isBallInside){

      if (ballPosition_X != 0.0){
         driveTrain.arcadeDrive(0.5, turnController.calculate(ballPosition_X, 0.0));       
      }
      else
      driveTrain.arcadeDrive(0.5, 0.0);;
    }
    else if(ballSwitch.get())
      isBallInside = true;
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    driveTrain.tankDrive(tankSpeedL(),tankSpeedR());

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {autonomousInit();}

  @Override

  public void testPeriodic() {}



  public double tankSpeedL(){
    boolean isReversed = reverseDriveGear.getAsBoolean();
    if(isReversed){
      return -tankStick_L.getY();
    }
    return tankStick_L.getY();
  }

  public double tankSpeedR(){
    boolean isReversed = reverseDriveGear.getAsBoolean();
    if(isReversed){
      return -tankStick_R.getY();
    }
    return tankStick_R.getY();
  }

  public void SetBallDetectorAlliance(){
		if (DriverStation.getAlliance() == Alliance.Blue){
		  ballDetector.setBlueAlliance();
		}
		else if (DriverStation.getAlliance() == Alliance.Red){
		  ballDetector.setRedAlliance();
		}
	}

}
