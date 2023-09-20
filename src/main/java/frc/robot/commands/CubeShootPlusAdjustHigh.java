package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;

public class CubeShootPlusAdjustHigh extends SequentialCommandGroup {
   /** Creates a new CubeShoot. */
   public CubeShootPlusAdjustHigh(DigitalInput digitalInput, Intake boxGrabber, XboxController xboxController2, Intake intake, double intakePower, double shooterPower) {

   double power = 0.5;
    // Add your commands in the addCommands() call, e.g.
     // addCommands(new FooCommand(), new BarCommand());
     addCommands(
       
       //new CubeAdjustFullHigh(digitalInput, boxGrabber, xboxController2),
       new CubeAdjust(boxGrabber, 0.15).withTimeout(0.35),
       new CubeShoot( boxGrabber, -1, -1)
       );
     
   }
 }
