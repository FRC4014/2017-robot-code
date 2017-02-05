package org.usfirst.frc.team4014.steamworks.fuelintake;

import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelIntake extends Subsystem {

	// TODO: what's the real CAN ID?
	public static final CANTalon motor = new CANTalon(9);

	private final OI oi;
	private final JoystickButton button;

	public FuelIntake(OI oi) {
		super("Fuel Intake");
		this.oi = oi;

		button = new JoystickButton(oi.getMateJoystick(), 4);
		button.toggleWhenPressed(new EatFuel(this));
	}

	@Override
	protected void initDefaultCommand() {
		// We don't want anything to run by default in this sub-system.
	}

	public void start() {
		motor.set(1);
	}

	public void stop() {
		motor.set(0);
	}
}
