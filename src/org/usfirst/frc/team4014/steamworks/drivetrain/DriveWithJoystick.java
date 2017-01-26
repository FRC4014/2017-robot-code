/*
 * The Top Hat Technician's 2017 Robot Code
 *
 *      (c) 2017 Top Hat Technicians 
 *      tophattechnicians.com | FRC team 4014
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of the Top Hat Technicians.  The intellectual and technical 
 * concepts contained herein are proprietary (at least until the end of the 
 * season) to the Top Hat Technicians, and are protected by trade secret or 
 * copyright law.  Dissemination of this information or reproduction of this 
 * material is forbidden unless permission is obtained from the Top Hat 
 * Technicians.  The information may be released under a more permissive 
 * license in the future, but until then, all this stuff is hush-hush.
 */
package org.usfirst.frc.team4014.steamworks.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4014.steamworks.OI;


/**
 * Operates the DriveTrain with the driver joystick.  Should be the "default
 * command" of DriveTrain, and is therefore running the entire match.
 */
public class  DriveWithJoystick extends Command {
	
//	public float test;
	
	private final DriveTrain driveTrain;
	private final OI oi;
	
    public DriveWithJoystick(OI oi, DriveTrain driveTrain) {
		this.oi = oi;
		this.driveTrain = driveTrain;
        requires(driveTrain);
	}
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        driveTrain.drive(oi.getDriverJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //will never finish
    }

    // Called once after isFinished returns true
    protected void end() {
        driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
