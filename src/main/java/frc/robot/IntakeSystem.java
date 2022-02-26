package frc.robot;


import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IntakeSystem {

    private RobotContainer container;
    private JoystickButton intakeActivate_Butt = container.activateIntk_Butt;
    private JoystickButton intakeState_Butt = container.intakeState_Butt;
    private TalonSRX intakeSRX = container.intakeA;
    private boolean intakeFlipflop = false; // Private State Flipflop container variable

    public IntakeSystem(RobotContainer container){
        this.container = container;
    }

    public void OperateIntake(boolean isOnOverride){ // NOT SURE IF WORKS, EVENT SYSTEM MUST BE TESTED
      intakeState_Butt.whenPressed(SwitchIntakeState()); 
      intakeActivate_Butt.whileHeld(SpinIntake(intakeActivate_Butt.getAsBoolean()));
    }





    private Command SwitchIntakeState(){
      if(!intakeFlipflop){
        OpenIntake();
        intakeFlipflop = true;
        return null;
      }
      CloseIntake();
      intakeFlipflop = false;
      return null;
    }

    private Command SpinIntake(boolean isSpinning){    // Set true to activate
      if(isSpinning){
        intakeSRX.set(TalonSRXControlMode.PercentOutput, 1.0);
        return null;
      }
      intakeSRX.set(TalonSRXControlMode.PercentOutput, 0.0);
      return null;
    }

    private void CloseIntake(){                              
      container.intakeSolenoid_Left.set(Value.kForward);     
      container.intakeSolenoid_Right.set(Value.kForward);
      }
    private void OpenIntake(){
      container.intakeSolenoid_Left.set(Value.kReverse);
      container.intakeSolenoid_Right.set(Value.kReverse);
      }

    
}
