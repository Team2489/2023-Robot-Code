// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BoxGrabber;

public class GrabberIntake extends CommandBase {
  /** Creates a new GrabberIntake. */
  BoxGrabber gIntake;
  boolean grab;
  boolean release;


  public GrabberIntake(BoxGrabber gIntake, Boolean grab, boolean release) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.gIntake = gIntake;
    this.grab = grab;
    this.release = release;
    addRequirements(gIntake);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    gIntake.intake(grab, release);
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
