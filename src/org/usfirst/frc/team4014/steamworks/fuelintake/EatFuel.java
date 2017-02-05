package org.usfirst.frc.team4014.steamworks.fuelintake;

import edu.wpi.first.wpilibj.command.Command;

public class EatFuel extends Command {

	private final FuelIntake fuelIntake;

	public EatFuel(FuelIntake fuelIntake) {
		this.fuelIntake = fuelIntake;
		requires(fuelIntake);
	}

	@Override
	protected void execute() {
		fuelIntake.start();
	}

	@Override
	protected void end() {
		fuelIntake.stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
