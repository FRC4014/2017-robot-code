package org.usfirst.frc.team4014.steamworks.fuelintake;

import org.usfirst.frc.team4014.steamworks.CAN;
import org.usfirst.frc.team4014.steamworks.OI;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FuelIntake extends Subsystem {

	public static final CANTalon motor = new CANTalon(CAN.FUEL_INTAKE_MOTOR);

	private final OI oi;
	private final JoystickButton button;

	public FuelIntake(OI oi) {
		super("Fuel Intake");
		this.oi = oi;

		button = new JoystickButton(oi.getMateJoystick(), 4);
		button.toggleWhenPressed(new EatFuel(this));
		
		SmartDashboard.putString("Fuel Intake", "OFF");
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
