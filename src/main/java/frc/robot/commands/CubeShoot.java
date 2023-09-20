// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CubeShoot extends SequentialCommandGroup {
  /** Creates a new CubeShoot. */
  public CubeShoot(Intake intake, double intakePower, double shooterPower) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

      new RunRightIntake(intake, intakePower).withTimeout(0.5),
      new Shoot(intake, shooterPower).withTimeout(1.5));
    
  }
}


