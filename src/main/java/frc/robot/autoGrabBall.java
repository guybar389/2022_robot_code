// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

/** Add your docs here. */
public class autoGrabBall {

    private int alliance = 3;
    Pixy2 Camera;
    private static final int blockSignature = 1;
	ShuffleboardTab ballTab = Shuffleboard.getTab("autonomous ball place");
	//private NetworkTableEntry ballXentry, ballYentry, allianceEntry;
	

    public autoGrabBall(Pixy2 pixy){

        Camera = pixy;
        Camera.init();

			//currently noted untill I'll figure it out how to use shuffleboard in this situation
		// ballXentry = ballTab.add("Ball X",1.0).getEntry();  
		// ballYentry = ballTab.add("Ball Y",2.001).getEntry();
		// allianceEntry = ballTab.add("Alliance (1-red, 2-blue)", alliance).getEntry();

		SmartDashboard.putNumber("Ball X", 3.0);
		SmartDashboard.putNumber("Ball Y", 0.0);
		SmartDashboard.putNumber("Alliance (1-red, 2-blue)", alliance);
    }

    public void setRedAlliance(){

        alliance = Pixy2CCC.CCC_SIG1; //calibrate SIG1 to red ball

    }

    public void setBlueAlliance(){

        alliance = Pixy2CCC.CCC_SIG2; //calibrate SIG2 to blue ball

    }

    protected void execute() {
		int blockCount = Camera.getCCC().getBlocks(false, alliance, 25);
		
		SmartDashboard.putNumber("block Count" , 2);

		if (blockCount <= 0) {
			System.err.println("No block count");
			return;
		}
		ArrayList<Block> blocks = Camera.getCCC().getBlockCache();
		Block largestBlock = null;
		if (blocks == null) {
			System.err.println("No Blocks");
			return;
		}
		for (Block block : blocks) {
			if (block.getSignature() == blockSignature) {
				if (largestBlock == null) {
					largestBlock = block;
				} else if (block.getWidth() > largestBlock.getWidth()) {
					largestBlock = block;
				}
			}
		}

		SmartDashboard.putNumber("Ball X", largestBlock.getX());
		SmartDashboard.putNumber("Ball Y", largestBlock.getY());
		SmartDashboard.putNumber("Alliance (1-red, 2-blue)", alliance);
		
		

			//currently noted untill I'll figure it out how to use shuffleboard in this situation
		// ballXentry.setDouble(largestBlock.getX());
		// ballYentry.setDouble(largestBlock.getY());
		// allianceEntry.setDouble(alliance);
		

	}

}
