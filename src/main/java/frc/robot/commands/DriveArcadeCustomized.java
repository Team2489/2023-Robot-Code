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
  Drivetrain driveTrain;
  DoubleSupplier speed;
  DoubleSupplier rotation;
  
  XboxController xboxController;
  double limit;
  public DriveArcadeCustomized(Drivetrain driveTrain, DoubleSupplier speed, DoubleSupplier rotation, double limit, XboxController xboxController) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.speed = speed;
    this.rotation = rotation;
    this.limit = limit;
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
    double speeds = speed.getAsDouble()*(limit);
    double rotations = rotation.getAsDouble() *0.3;

    if(xboxController.getRightBumper() || xboxController.getLeftBumper() ){
      driveTrain.arcadeDriveCustomized(-speeds*limit, rotations);
    } else{
      driveTrain.arcadeDriveCustomized(-speeds*0.6, rotations);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("arcade drive ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
