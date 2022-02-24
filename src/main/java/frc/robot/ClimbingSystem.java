package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimbingSystem {
    
    RobotContainer container;

    
    ClimbingSystem(RobotContainer Container){
        this.container = Container;
      }

      //activate cylinders through those commands
    void TwistLeftClimber(){container.LeftClimbSolenoid.set(Value.kForward);}
    void StraightenLeftClimber(){container.LeftClimbSolenoid.set(Value.kReverse);}
    
    void TwistRightClimber(){container.RightClimbSolenoid.set(Value.kForward);}
    void StraightenRightClimber(){container.RightClimbSolenoid.set(Value.kReverse);}
}
