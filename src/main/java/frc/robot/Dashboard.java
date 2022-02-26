package frc.robot;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Dashboard {
    

    private RobotContainer container;

    public Dashboard(RobotContainer container) {
        this.container = container;
    }

    public void DisplayOverride(boolean state){
        SmartDashboard.putString("Manual Override:", state?"OFFLINE":"ACTIVE");
    }

    
    
}
