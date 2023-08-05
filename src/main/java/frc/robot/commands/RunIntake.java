// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  /** Creates a new GrabberOne. */
  Intake boxGrabber = null;
  DigitalInput  digitalInput = null;
  double power = 0.0;
  XboxController xboxController = null;

  public RunIntake(Intake boxGrabber, double power) {
    this.boxGrabber = boxGrabber;
    this.power = power;
    addRequirements(boxGrabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    boxGrabber.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boxGrabber.intakeRun(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    boxGrabber.stop();
    System.out.println("run intake method stopped");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
