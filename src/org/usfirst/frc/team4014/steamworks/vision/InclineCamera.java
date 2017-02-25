package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InclineCamera extends Command {
	private static final int MAX_SERVO_ANGLE = 100;
	private static final int MIN_SERVO_ANGLE = 20;
	
	final VisionTracker vision;
	private static final Servo cameraServo = new Servo(4);
	private VisionState visionState;
	private final boolean boilerMode;
	private boolean oneAndDone = false;
	private Preferences prefs;
	
	
	public InclineCamera(VisionTracker vision, boolean boilerMode) {
		this.boilerMode = boilerMode;
		this.vision = vision;
	}
	
	
	@Override
	protected void initialize() {
		prefs = Preferences.getInstance();
		super.initialize();
		visionState = vision.getState();
		if (this.boilerMode){
			int boilerInitAngle = prefs.getInt("vision.InclineCamera.boilerInitAngle", 50);
			cameraServo.setAngle(boilerInitAngle);
		}
		else {
			int pegInitAngle = prefs.getInt("vision.InclineCamera.pegInitAngle", 10);
			cameraServo.setAngle(pegInitAngle);
		}
		System.out.println(cameraServo.getAngle());
	}


	@Override
	protected void execute() {
		super.execute();
		double currentAngle = cameraServo.getAngle();
		System.out.println("Got Angle!");
		System.out.println(currentAngle);
		VisionState newVisionState = vision.getState();
		if (!newVisionState.quickEquals(visionState) || newVisionState.contourCount == 0) {
			visionState = newVisionState;
			if (visionState.contourCount == 2){
				cameraServo.setAngle(currentAngle - visionState.verticalDeltaAngle);
			}
			else if (visionState.contourCount == 1) {
				cameraServo.setAngle(currentAngle - visionState.verticalDeltaAngle);
			}
			else {
				double defaultDeltaAngle = prefs.getDouble("vision.InclineCamera.defaultDeltaCameraServo", 0.5);
				cameraServo.setAngle(currentAngle + defaultDeltaAngle);
			}
				SmartDashboard.putNumber("cameraServo Angle", cameraServo.getAngle());
				SmartDashboard.putNumber("verticalDeltaAngle", visionState.verticalDeltaAngle);
				//oneAndDone = true;
	
				
			}
	}

	public void moveServo(double angle){
		cameraServo.setAngle(angle);
	}
	@Override
	protected boolean isFinished() {
		boolean done = visionState.yCentered;
		return done;
	}

}
