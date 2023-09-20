package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;

public class CubeScoreMid extends SequentialCommandGroup {
   /** Creates a new CubeShoot. */
   public CubeScoreMid(DigitalInput digitalInput, Intake boxGrabber, XboxController xboxController2, Intake intake, double intakePower, double shooterPower) {

   // Add your commands in the addCommands() call, e.g.
     // addCommands(new FooCommand(), new BarCommand());
     addCommands(
 
       new CubeAdjustMid(digitalInput, boxGrabber, xboxController2).withTimeout(0.8),
       new CubeShoot( boxGrabber, -1, -1));
     
   }
 }
