package org.usfirst.frc.team4014.steamworks.fuelintake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EatFuel extends Command {

	private final FuelIntake fuelIntake;

	public EatFuel(FuelIntake fuelIntake) {
		this.fuelIntake = fuelIntake;
		requires(fuelIntake);
	}

	@Override
	protected void execute() {
		fuelIntake.start();
		SmartDashboard.putString("Fuel Intake", "ON");
	}

	@Override
	protected void end() {
		fuelIntake.stop();
		SmartDashboard.putString("Fuel Intake", "OFF");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
