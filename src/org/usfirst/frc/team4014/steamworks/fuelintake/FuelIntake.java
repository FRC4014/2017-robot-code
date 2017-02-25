package org.usfirst.frc.team4014.steamworks.fuelintake;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FuelIntake extends Subsystem {

	public static final CANTalon motor = new CANTalon(CAN.FUEL_INTAKE_MOTOR);
	private static final String FUEL_RUN_SPEED = "fuelintake.run.speed";
	private static final double INTAKE_SPEED = 0.8;
			
	private final OI oi;
	private final JoystickButton button;

	public static void initPreferences() {
		Preferences prefs = Preferences.getInstance();
		prefs.putDouble(FUEL_RUN_SPEED, INTAKE_SPEED);
		
	}
	
	public FuelIntake(OI oi) {
		super("Fuel Intake");
		this.oi = oi;

		button = new JoystickButton(oi.getMateJoystick(), 7);
		button.toggleWhenPressed(new EatFuel(this));
		
		SmartDashboard.putString("Fuel Intake", "OFF");
	}

	@Override
	protected void initDefaultCommand() {
		// We don't want anything to run by default in this sub-system.
	}

	public void start() {
		Preferences prefs = Preferences.getInstance();
		double fuelIntakeSpeed = prefs.getDouble(FUEL_RUN_SPEED, INTAKE_SPEED);
		motor.set(fuelIntakeSpeed);
	}

	public void stop() {
		motor.set(0);
	}
}
