// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveArcadeCustomized extends CommandBase {
  /** Creates a new DriveArcadeCustomized. */
  Drivetrain driveTrain;
  DoubleSupplier speed;
  DoubleSupplier rotation;
  SlewRateLimiter filter;
  SlewRateLimiter filter1;
  XboxController xboxController;
  double limit;
  public DriveArcadeCustomized(Drivetrain driveTrain, DoubleSupplier speed, DoubleSupplier rotation, double limit, XboxController xboxController) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.speed = speed;
    this.rotation = rotation;
    this.limit = limit;
    this.xboxController = xboxController;
    filter = new SlewRateLimiter(0.9);
    filter1 = new SlewRateLimiter(0.9);
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    driveTrain.zeroEncoders();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(xboxController.getRightBumper()){
      driveTrain.arcadeDriveCustomized(-speed.getAsDouble()*limit, rotation.getAsDouble()*0.3);
    }
    else if(xboxController.getLeftBumper()){
      driveTrain.arcadeDriveCustomized(-speed.getAsDouble()*limit, rotation.getAsDouble()*0.3);
    }
    else{
    driveTrain.arcadeDriveCustomized(-speed.getAsDouble()*0.6, rotation.getAsDouble()*0.3);
    }
    driveTrain.putNumbers();
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
