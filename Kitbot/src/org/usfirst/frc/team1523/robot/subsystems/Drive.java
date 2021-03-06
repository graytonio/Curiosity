package org.usfirst.frc.team1523.robot.subsystems;

import org.usfirst.frc.team1523.robot.RobotMap;
import org.usfirst.frc.team1523.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	TalonSRX rightDrive;
	TalonSRX leftDrive;
	RobotDrive drive;
	
	public Drive(){
		rightDrive = new TalonSRX(RobotMap.RIGHT_DRIVE_TALON_ID);
		leftDrive = new TalonSRX(RobotMap.LEFT_DRIVE_TALON_ID);
		drive = new RobotDrive(leftDrive, rightDrive);
	}
	
	public void driveJoystick(Joystick stick){
		drive.tankDrive(stick.getRawAxis(1), stick.getRawAxis(3));
	}
	
	public void drive(double left, double right){
		drive.tankDrive(left, right);
	}
	
	public void stop(){
		drive.stopMotor();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
}

