package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


public class CannonSystem {
    
    private RobotContainer container;
    private TalonSRX towerSRX = container.towerSRX;
    private MotorControllerGroup cannonSRX = container.cannonSRX;
    private XboxController gunnerStick = container.gunnerStick;
    private Button fireCannon_Button = container.fireCannon_Butt;
    
    public CannonSystem(RobotContainer container){
        this.container = container;
      }
    
    public void OperateCannon(boolean isOnOverride){
      RotateTower(gunnerStick.getRightX());
      FireCannon(GetfireCannon_isPressed());
    }





    private void RotateTower(double rotationSpeed){ // Rotation Speed must be between -1.0 to 1.0
      towerSRX.set(TalonSRXControlMode.PercentOutput, rotationSpeed);
    }
    

    private void FireCannon(boolean isFiring){
      if(isFiring)
        cannonSRX.set(container.cannonFireSpeed);
      else
        cannonSRX.set(0.0);
    }


    private boolean GetfireCannon_isPressed(){
      return fireCannon_Button.value == 1;
    }
}
