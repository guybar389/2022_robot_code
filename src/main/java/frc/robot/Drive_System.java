package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive_System {

    private Data_Container container;
    private PIDController turnController;
    private DifferentialDrive driveTrain;
    private JoystickButton reverseDrive_Butt;
    private Joystick tankStick_L;
    private Joystick tankStick_R;

    public Drive_System(Data_Container container) {
        this.container = container;
        turnController = container.turnController;
        driveTrain = this.container.driveTrain;
        reverseDrive_Butt = this.container.reverseDrive_Butt;
        tankStick_L = this.container.driverStickL;
        tankStick_R = this.container.driverStickR;
    }


    public void PilotDrive(boolean isOnOverride) {
        driveTrain.tankDrive(SetTankSpeed(tankStick_L),SetTankSpeed(tankStick_R));
    }



    public void Autonomus_DriveStraight(){
      driveTrain.arcadeDrive(0, 0.5);

    }
    public void Autonomous_DriveToLocation(double ballPosition_X){
      driveTrain.arcadeDrive(0.5, turnController.calculate(ballPosition_X, 0.0));  
    }

    private double SetTankSpeed(Joystick targetStick){
        boolean isReversed = reverseDrive_Butt.getAsBoolean();
        if(isReversed){
          return -targetStick.getY();
        }
        return targetStick.getY();
      }
}
