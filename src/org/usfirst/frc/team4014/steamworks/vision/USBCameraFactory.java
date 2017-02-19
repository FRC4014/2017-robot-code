package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public final class USBCameraFactory {
	
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	private static UsbCamera camera;
	
	public static synchronized final UsbCamera getCamera(){
		if (camera == null) {
		    camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
			camera.setExposureManual(-100);
			camera.setExposureHoldCurrent();
//			camera.setBrightness(-1000);
			System.out.println("USBCameraFactory.getCamera: The camera has been created");
		}
		return camera;
	}
		
	

}
