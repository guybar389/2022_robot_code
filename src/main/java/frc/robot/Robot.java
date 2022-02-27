// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
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
  
    Data_Container container;
    boolean MANUAL_OVERRIDE = true; // Disables all automatic assistance from the robot
                                     // And transfers full system control to the pilots.
                                     // Use in case of critical sensor's failure. 
                                     // Once true CANNOT be switched off untill the end of round.
    BallDetectorAuto ballDetector;
    Drive_System tankDrive;
    Climbing_System robotClimber;
    Cannon_System cannonTower;
    Intake_System ballIntake;
    Dashboard SmartDash;
    Pixy2 pixyCamera;
    
    double ballPosition_X;


  @Override
  public void robotInit() {
    container = new Data_Container();

    pixyCamera = container.pixyCamera;
    

    ballDetector = new BallDetectorAuto(pixyCamera);
    tankDrive = new Drive_System(container);
    robotClimber = new Climbing_System(container);
    cannonTower = new Cannon_System(container);
    ballIntake = new Intake_System(container);
    SmartDash = new Dashboard(container);

    SetBallDetectorAlliance();

  }

  

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {

    ballDetector.execute();
    
    tankDrive.Autonomus_DriveStraight();

    ballPosition_X = SmartDashboard.getNumber("Ball X", 0.0);
    if (!ballIntake.CheckIfHasBall()){
      if (ballPosition_X != 0.0){
        tankDrive.Autonomous_DriveToLocation(ballPosition_X);
      }
      else
      tankDrive.Autonomus_DriveStraight();
     }

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
