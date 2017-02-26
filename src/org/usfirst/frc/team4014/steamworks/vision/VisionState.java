package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class VisionState {
	final double horizontalDeltaAngle;
	final boolean xCentered;
	final int contourCount;
	final boolean yCentered;
	final double verticalDeltaAngle;
	final long timeStamp;
	
	public VisionState(double horizontalDeltaAngle, boolean centered, int contourCount, boolean yCentered, double verticalDeltaAngle) {
		timeStamp = System.currentTimeMillis();
		this.horizontalDeltaAngle = horizontalDeltaAngle;
		this.xCentered = centered;
		this.contourCount = contourCount;
		this.yCentered = yCentered;
		this.verticalDeltaAngle = verticalDeltaAngle;
		SmartDashboard.putNumber("contours", this.contourCount);
		System.out.println(verticalDeltaAngle);
		System.out.println(yCentered);
		
	}

	public boolean quickEquals(VisionState other){
		return (other != null) && (this.horizontalDeltaAngle == other.horizontalDeltaAngle) && (this.verticalDeltaAngle == other.verticalDeltaAngle);
	}
	
}
