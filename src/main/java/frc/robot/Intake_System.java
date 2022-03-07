package frc.robot;


import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController.Button;


public class Intake_System {

    private Data_Container container;
    private Button intakeActivate_Butt;
    private Button intakeState_Butt;
    private TalonSRX intakeSRX;
    private DigitalInput ballCheckSwitch;
    

    public Intake_System(Data_Container container){
        this.container = container;
        intakeActivate_Butt = container.activateIntk_Butt;
        intakeState_Butt = container.intakeState_Butt;
        intakeSRX = container.intakeA;
        ballCheckSwitch = container.ballSwitch;

    }

    
    public void OperateIntake(boolean isOnOverride){
      if(isOnOverride){
        DoOnce_CheckButton_SwitchIntakeState();
        SpinIntake(GetActivateButton_isPressed());
      }
      else{
        
      }
    }


    private boolean intakeStateButton_WasPressedOnce = false;
    private void DoOnce_CheckButton_SwitchIntakeState(){
      if (GetStateButton_isPressed() && !intakeStateButton_WasPressedOnce){
        intakeStateButton_WasPressedOnce = true;
        SwitchIntakeState();
      }
      else if (!GetStateButton_isPressed())
      {
        intakeStateButton_WasPressedOnce = false;
      }
    }


    public boolean CheckIfHasBall(){
      return ballCheckSwitch.get();
    }



    private boolean intakeState_Flipflop = false;
    private void SwitchIntakeState(){
      if(!intakeState_Flipflop){
        OpenIntake();
        intakeState_Flipflop = true;
      }
      CloseIntake();
      intakeState_Flipflop = false;
    }


    

    private void SpinIntake(boolean isSpinning){
      if(isSpinning)
        intakeSRX.set(TalonSRXControlMode.PercentOutput, container.intakeRotationSpeed);
      else
        intakeSRX.set(TalonSRXControlMode.PercentOutput, 0.0);
    }


    private void CloseIntake(){                              
      container.intakeSolenoid_Left.set(Value.kForward);     
      container.intakeSolenoid_Right.set(Value.kForward);
    }


    private void OpenIntake(){
      container.intakeSolenoid_Left.set(Value.kReverse);
      container.intakeSolenoid_Right.set(Value.kReverse);
    }


    private boolean GetStateButton_isPressed(){
      return intakeState_Butt.value == 1;
    }

    private boolean GetActivateButton_isPressed(){
      return intakeActivate_Butt.value == 1;
    }

    
}
