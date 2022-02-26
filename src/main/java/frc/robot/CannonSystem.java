package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class CannonSystem {
    
    private RobotContainer container;
    private TalonSRX towerSRX = container.towerSRX;
    private MotorControllerGroup cannonSRX = container.cannonSRX;
    private Joystick gunnerStick = container.gunnerStick;
    private JoystickButton fireCannon_Button = container.fireCannon_Butt;
    
    public CannonSystem(RobotContainer container){
        this.container = container;
      }
    
    public void OperateCannon(boolean isOnOverride){
      RotateTower(gunnerStick.getX());
      fireCannon_Button.whenHeld(FireCannon(fireCannon_Button.getAsBoolean()));
    }





    private void RotateTower(double rotationSpeed){ // Rotation Speed must be between -1.0 to 1.0
      towerSRX.set(TalonSRXControlMode.PercentOutput, rotationSpeed);
    }
    
    private Command FireCannon(boolean isFiring){ // Set true to fire the shooter cannon
      if(isFiring){
        cannonSRX.set(1.0);
        return null;
      }
      cannonSRX.set(0.0);
      return null;
    }

}
