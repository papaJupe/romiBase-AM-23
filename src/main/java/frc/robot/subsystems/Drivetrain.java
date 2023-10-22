//  RomiBase-AM-23         drive subsystem    Drivetrain.j   
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.sensors.RomiGyro;

public class Drivetrain extends SubsystemBase {
  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.756; // 70 mm

  // The Romi has left and right motors set to PWM channel 0 and 1
  private final Spark m_leftMotor = new Spark(0);
  private final Spark m_rightMotor = new Spark(1);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  // Set up the differential drive controller
  private final DifferentialDrive m_diffDrive =
          new DifferentialDrive(m_leftMotor, m_rightMotor);

  // Set up the RomiGyro; N.B. gyro instanced inside drivetrain subsystem
  private final RomiGyro m_gyro = new RomiGyro();

  // Set up the BuiltInAccelerometer
  private final BuiltInAccelerometer m_accelerometer = new 
       BuiltInAccelerometer();

  /** CONSTRUCT a new Drivetrain. */
  public Drivetrain() {
    // We need to invert one side of the drivetrain so that positive #
    // results in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side
    m_rightMotor.setInverted(true);

    // Use inches as unit for encoder distances
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) /
          kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) /
          kCountsPerRevolution);
    resetEncoders();
  } //end constructor

  // sloppy naming by WPI: top aD() is subsys. method, second is inherited
  //  diffDrive method; using same name confuses the issue
  public void arcaDriv(double YaxisSpeed, double ZaxisRotate) {
    // diffDrive aD does some math that inverts z-rot
    m_diffDrive.arcadeDrive(YaxisSpeed * 0.6, -ZaxisRotate * 0.6, true);
  } 

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }
  public int getLeftEncoderCount() {
    return m_leftEncoder.get();
  }
  public int getRightEncoderCount() {
    return m_rightEncoder.get();
  }
  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }
  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }
  public double getAverageDistanceInch() {
    return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
  }
  // @return The acceleration of the Romi along the X-axis in Gs
  public double getAccelX() {
    return m_accelerometer.getX();
  }
   // @return The acceleration of the Romi along the Y-axis in Gs
  public double getAccelY() {
    return m_accelerometer.getY();
  }

  //  @return The acceleration of the Romi along the Z-axis in Gs
     public double getAccelZ() {
    return m_accelerometer.getZ();
  }

  /* @return The current angle of the Romi along X axis in degrees
  public double getGyroAngleX() {
    return m_gyro.getAngleX();
  }

  /* @return The current angle of the Romi along Y axis in degrees
   */
  public double getGyroAngleY() {
    return m_gyro.getAngleY();
  }

  // @return The current angle of the Romi in degrees on z Axis 
     public double getGyroAngleZ() {
    return m_gyro.getAngleZ();
  }

  // Reset the gyro.
  public void resetGyro() {
    m_gyro.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}  // end class
