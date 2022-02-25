package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IntakeSystem {

    RobotContainer container;
    Joystick gunnerStick = container.gunnerStick;
    JoystickButton fireButton = new JoystickButton(gunnerStick,0);
    
    IntakeSystem(RobotContainer Container){
        this.container = Container;
      }

    public void OperateIntake(){

    }





    void openIntake(){                              
      container.LeftIntakeSolenoid.set(Value.kForward);     
      container.RightIntakeSolenoid.set(Value.kForward);
      }
    void CloseIntake(){
      container.LeftIntakeSolenoid.set(Value.kReverse);
      container.RightIntakeSolenoid.set(Value.kReverse);
      }

    
}
