package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class InclineCamera extends Command {
	
	private static final int MAX_SERVO_ANGLE = 100;
	private static final int MIN_SERVO_ANGLE = 20;
	private static final int BOILER_INIT_ANGLE = 50;
	private static final int PEG_INIT_ANGLE = 10;
	//TODO find what the actual angles are
	
	final VisionTracker vision;
	private final Servo cameraServo;
	private VisionState visionstate;
	private final boolean boilerMode;
	
	public InclineCamera(VisionTracker vision, Servo cameraServo, boolean boilerMode) {
		this.boilerMode = boilerMode;
		this.vision = vision;
		this.cameraServo = cameraServo;
	}
	
	
	@Override
	protected void initialize() {
		super.initialize();
		if (this.boilerMode){
			cameraServo.setAngle(BOILER_INIT_ANGLE);
		}
		else {
			cameraServo.setAngle(PEG_INIT_ANGLE);
		}
	}


	@Override
	protected void execute() {
		super.execute();
		double currentAngle = cameraServo.getAngle();
		visionstate = vision.getState();
		if (visionstate.contourCount == 2){
			cameraServo.setAngle(currentAngle + visionstate.verticalDeltaAngle);
		}
		else if (visionstate.contourCount == 1) {
			cameraServo.setAngle(currentAngle + visionstate.verticalDeltaAngle);
		}
		else {
			if (currentAngle < MAX_SERVO_ANGLE){
				cameraServo.setAngle(currentAngle + 2);
			}
	
			
		}
	}


	@Override
	protected boolean isFinished() {
		boolean done = visionstate.yCentered;
		return done;
	}

}
