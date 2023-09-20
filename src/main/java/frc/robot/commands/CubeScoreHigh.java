package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;

public class CubeScoreHigh extends SequentialCommandGroup {
   /** Creates a new CubeShoot. */
   public CubeScoreHigh(DigitalInput digitalInput, Intake boxGrabber, XboxController xboxController2, Intake intake, double intakePower, double shooterPower) {

   double power = 0.1;
    // Add your commands in the addCommands() call, e.g.
     // addCommands(new FooCommand(), new BarCommand());
     addCommands(
       
       //new CubeAdjustFullHigh(digitalInput, boxGrabber, xboxController2),
       new CubeAdjustHigh(digitalInput, boxGrabber, xboxController2).withTimeout(0.30),
       new RunLeftIntake(intake, power).withTimeout(0.5),
       new CubeShoot( boxGrabber, -1, -1)
       );
     
   }
 }
