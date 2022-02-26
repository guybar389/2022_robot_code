package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSystem {

    private RobotContainer container;
    private DifferentialDrive driveTrain = container.driveTrain;
    private JoystickButton reverseDrive_Butt = container.reverseDrive_Butt;
    private Joystick tankStick_L = container.driverStickL;
    private Joystick tankStick_R = container.driverStickR;

    public DriveSystem(RobotContainer Container) {
        this.container = Container;
    }

    public void PilotDrive(boolean isOnOverride) {
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
