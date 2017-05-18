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
		if(pos==0 || pos==3){
			addSequential(new AutoDrive(0.2, 55));
			addSequential(new AutoTurn(-45));
			addSequential(new AutoDrive(0.2, 2));
			//Center
		}else if(pos==1){
			addSequential(new AutoDrive(0.2, 45));
			//Right
		}else if(pos==2 || pos==4){
			addSequential(new AutoDrive(0.2, 55));
			addSequential(new AutoTurn(45));
			addSequential(new AutoDrive(0.2, 2));
		}
		//Find Target
		//		addSequential(new Vision());
		//Put Gear on Peg
		if(pos==0 || pos==1 || pos==2){
			addSequential(new SetUpperGear(false));
			addSequential(new WaitCommand(0.5));
			addSequential(new SetUpperGear(true));
		}
	}
}
