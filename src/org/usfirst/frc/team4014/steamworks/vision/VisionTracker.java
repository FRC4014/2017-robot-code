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
	private double centerX1 = 0.0;
	private double centerX2 = 0.0;
	private final Object imgLock = new Object();
	private double[] contours = new double[2];
	public UsbCamera camera;
	
	public VisionTracker() {
		camera = USBCameraFactory.getCamera();
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			if (!pipeline.filterContoursOutput().isEmpty()) {
				if (pipeline.filterContoursOutput().size() > 1){
					Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
			    	Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
					synchronized (imgLock) {
			    		centerX1 = r1.x + (r1.width / 2);
			    		centerX2 = r2.x + (r2.width / 2);
			    	}
					
				}
				SmartDashboard.putNumber("centerX1", centerX1);
		    	SmartDashboard.putNumber("centerX2", centerX2);
			}
		});
		visionThread.start();
	}
	
	public double[] findContoursCenterX(){
		synchronized (imgLock) {
			contours[0] = this.centerX1;
			contours[1] = this.centerX2;
			
			return contours;
		}
	}
	
	
	private double calculateDeltaAngle(double targetpixel){
		double pixelchange = (FOV_WIDTH/2) - targetpixel;
		double deltaangle = Math.atan((pixelchange/PIXEL_DISTANCE));
		return deltaangle;
	}
	
	private double middleOfTwoContours(double[] contours){
		double middleofcontours = (contours[0] + contours[1]) / 2;
		return middleofcontours;
	}
}
