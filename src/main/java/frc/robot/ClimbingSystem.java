package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimbingSystem {
    
    RobotContainer container;

    
    ClimbingSystem(RobotContainer Container){
      this.container = Container;
    }

    void OperateClimber(){

    }

      //activate cylinders through those commands
    void TwistLeftClimber(){
      container.climbSolenoid_Left.set(Value.kForward);
    }
    void StraightenLeftClimber(){
      container.climbSolenoid_Left.set(Value.kReverse);
    }
    
    void TwistRightClimber(){
      container.climbSolenoid_Right.set(Value.kForward);
    }
    void StraightenRightClimber(){
      container.climbSolenoid_Right.set(Value.kReverse);
    }
}
