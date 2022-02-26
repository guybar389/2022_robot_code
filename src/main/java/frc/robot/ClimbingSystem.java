package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimbingSystem {
    
    private RobotContainer container;

    Joystick driveStick_L = container.driverStickL;
    Joystick driveStick_R = container.driverStickR;

    public ClimbingSystem(RobotContainer Container){
      this.container = Container;
    }

    public void OperateClimber(boolean isOnOverride){
      ControlClimbMechanism_Left();
      ControlClimbMechanism_Right();
    }





    private void ControlClimbMechanism_Left(){
      if (driveStick_L.getY() > 0.75)
        TwistLeftClimber();
      else if (driveStick_L.getY() < -0.75)
        StraightenLeftClimber();
    }

    private void ControlClimbMechanism_Right(){
      if (driveStick_R.getY() > 0.75)
        TwistRightClimber();
      else if (driveStick_R.getY() < 0.75)
        StraightenRightClimber();
    }

      //activate cylinders through those commands
    private void TwistLeftClimber(){
      container.climbSolenoid_Left.set(Value.kForward);
    }
    private void StraightenLeftClimber(){
      container.climbSolenoid_Left.set(Value.kReverse);
    }
    
    private void TwistRightClimber(){
      container.climbSolenoid_Right.set(Value.kForward);
    }
    private void StraightenRightClimber(){
      container.climbSolenoid_Right.set(Value.kReverse);
    }
}
