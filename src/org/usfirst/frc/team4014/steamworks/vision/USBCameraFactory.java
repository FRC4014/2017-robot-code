package org.usfirst.frc.team4014.steamworks.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public final class USBCameraFactory {
	
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	private static UsbCamera camera;
	
	static {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera.setExposureManual(-100);
		camera.setExposureHoldCurrent();
		camera.setBrightness(-1000);
	}
	
	public static final UsbCamera getCamera(){
		return camera;
	}
		
	

}
