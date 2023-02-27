// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveArcade extends CommandBase {
  /** Creates a new DriveArcade. */
    Drivetrain driveTrain;
    DoubleSupplier speed;
    DoubleSupplier rotation;
    SlewRateLimiter filter;
    SlewRateLimiter filter1;
  public DriveArcade(Drivetrain driveTrain, DoubleSupplier speed, DoubleSupplier rotation) {
    this.driveTrain = driveTrain;
    this.speed = speed;
    this.rotation = rotation;
    filter = new SlewRateLimiter(0.9); //0.9 best results
    filter1 = new SlewRateLimiter(0.2); 
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.arcadeDrive(speed.getAsDouble()*0.75, filter.calculate(rotation.getAsDouble()*0.75));
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
