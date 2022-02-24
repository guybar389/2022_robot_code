package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class IntakeSystem {

    RobotContainer container;

    
    IntakeSystem(RobotContainer Container){
        this.container = Container;
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
