package org.usfirst.frc.team4014.steamworks.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.MatOfPoint;
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
	private final Object imgLock = new Object();
	public UsbCamera camera;
	private int[] centerXs;
	
	public VisionTracker() {
		camera = USBCameraFactory.getCamera();
		if (camera.isConnected())
			System.out.println("VisionTracker: There is a camera!");
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			ArrayList<Rect> rs = new ArrayList<Rect>();
			Iterator<MatOfPoint> itr = pipeline.filterContoursOutput().iterator();
			while(itr.hasNext()){
				Rect r = Imgproc.boundingRect(itr.next());
				rs.add(r);
			}
			SmartDashboard.putNumber("rectangles", rs.size());
			SmartDashboard.putNumber("centerX1", centerXs[0]);
		    SmartDashboard.putNumber("centerX2", centerXs[1]);
		    
			Collections.sort(rs, (Rect a, Rect b) -> ((int)(b.area() - a.area())));
			final StringBuilder x = new StringBuilder();
			Iterator<Rect> itrr = rs.iterator();
			while (itrr.hasNext()) x.append(", ").append(itrr.next().area());
			SmartDashboard.putString("Sorted Rect Areas:", x.substring(2));
			int[] xs;
			if (rs.size() == 1) {
		    	 xs = new int[] {centerx(rs.get(0))};
		    }
		    else if (rs.size() > 1) {
		    	 xs = new int[] {centerx(rs.get(0)), centerx(rs.get(1))};
		    }
		    else{
		    	 xs = new int[] {};
		    }
			synchronized (imgLock) {
				centerXs = xs;
			}
		});
		visionThread.start();
	}
	
	private int centerx(Rect rect) {
		return rect.x + (rect.width / 2);
	}
	
	
	private double calculateDeltaAngle(double targetPixel){
		double pixelChange = (FOV_WIDTH/2) - targetPixel;
		double deltaAngle = Math.atan((pixelChange/PIXEL_DISTANCE));
		return deltaAngle;
	}
	
	private double middleOfTwoContours(double centerX1, double centerX2){
		double middleOfContours = (centerX1 + centerX2) / 2;
		SmartDashboard.putNumber("middle", middleOfContours);
		return middleOfContours;
	}
	
	private double radiansToDegrees(double rads) {
		return rads * (90/Math.PI);
	} 
	
	private double getDeltaAngle(double centerX1, double centerX2){
		double pixelTarget = middleOfTwoContours(centerX1, centerX2);
		double deltaAngleRads = calculateDeltaAngle(pixelTarget);
		return 0 - radiansToDegrees(deltaAngleRads);
	}
	public int[] getCenterXs(){
		return centerXs;
	}
	
	public VisionState getState(){
		int[] xs = centerXs;
	
		int contourcount = 0;
		double angle = 0;
		boolean centered = false;
		
		if (xs.length == 1){
			contourcount = 1;
			angle = getDeltaAngle(xs[0], 160);
			centered = false;
		}
		else if((xs.length == 2)) {
			contourcount = 2;
			angle = getDeltaAngle(xs[0], xs[1]);
			centered = (angle < 1);
		}
		
		return new VisionState(angle, centered, contourcount);
	}
}
