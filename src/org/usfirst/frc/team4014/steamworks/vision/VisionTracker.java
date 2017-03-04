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
	private final int FOV_HEIGHT = 240;
	private final int LIFECAM_HORIZONTAL_ANGLE = 60;
	private final int LIFECAM_VERTICAL_ANGLE = 41;
	private final int HORIZONTAL_PIXEL_DISTANCE = 226;
	private final int VERTICAL_PIXEL_DISTANCE = 321;
	private VisionThread visionThread;
	private final Object imgLock = new Object();
	public UsbCamera camera;
	private int[] centerXs;
	private int[] centerYs;
	
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
		    
			Collections.sort(rs, (Rect a, Rect b) -> ((int)(b.area() - a.area())));
//			final StringBuilder x = new StringBuilder();
//			Iterator<Rect> itrr = rs.iterator();
//			while (itrr.hasNext()) x.append(", ").append(itrr.next().area());
//			SmartDashboard.putString("Sorted Rect Areas:", x.substring(2));
			int[] xs;
			int[] ys;
			if (rs.size() == 1) {
		    	 xs = new int[] {centerx(rs.get(0))};
		    	 ys = new int[] {centery(rs.get(0))};
		    }
		    else if (rs.size() > 1) {
		    	 xs = new int[] {centerx(rs.get(0)), centerx(rs.get(1))};
		    	 ys = new int[] {centery(rs.get(0)), centery(rs.get(1))};
		    }
		    else{
		    	 xs = new int[] {};
		    	 ys = new int[] {};
		    }
			synchronized (imgLock) {
				centerXs = xs;
				centerYs = ys;
			}
		});
		visionThread.start();
	}
	
	private int centerx(Rect rect) {
		return rect.x + (rect.width / 2);
	}
	
	private int centery(Rect rect) {
		return rect.y + (rect.height/2);
	}
	
	private double calculateDeltaAngle(double targetPixel, int pixelsize, int middledistance){
		double pixelChange = (pixelsize/2) - targetPixel;
		double deltaAngle = Math.atan((pixelChange/middledistance));
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
	
	private double getHorizontalDeltaAngle(double centerX1, double centerX2){
		double horizontalPixelTarget = middleOfTwoContours(centerX1, centerX2);
		double horizontalDeltaAngleRads = calculateDeltaAngle(horizontalPixelTarget, FOV_WIDTH, HORIZONTAL_PIXEL_DISTANCE);
		return 0 - radiansToDegrees(horizontalDeltaAngleRads);
	}
	
	private double getVerticalDeltaAngle(double centerY1, double centerY2){
		double verticalPixelTarget = middleOfTwoContours(centerY1, centerY2);
		double verticalDeltaAngleRads = calculateDeltaAngle(verticalPixelTarget, FOV_HEIGHT, VERTICAL_PIXEL_DISTANCE);
		return 0 - radiansToDegrees(verticalDeltaAngleRads);
	}
	public int[] getCenterXs(){
		return centerXs;
	}
	
	public VisionState getState(){
		int[] xs;
		int[] ys;
		int contourcount = 0;
		double horizontalDeltaAngle = 0;
		double verticalDeltaAngle = 0;
		boolean xCentered = false;
		boolean yCentered = false;
		synchronized(imgLock){
			xs = centerXs;
			ys = centerYs;
		}
		
		if (xs.length == 1){
			contourcount = 1;
			horizontalDeltaAngle = getHorizontalDeltaAngle(xs[0], 160);
			verticalDeltaAngle = getVerticalDeltaAngle(ys[0], 120);
			xCentered = false;
			yCentered = false;
		}
		else if((xs.length == 2)) {
			contourcount = 2;
			verticalDeltaAngle = getVerticalDeltaAngle(ys[0], ys[1]);
			horizontalDeltaAngle = getHorizontalDeltaAngle(xs[0], xs[1]);
			System.out.println(verticalDeltaAngle);
			xCentered = (Math.abs(horizontalDeltaAngle) < 1.5);
			yCentered = (Math.abs(verticalDeltaAngle) < 1.5);
		}
		System.out.println(horizontalDeltaAngle);
		
		return new VisionState(horizontalDeltaAngle, xCentered, contourcount, yCentered, verticalDeltaAngle );
	}
}
