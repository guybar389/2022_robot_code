package frc.robot;


import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IntakeSystem {

    RobotContainer container;
    Joystick gunnerStick = container.gunnerStick;
    JoystickButton intakeActivate = container.activateIntake;
    JoystickButton intakeState = container.setIntakeState;
    TalonSRX intakeSRX = container.intakeA;


    IntakeSystem(RobotContainer container){
        this.container = container;
      }

    public void OperateIntake(){
      intakeState.whenPressed(SwitchIntakeState()); // NOT SURE IF WORKS MUST BE TESTED
      intakeActivate.whileHeld(SpinIntake());
    }


    private boolean intakeFlipflop = false;
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

    private Command SpinIntake(){
      if(intakeActivate.getAsBoolean()){
        intakeSRX.set(TalonSRXControlMode.PercentOutput, 1.0);
        return null;
      }
      intakeSRX.set(TalonSRXControlMode.PercentOutput, 1.0);
      return null;
    }

    private void OpenIntake(){                              
      container.LeftIntakeSolenoid.set(Value.kForward);     
      container.RightIntakeSolenoid.set(Value.kForward);
      }
    private void CloseIntake(){
      container.LeftIntakeSolenoid.set(Value.kReverse);
      container.RightIntakeSolenoid.set(Value.kReverse);
      }

    
}
