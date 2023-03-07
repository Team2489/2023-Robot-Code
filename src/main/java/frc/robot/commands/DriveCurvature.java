// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


public class DriveCurvature extends CommandBase {
  /** Creates a new DriveCurvature. */
  Drivetrain driveTrain;
  DoubleSupplier speed;
  DoubleSupplier rotation;
  public DriveCurvature(Drivetrain driveTrain, DoubleSupplier speed, DoubleSupplier rotation){
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.speed = speed;
    this.rotation = rotation;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.curvatureDrive(speed.getAsDouble()*0.75, rotation.getAsDouble()*0.75);
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
