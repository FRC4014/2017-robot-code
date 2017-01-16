package org.usfirst.frc.team4014.steamworks.drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	private static DriveTrain instance;

	public static synchronized DriveTrain getInstance() {
		if (instance == null) instance = new DriveTrain();
		
		return instance;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public void drive(Joystick joystick) {
		// TODO Auto-generated method stub
		
	}

}
