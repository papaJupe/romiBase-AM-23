// RomiBase-AM-23              commands/AutonomousDistance.java

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonomousDistance extends SequentialCommandGroup {
  /**
   * construct new Autonomous sequence using encoder-based distance.
   * drive a specified distance, turn 180 deg, (could) drive back and turn
   * again if turns accurate.
   * @param drivetrain the subsystem to be controlled
   */
  public AutonomousDistance(Drivetrain drivetrain) {
    addCommands(
        new DriveDistance(1.0, 30, drivetrain),
        new WaitCommand(5.0),
         new TurnDegrees(0.7, 180, drivetrain));
        // new WaitCommand(5.0),
        // new DriveDistance(1, 30, drivetrain),
        // new WaitCommand(5.0),
        // new TurnDegrees(1, -180, drivetrain));
  }
}  // end class
