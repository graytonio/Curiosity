
package org.usfirst.frc.team1523.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1523.robot.commands.AutoGear;
import org.usfirst.frc.team1523.robot.commands.DriveAcrossBaseLine;
import org.usfirst.frc.team1523.robot.subsystems.Ball;
import org.usfirst.frc.team1523.robot.subsystems.Drive;
import org.usfirst.frc.team1523.robot.subsystems.Gear;
import org.usfirst.frc.team1523.robot.subsystems.Rope;
import org.usfirst.frc.team1523.vision.GripPipeline;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drive drive;
	public static Ball ball;
	public static Gear gear;
	public static Rope rope;

	public static Gyro gyro;
	public static Compressor comp;

	public static VisionThread vision;
	public static double x;
	public static double y1;
	public static double y2;
	public static double distance;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		//Initialize Network Tables
		NetworkTable.initialize();
		NetworkTable.flush();
		CameraManager.init();

		//Create Subsystem Objects
		comp = new Compressor(0);
		comp.setClosedLoopControl(true);
		gyro = new ADXRS450_Gyro();
		drive = new Drive();
		ball = new Ball();
		gear = new Gear();
		rope = new Rope();
		oi = new OI();

		//Reset Hardware Values
		drive.reset();
		gyro.calibrate();

		//Create Autonomous Chooser
		chooser.addObject("Center", new AutoGear(1));
		chooser.addObject("Left", new AutoGear(0));
		chooser.addObject("Right", new AutoGear(2));
		chooser.addObject("Drive Across Baseline", new DriveAcrossBaseLine());
		chooser.addDefault("Nothing", null);
		SmartDashboard.putData("Auto Choice", chooser);
		
		//Start Vision Thread
		vision = new VisionThread(CameraServer.getInstance().startAutomaticCapture(0), new GripPipeline(), grip ->{
			if(grip.convexHullsOutput().size()==2){
				Rect r1 = Imgproc.boundingRect(grip.convexHullsOutput().get(0));
				Rect r2 = Imgproc.boundingRect(grip.convexHullsOutput().get(1));
				x = ((r1.x + r1.width/2) + (r2.x + r2.width/2)) / 2;
				if(r1.x<r2.x){
					y1 = r1.y;
					y2 = r2.y;
				}else{
					y1 = r2.y;
					y2 = r1.y;
				}
				distance = r1.x - r2.x;
				System.out.println("STEP: X: " + x + " Distance: " + distance);
			}else{
				System.out.println("NO TARGET FOUND" + grip.convexHullsOutput().size());
				x = -1;
				distance = -1;
			}
		});
		
		vision.start();
	}

	@Override
	public void disabledInit() {
		drive.reset();
		gyro.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
//		System.out.println("X: " + x);
		log();
	}

	@Override
	public void autonomousInit() {
		//Auto Camera Settings
		CameraManager.auto();
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null) autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		//Teleop Camera Settings
		CameraManager.tele();
		drive.reset();
		gyro.reset();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void log(){
		//Put important data in dashboard
		SmartDashboard.putNumber("Right Drive", drive.getRightDistance());
		SmartDashboard.putNumber("Left Drive", drive.getLeftDistance());
		SmartDashboard.putNumber("Gyro Reading", gyro.getAngle());
	}
}
