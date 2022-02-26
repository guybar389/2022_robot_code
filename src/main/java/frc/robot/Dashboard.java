package frc.robot;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Dashboard {
    

   // private RobotContainer container;

    public Dashboard(Data_Container container) {
   //     this.container = container;
    }

    public void DisplayOverride(boolean state){
       SmartDashboard.putString("MANUAL OVERRIDE:", state?"OFFLINE":"ACTIVE");
    }

    
    
}
