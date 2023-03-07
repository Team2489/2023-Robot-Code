// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveTank extends CommandBase {
  /** Creates a new DriveTank. */
  Drivetrain driveTrain;
  DoubleSupplier right;
  DoubleSupplier left;
  SlewRateLimiter filter;
  SlewRateLimiter filter1;
  public DriveTank(Drivetrain driveTrain, DoubleSupplier right, DoubleSupplier left) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.right = right;
    this.left = left;
    filter = new SlewRateLimiter(0.9);
    filter1 = new SlewRateLimiter(0.9);
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.tankDrive(filter.calculate(right.getAsDouble())*0.5, filter1.calculate(left.getAsDouble())*0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
