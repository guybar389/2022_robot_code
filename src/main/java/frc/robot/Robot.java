// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.Pixy2;


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
  
    Data_Container container = new Data_Container();
    Pixy2 pixyCamera = container.pixyCamera;
    DifferentialDrive driveTrain = container.driveTrain;
    PIDController turnController = container.turnController;
    DigitalInput ballSwitch = container.ballSwitch;

    boolean MANUAL_OVERRIDE = false; // Disables all automatic assistance from the robot
                                     // And transfers full system control to the pilots.
                                     // Use in case of critical sensor's failure. 
                                     // Once true CANNOT be switched off untill the end of round.

    BallDetectorAuto ballDetector = new BallDetectorAuto(pixyCamera);
    Drive_System tankDrive = new Drive_System(container);
    Climbing_System robotClimber = new Climbing_System(container);
    Cannon_System cannonTower = new Cannon_System(container);
    Intake_System ballIntake = new Intake_System(container);
    Dashboard SmartDash = new Dashboard(container);
    
    
    double ballPosition_X;
    boolean isBallInside = false;

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

    tankDrive.AutonomusDrive(); 

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
    RobotCheckForManualOverride();
    tankDrive.PilotDrive(MANUAL_OVERRIDE);
    ballIntake.OperateIntake(MANUAL_OVERRIDE);

    if (container.GetDriverSwitchPosition_L()>0.5)  // Slider at the bottom of the Driver Joystick
      cannonTower.OperateCannon(MANUAL_OVERRIDE);   // Controlls whenever the second pilot controlls the
    else                                            // Shooter tower or the climbing system.
      robotClimber.OperateClimber(MANUAL_OVERRIDE);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {autonomousInit();}

  @Override

  public void testPeriodic() {}


  public void RobotCheckForManualOverride(){
    SmartDash.DisplayOverride(MANUAL_OVERRIDE);
    if (container.CheckForManualOverrideInput())
      MANUAL_OVERRIDE = true;
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
