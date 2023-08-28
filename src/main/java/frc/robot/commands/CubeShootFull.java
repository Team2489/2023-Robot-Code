package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Intake;

public class CubeShootFull extends ParallelCommandGroup  {

   // DigitalInput digitalInput = new DigitalInput(Constants.LINE_BREAKER_PORT);

    public CubeShootFull(Intake intake, double intakePower, double shooterPower, DigitalInput digitalInput) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(
    
         new CubeShoot(intake, intakePower, shooterPower));
         // new CubeAdjustFull(digitalInput));
        
      }
}
