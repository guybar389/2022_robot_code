package frc.robot;


import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IntakeSystem {

    private RobotContainer container;
    private JoystickButton intakeActivate = container.activateIntake;
    private JoystickButton intakeState = container.setIntakeState;
    private TalonSRX intakeSRX = container.intakeA;


    IntakeSystem(RobotContainer container){
        this.container = container;
      }

    public void OperateIntake(){
      intakeState.whenPressed(SwitchIntakeState()); // NOT SURE IF WORKS MUST BE TESTED
      intakeActivate.whileHeld(SpinIntake(intakeActivate.getAsBoolean()));
    }


    private boolean intakeFlipflop = false; //true when intake is opened
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

    private Command SpinIntake(boolean isSpinning){    //true if we want to activate intake
      if(isSpinning){
        intakeSRX.set(TalonSRXControlMode.PercentOutput, 1.0);
        return null;
      }
      intakeSRX.set(TalonSRXControlMode.PercentOutput, 0.0);
      return null;
    }

    private void CloseIntake(){                              
      container.LeftIntakeSolenoid.set(Value.kForward);     
      container.RightIntakeSolenoid.set(Value.kForward);
      }
    private void OpenIntake(){
      container.LeftIntakeSolenoid.set(Value.kReverse);
      container.RightIntakeSolenoid.set(Value.kReverse);
      }

    
}
