package org.usfirst.frc.team4014.steamworks;

/**
 * Logical names for CAN IDs.
 */
public final class CAN {

	public static final int DRIVE_TRAIN_LEFT_MOTOR_1 = 2;
	public static final int DRIVE_TRAIN_LEFT_MOTOR_2 = 3;
	public static final int DRIVE_TRAIN_RIGHT_MOTOR_1 = 4;
	public static final int DRIVE_TRAIN_RIGHT_MOTOR_2 = 5;
	public static final int WINCH_MOTOR = 6;
	public static final int SHOOTER_MOTOR_1 = 7;
	public static final int SHOOTER_MOTOR_2 = 8;
	public static final int FUEL_INTAKE_MOTOR = 9;
	public static final int PNEUMATIC_CONTROL_MODULE = 16;

	/**
	 * This class is only used for static references, so hide the constructor.
	 */
	private CAN() {
	}
	
	/**
	 * Mapping values for botlobster
	 * SRX plug covers use a 5/64th
	 * SRX ID = 7, CAN ID = 5, Port Drive Two
	 * SRX ID = 4, CAN ID = 3. Starboard Drive Two
	 * SRX ID = 5, CAN ID = 9. Ball Intake
	 * SRX ID = 8, CAN ID = 8. Shooter 1
	 * SRX ID = 2, CAN ID = 6. Winch
	 * SRX ID = 6, CAN ID = 4. Starboard Drive One
	 * SRX ID = 3, CAN ID = 2. Port Drive One
	 */
}
