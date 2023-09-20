package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class CubeAdjustMid extends CommandBase  {
    DigitalInput  digitalInput = null;
    boolean done = false;
    Intake boxGrabber = null;
    XboxController xboxController = null;




    public CubeAdjustMid(DigitalInput digitalInput, Intake boxGrabber, XboxController xboxController) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(boxGrabber);
        this.digitalInput = digitalInput;
        this.boxGrabber = boxGrabber;
        this.xboxController = xboxController;
      }
    @Override
    public void initialize() {
    boxGrabber.stop();
    }


    @Override
    public void execute() {
    if (xboxController.getAButton() && !digitalInput.get()) {
        boxGrabber.runLeftIntake(0.09);
    }

    if(!digitalInput.get()){
        boxGrabber.runLeftIntake(0.09);
    } else {
        boxGrabber.stop();
    }
     /* else if (xboxController.getAButton()) {
        boxGrabber.runLeftIntake(-0.5);
    } */
    }

    @Override
    public void end(boolean interrupted) {
      boxGrabber.stop();
      done=true;
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
