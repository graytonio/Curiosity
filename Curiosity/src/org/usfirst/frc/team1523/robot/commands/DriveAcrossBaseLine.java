package org.usfirst.frc.team1523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAcrossBaseLine extends CommandGroup {

    public DriveAcrossBaseLine() {
        addSequential(new AutoDrive(0.7, 100));
    }
}
