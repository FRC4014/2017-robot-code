package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InclineCamera extends Command {
	private static final int MAX_SERVO_ANGLE = 100;
	private static final int MIN_SERVO_ANGLE = 20;
	private static final int BOILER_INIT_ANGLE = 50;
	private static final int PEG_INIT_ANGLE = 10;
	//TODO find what the actual angles are
	
	final VisionTracker vision;
	private static final Servo cameraServo = new Servo(4);
	private VisionState visionState;
	private final boolean boilerMode;
	private boolean oneAndDone = false;
	
	public InclineCamera(VisionTracker vision, boolean boilerMode) {
		this.boilerMode = boilerMode;
		this.vision = vision;
	}
	
	
	@Override
	protected void initialize() {
		super.initialize();
		visionState = vision.getState();
		if (this.boilerMode){
			cameraServo.setAngle(BOILER_INIT_ANGLE);
		}
		else {
			cameraServo.setAngle(PEG_INIT_ANGLE);
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
			//	if (currentAngle < MAX_SERVO_ANGLE){
					cameraServo.setAngle(currentAngle + .5);
			//	}
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
