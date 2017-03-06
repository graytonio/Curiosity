package org.usfirst.frc.team1523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoGear extends CommandGroup {

	/*
	 * 0 = Left no hopper
	 * 1 = Center
	 * 2 = Right no hopper
	 * 3 = Left hopper
	 * 4 = Right hopper
	 */
	
	public AutoGear(int pos) {
		driveToGear(pos);
		placeGear();
		if(pos==0 || pos==1 || pos==2) crossBaseLine(pos);
	}
	
	public void driveToGear(int pos){
		if(pos==0 || pos==3){
			addSequential(new AutoDrive(0.5, 90));
			addSequential(new AutoTurn(-55));
		}else if(pos==1){
			addSequential(new AutoDrive(0.5, 40));
		}else if(pos==2 || pos==4){
			addSequential(new AutoDrive(0.5, 90));
			addSequential(new AutoTurn(55));
		}
	}
	
	public void crossBaseLine(int pos){
		if(pos==0){
			addSequential(new AutoDrive(0.3, -30));
			addSequential(new AutoTurn(30));
			addSequential(new AutoDrive(0.3, 100));
			
		}else if(pos==2){
			addSequential(new AutoDrive(0.3, -30));
			addSequential(new AutoTurn(-30));
			addSequential(new AutoDrive(0.3, 100));
		}
	}
	
	public void placeGear(){
		addSequential(new Vision());
		addSequential(new SetUpperGear(false));
		addSequential(new WaitCommand(0.5));
		addSequential(new SetUpperGear(true));
	}
}
