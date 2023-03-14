
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.Constants;

public class ArmCommand extends CommandBase {


    Arm arm;
    Constants.ArmCommands command;

    public ArmCommand(Arm arm, Constants.ArmCommands comm) {
        this.arm = arm;
        this.command = comm;

        addRequirements(arm);
    }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      switch (command) {
          case CALIBRATE:
              arm.calibrate();
              break;
          case INTAKE:
              arm.intake();
              break;
          case THROW_UP:
              arm.throwUp();
              break;
          case THROW_MID:
              arm.throwMid();
              break;
          default:
              arm.rest();
      }
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
