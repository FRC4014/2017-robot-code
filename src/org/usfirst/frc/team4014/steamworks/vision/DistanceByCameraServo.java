package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.first.wpilibj.Servo;

public class DistanceByCameraServo {
	
	private static final double heightOfMiddleOfBoilerTargets = 83;
	private static final double cameraOffTheFloor = 4.5;
	private Servo servo;

	public DistanceByCameraServo(Servo servo){
		this.servo = servo;
	}
	public double getDistanceFromServo(){
		double degServoAngle = this.servo.getAngle();
		double radServoAngle = degServoAngle * (Math.PI/180);
		double distance = (heightOfMiddleOfBoilerTargets - cameraOffTheFloor)/ Math.tan(radServoAngle);
		return distance;

	}
}
