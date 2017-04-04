package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Command {

	private final int DEAD_ZONE=2;
	private double THROW_DISTANCE=60;
	private double distance;

	public Vision() {
		requires(Robot.drive);
		requires(Robot.gear);
	}

	protected void execute() {											//Get distance to target
		if(Robot.x>80+DEAD_ZONE){										//If Robot is too far to the right
			Robot.drive.drive(0.35, 0, 0);								//Move to the left
		}else if(Robot.x<80-DEAD_ZONE){									//If Robot is too far to the left
			Robot.drive.drive(-0.35, 0, 0);								//Move to the right
		}else if(Robot.y1<Robot.y2+DEAD_ZONE){
			Robot.drive.drive(0, 0, 0.35);
		}else if(Robot.y2<Robot.y1+DEAD_ZONE){
			Robot.drive.drive(0, 0, -0.35);
		}else if(Robot.distance<THROW_DISTANCE){
			Robot.drive.drive(0, -0.2, 0);//If the distance to the target is below the threshold drive forward slowly
		}else{
			Robot.drive.stop();										//If the distance to the target is above the threshold stop and move to the next command
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return distance>THROW_DISTANCE;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.stop();
	}
	
	private double getX(){
		return Robot.x;
	}
	
	private double getDistance(){
		return Robot.distance;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
