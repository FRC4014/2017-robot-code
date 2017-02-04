package org.usfirst.frc.team4014.steamworks.vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class VisionTracker {
	
	private final int FOV_WIDTH = 320;
	private final int LIFECAM_HORIZONTAL_ANGLE = 60;
	private final int PIXEL_DISTANCE = 226;
	private VisionThread visionThread;
	private double centerX = 0.0;
	private final Object imgLock = new Object();
	
	public VisionTracker(){
	
	}
	
	public VisionTracker(UsbCamera camera) {
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			if (!pipeline.filterContoursOutput().isEmpty()) {
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
		    	synchronized (imgLock) {
		    		centerX = r.x + (r.width / 2);
		    	}
		    	SmartDashboard.putNumber("centerX", centerX);
			}
		});
		visionThread.start();
	}
	
	public double findContoursCenterX(){
		synchronized (imgLock) {
			centerX = this.centerX;
			return centerX;
		}
	}
	private double calculateDeltaAngle(double targetpixel){
		double pixelchange = (FOV_WIDTH/2) - targetpixel;
		double deltaangle = Math.atan((pixelchange/PIXEL_DISTANCE));
		return deltaangle;
	}
	
	private double middleOfTwoContours(double contour1centerx, double contour2centerx){
		double middleofcontours = (contour1centerx + contour2centerx)/2;
		return middleofcontours;
	}
}
