package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSystem {

    RobotContainer container = new RobotContainer();
    DifferentialDrive driveTrain = container.driveTrain;
    JoystickButton reverseDriveGear = container.reverseDriveGear;
    Joystick tankStick_L;
    Joystick tankStick_R;

    public DriveSystem(Joystick tankStick_L, Joystick tankStick_R) {
        this.tankStick_L = tankStick_L;
        this.tankStick_R = tankStick_R;
    }

    public void PilotDrive() {
        driveTrain.tankDrive(SetTankSpeed(tankStick_L),SetTankSpeed(tankStick_R));
    }

    public void AutonomusDrive() {
        
    }


    private double SetTankSpeed(Joystick targetStick){
        boolean isReversed = reverseDriveGear.getAsBoolean();
        if(isReversed){
          return -targetStick.getY();
        }
        return targetStick.getY();
      }
}
