// RomiBase-AM-23  -- AM edit for base example					Robot.j 
//  -- cmd/subsys framework runs basic Romi; auto commands use
// encoder and timing for all actions, so control is very rough, driving
// subject to Romi mechanical irregularity.
// code simplified for new users, comments clarified, advanced functions
// (lambdas) eliminated.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the 
 * functions corresponding to each mode, as described in TimedRobot docs.
 * NOTHING here specific to any one robot.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and contains
   * generic initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate RobotContainer: RC declares, instances, configs robot
    // specific components and their functionality (methods).
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. For 
   * items like diagnostics that you want run during disabled, autonomous,
   * teleoperated and test.
   * This runs after the mode specific periodic functions, but before 
   * LiveWindow & SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Calls the Scheduler <-- this is responsible for polling buttons, adding 
    // newly-scheduled commands, running now-scheduled commands, removing  
    // finished or interrupted commands, and running subsystem periodic()
    // methods. This must be called from the robotPeriodic block in order for
    //  anything in the  Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  // This function is called once each time the robot enters Disabled mode. 
    @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  // autoInit runs the autonomous command selected in {RobotContainer} class.
  @Override
  public void autonomousInit() {
    // Get selected routine from the SmartDashboard
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the selected autonomous command (if not empty variable)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    else System.out.println("No auto cmd defined");
    
  }  // end autoInit

  // This function is called periodically during autonomous.
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous code gets stopped, which enables
    // the default command which is ArcadeDrive. If you want the autonomous
    // to continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  // This function is called periodically during operator control. 
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  // This function is called periodically during test mode.
  @Override
  public void testPeriodic() {}
  
}  // end robot.j class
