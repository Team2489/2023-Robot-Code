// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveArcadeCustomized extends CommandBase {
  /** Creates a new DriveArcadeCustomized. */
  Drivetrain driveTrain = null;
  DoubleSupplier speed = null;
  DoubleSupplier rotation = null;
  XboxController xboxController = null;
  double creepRotationLimit = 0.0;
  double creepLimit = 0.0;
  double fastLimit =0.0;
  

  public DriveArcadeCustomized(Drivetrain driveTrain, DoubleSupplier speed, DoubleSupplier rotation, double creepLimit, double creepRotationLimit, double fastLimit, XboxController xboxController) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.speed = speed;
    this.rotation = rotation;
    this.creepLimit = creepLimit;
    this.creepRotationLimit = creepRotationLimit;
    this.fastLimit = fastLimit;
    this.xboxController = xboxController;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.arcadeDriveCustomized(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speeds = speed.getAsDouble();
    double rotations = rotation.getAsDouble();
    double currentLimit = 0.6;
    double currentRotationLimit = 0.3;
    if(xboxController.getRightBumper()){
      currentLimit = fastLimit;
      currentRotationLimit = 0.3;
    }
    else if(xboxController.getLeftBumper()) {
      currentLimit = creepLimit;
      currentRotationLimit = creepRotationLimit;
    }

    driveTrain.arcadeDriveCustomized(-speeds*currentLimit, rotations*currentRotationLimit);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.arcadeDriveCustomized(0, 0);
    System.out.println("arcade drive ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
