package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSystem {

    RobotContainer container;
    DifferentialDrive driveTrain = container.driveTrain;
    JoystickButton reverseDrive_Butt = container.reverseDrive_Butt;
    Joystick tankStick_L = container.driverStickL;
    Joystick tankStick_R = container.driverStickR;

    public DriveSystem(RobotContainer Container) {
        this.container = Container;
    }

    public void PilotDrive() {
        driveTrain.tankDrive(SetTankSpeed(tankStick_L),SetTankSpeed(tankStick_R));
    }

    public void AutonomusDrive() {
      driveTrain.arcadeDrive(0, 0.5);

    }


    private double SetTankSpeed(Joystick targetStick){
        boolean isReversed = reverseDrive_Butt.getAsBoolean();
        if(isReversed){
          return -targetStick.getY();
        }
        return targetStick.getY();
      }
}
