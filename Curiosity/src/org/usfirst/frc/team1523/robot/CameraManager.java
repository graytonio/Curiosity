package org.usfirst.frc.team1523.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraManager {
	
	public static UsbCamera cam1;
	public static UsbCamera cam2;

	public static void init(){
		//Grab Camera Feeds
		cam1 = CameraServer.getInstance().startAutomaticCapture(0);
		cam2 = CameraServer.getInstance().startAutomaticCapture(1);
	}
	
	/**
	 * Teleop Camera Settings
	 */
	public static void tele(){
		cam1.setExposureManual(30);
		cam2.setExposureManual(30);
	}
	
	/**
	 * Autonomous Camera Settings
	 */
	public static void auto(){
		cam1.setExposureManual(-50);
		cam2.setExposureManual(-50);
	}
}
