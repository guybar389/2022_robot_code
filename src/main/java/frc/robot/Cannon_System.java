package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


public class Cannon_System {
    
    private Data_Container container;
    private TalonSRX towerSRX;
    private MotorControllerGroup cannonSRX;
    private XboxController gunnerStick;
    private Button fireCannon_Button;
    
    public Cannon_System(Data_Container container){
        this.container = container;

        towerSRX = container.towerSRX;
        cannonSRX = container.cannonSRX;
        gunnerStick = container.gunnerStick;
        fireCannon_Button = container.fireCannon_Butt;
      }
    
    public void OperateCannon(boolean isOnOverride){
      if(isOnOverride){
        RotateTower(gunnerStick.getRightX());
        FireCannon(GetfireCannon_isPressed());
      }
      else{
        
      }
      
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
