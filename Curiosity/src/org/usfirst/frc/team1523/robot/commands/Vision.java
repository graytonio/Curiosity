package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Command {

	private final int DEAD_ZONE=10;
	private double THROW_DISTANCE=60;
	private boolean lastState;
	private NetworkTable server;
	private double distance;

	public Vision() {
		requires(Robot.drive);
		requires(Robot.gear);
		server = NetworkTable.getTable("SmartDashboard");
	}

	protected void execute() {
		double x = getX();												//Get center x of target
		distance = getDistance();										//Get distance to target
		if(x<=0){														//If robot lost target
			Robot.drive.stop();											//Stop moving
		}else if(x>80+DEAD_ZONE){										//If Robot is too far to the right
			Robot.drive.drive(0.4, 0, 0);								//Move to the left
			lastState=true;												//Set last state to left
		}else if(x<80-DEAD_ZONE){										//If Robot is too far to the left
			Robot.drive.drive(-0.4, 0, 0);								//Move to the right
			lastState=false;											//Set last state to right
		}else if(distance<THROW_DISTANCE) Robot.drive.drive(0, -0.2, 0);//If the distance to the target is below the threshold drive forward slowly
		else Robot.drive.stop();										//If the distance to the target is above the threshold stop and move to the next command
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
