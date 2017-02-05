package org.usfirst.frc.team4014.steamworks.autonomous;

import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Pivots the robot. 
 * Set speed as positive to pivot left
 * or negative to pivot right
 */
public class PivotByGyro extends Command {

	private static final double SPEED = 0.6;
	
	private final DriveTrain driveTrain;
	private double angle;
	private Gyro gyro;
	private double left;
	private double endRange;
	private double finalRange;
	private int counter;
	
	public PivotByGyro(DriveTrain driveTrain, Gyro gyro, double angle) {
		this.driveTrain = driveTrain;
		this.angle = angle;
		this.gyro = gyro;
		
	}
	
	protected void initialize(){
		if (angle > 0){
			left = -SPEED;
		} else {
			left = SPEED;
		}
		gyro.reset();
		endRange = 10;
		finalRange = -1;
	}
	
	protected void execute(){
		counter++;
//		if (left < 0.1){
//			left = 0.1;
//		}
		driveTrain.drive(left, -left);
		SmartDashboard.putNumber("angle", gyro.getAngle());
		SmartDashboard.putNumber("Counter", counter);
		SmartDashboard.putNumber("Left", left);
		SmartDashboard.putNumber("endRange", endRange);
			if (Math.abs(Math.abs(angle) - Math.abs(gyro.getAngle())) <= endRange){
				left = -left;
				endRange = endRange/2;
			}
	}

	@Override
	protected boolean isFinished() {
//		if (Math.abs(Math.abs(angle) - Math.abs(gyro.getAngle())) <= endRange){
//			new WaitCommand(.1);
////			if(Math.abs(Math.abs(angle) - Math.abs(gyro.getAngle())) <= finalRange){
////				return true;
////			} else {
//				left = -left/2;
//				endRange = endRange/2;
//				return false;
////			}
//		}
		return false;
	}

}
