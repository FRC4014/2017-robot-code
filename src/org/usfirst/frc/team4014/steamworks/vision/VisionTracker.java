package org.usfirst.frc.team4014.steamworks.vision;

import java.util.ArrayList;
import java.util.Collections;
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
	private double centerX1 = 0.0;
	private double centerX2 = 0.0;
	private final Object imgLock = new Object();
	public UsbCamera camera;
	private List<Integer> centerXs;
	
	public VisionTracker() {
		camera = USBCameraFactory.getCamera();
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			ArrayList<Rect> rs = new ArrayList<Rect>();
			Iterator<MatOfPoint> itr = pipeline.filterContoursOutput().iterator();
			while(itr.hasNext()){
				Rect r = Imgproc.boundingRect(itr.next());
				rs.add(r);
			}
			Collections.sort(rs, (Rect a, Rect b) -> (a.area - b.area));
			synchronized (imgLock) {
				this.centerXs = Collections.unmodifiableList(tempc);
			}
					
			SmartDashboard.putNumber("centerX1", centerX1);
		    SmartDashboard.putNumber("centerX2", centerX2);
		});
		visionThread.start();
	}
	
	public double[] findContoursCenterX(){
		synchronized (imgLock) {
			centerXs0 = this.centerX1;
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
