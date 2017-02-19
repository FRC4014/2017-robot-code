
package org.usfirst.frc.team4014.steamworks;

import org.usfirst.frc.team4014.steamworks.autonomous.BoilerPositionBlue;
import org.usfirst.frc.team4014.steamworks.autonomous.BoilerPositionRed;
import org.usfirst.frc.team4014.steamworks.autonomous.CenterPosition;
import org.usfirst.frc.team4014.steamworks.autonomous.FakeAutonomousCommand;
import org.usfirst.frc.team4014.steamworks.autonomous.LoadingZonePositionBlue;
import org.usfirst.frc.team4014.steamworks.autonomous.LoadingZonePositionRed;
import org.usfirst.frc.team4014.steamworks.autonomous.PIDPivotByGyro;
import org.usfirst.frc.team4014.steamworks.autonomous.PivotTest;
import org.usfirst.frc.team4014.steamworks.drivetrain.DriveTrain;
import org.usfirst.frc.team4014.steamworks.gear.Gear;
import org.usfirst.frc.team4014.steamworks.fuelintake.FuelIntake;
import org.usfirst.frc.team4014.steamworks.shooter.Shooter;
import org.usfirst.frc.team4014.steamworks.vision.LEDs;
import org.usfirst.frc.team4014.steamworks.winch.Winch;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private static final AnalogGyro GYRO = new AnalogGyro(1);
	private OI oi;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	private DriveTrain driveTrain;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain(oi);
		new Shooter(oi);
		Gear gear = new Gear(oi);
		new Winch(oi, gear);
		new FuelIntake(oi);	
		new LEDs(oi);
		/*
		chooser.addDefault("Center", new CenterPosition(driveTrain, gear, GYRO));
		chooser.addObject("Boiler Position Blue", new BoilerPositionBlue(driveTrain, gear, GYRO));
		chooser.addObject("Boiler Position Red", new BoilerPositionRed(driveTrain, gear, GYRO));
		chooser.addObject("Loading Zone Position Blue", new LoadingZonePositionBlue(driveTrain, gear, GYRO));
		chooser.addObject("Loading Zone Position Red", new LoadingZonePositionRed(driveTrain, gear, GYRO));
		chooser.addObject("Do Nothing", new FakeAutonomousCommand());
		SmartDashboard.putData("Autonomous Mode Chooser", chooser);
		*/
		SmartDashboard.putData("pivot 0.5 45:", new PIDPivotByGyro(driveTrain, GYRO, 0.5, 45));
		SmartDashboard.putData("pivot 0.5 -45:", new PIDPivotByGyro(driveTrain, GYRO, 0.5, -45));
		SmartDashboard.putData("pivot 0.8 45:", new PIDPivotByGyro(driveTrain, GYRO, 0.8, 45));
		SmartDashboard.putData("pivot 0.8 -45:", new PIDPivotByGyro(driveTrain, GYRO, 0.8, -45));
		SmartDashboard.putData("pivot 1 45:", new PIDPivotByGyro(driveTrain, GYRO, 1, 45));
		SmartDashboard.putData("pivot 1 -45:", new PIDPivotByGyro(driveTrain, GYRO, 1, -45));
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {
		GYRO.reset();
	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
//		autonomousCommand = chooser.getSelected();
		autonomousCommand = new PivotTest(driveTrain, GYRO);

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
