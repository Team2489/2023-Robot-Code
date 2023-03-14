// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

public static final int RIGHT_FRONT_SPARK = 1;
public static final int RIGHT_BACK_SPARK = 2;
public static final int LEFT_FRONT_SPARK = 3;
public static final int LEFT_BACK_SPARK =4;
public static final int RIGHT_INTAKE = 0;
public static final int LEFT_INTAKE = 0;
public static final int XBOX_CONTROLLER_PORT = 0;
public static final int LINE_BREAKER_PORT = 1;

// arm constants
public static final int frameMotorPort      = 5;
public static final int rodMotorPort        = 6;
public static final int throwerMotorPort    = 7;

public static final int[] kEncoderPorts     =
                new int[] { 4,5,
                            6,7,
                            8,9};
public static final int kEncoderPPR         = 256;
public static final double kEncoderDistancePerPulse = 2.0 * Math.PI / kEncoderPPR;

public static final double kArmOffsetRads   = 0.5;
public static final double kP               = 1;

public static final double kMaxAccelerationRadPerSecSquared = 10;
public static final double kMaxVelocityRadPerSecond         = 3;

public static final double kSVolts                      = 1;
public static final double kGVolts                      = 1;
public static final double kVVoltSecondPerRad           = 0.5;
public static final double kAVoltSecondSquaredPerRad    = 0.1;


}

//public static final class ArmConstants {
//    public static final int kMotorPort = 4;
//
//    public static final double kP = 1;
//
//    // These are fake gains; in actuality these must be determined individually for each robot
//    public static final double kSVolts = 1;
//    public static final double kGVolts = 1;
//    public static final double kVVoltSecondPerRad = 0.5;
//    public static final double kAVoltSecondSquaredPerRad = 0.1;
//
//    public static final double kMaxVelocityRadPerSecond = 3;
//    public static final double kMaxAccelerationRadPerSecSquared = 10;
//
//    public static final int[] kEncoderPorts = new int[] {4, 5};
//    public static final int kEncoderPPR = 256;
//    public static final double kEncoderDistancePerPulse = 2.0 * Math.PI / kEncoderPPR;
//
//    // The offset of the arm from the horizontal in its neutral position,
//    // measured from the horizontal
//    public static final double kArmOffsetRads = 0.5;
//}
