package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class CannonSystem {
    
    RobotContainer container;
    TalonSRX towerSRX = container.towerSRX;
    MotorControllerGroup cannonSRX = container.cannonSRX;
    Joystick gunnerStick = container.gunnerStick;
    JoystickButton fireCannon_Button = container.fireCannon_Butt;
    
    CannonSystem(RobotContainer Container){
        this.container = Container;
      }
    
    public void OperateCannon(){
      RotateTower(gunnerStick.getX());
      fireCannon_Button.whenHeld(FireCannon(fireCannon_Button.getAsBoolean()));
    }


    public void RotateTower(double rotationSpeed){ // Rotation Speed must be between -1.0 to 1.0
      towerSRX.set(TalonSRXControlMode.PercentOutput, rotationSpeed);
    }
    
    public Command FireCannon(boolean isFiring){ // Set true to fire the shooter cannon
      if(isFiring){
        cannonSRX.set(1.0);
        return null;
      }
      cannonSRX.set(0.0);
      return null;
    }

}
