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
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  
    RobotContainer cont = new RobotContainer();
    MecanumDrive driveTrain = cont.driveTrain;
    Joystick driveStick = cont.driveStick;
    Pixy2 pixy = Pixy2.createInstance(new SPILink());
    autoGrabBall auto = new autoGrabBall(pixy);
    PIDController turnController = new PIDController(0.1, 0, 0);
    double ballx;
    DigitalInput ballSwitch = cont.ballSwitch;
    boolean ballInside = false;
    
  @Override
  public void robotInit() {
    if (DriverStation.getAlliance() == Alliance.Blue){
      auto.setBlueAlliance();
    }
    else if (DriverStation.getAlliance() == Alliance.Red){
      auto.setRedAlliance();
    }

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

    //driveTrain.driveCartesian(0, 0, 0.5);
    auto.execute();
    ballx = SmartDashboard.getNumber("Ball X", 0.0);


    if (!ballInside){
      if (ballx != 0.0){
         driveTrain.driveCartesian(0.5, 0, turnController.calculate(ballx, 0.0));
      
      }
      else
      driveTrain.driveCartesian(0, 0, 0.5);
    }
    else if(ballSwitch.get())
      ballInside = true;
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    driveTrain.driveCartesian(driveStick.getY(), driveStick.getX(), driveStick.getZ());

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
