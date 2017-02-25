package org.usfirst.frc.team4014.steamworks;

import org.usfirst.frc.team4014.steamworks.autonomous.CenterPosition;
import org.usfirst.frc.team4014.steamworks.fuelintake.FuelIntake;
import org.usfirst.frc.team4014.steamworks.vision.VisionTracker;
import org.usfirst.frc.team4014.steamworks.winch.Winch;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class InitPreferencesCommand extends Command {
	
	private static final String INIT_PREFERENCES_BLOCK = "init.preferences.block";

	@Override
	protected void execute() {
		Preferences prefs = Preferences.getInstance();
		if (!prefs.getBoolean(INIT_PREFERENCES_BLOCK, false)) {
			prefs.putBoolean(INIT_PREFERENCES_BLOCK, true);
			CenterPosition.initPreferences();
			Winch.initPreferences();
			FuelIntake.initPreferences();
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
