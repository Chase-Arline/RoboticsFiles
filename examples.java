

DRIVETRAIN INHERITANCE EXAMPLE

public class Drivetrain extends ReplayableSubsystem{
	public void setReplayValues(double[] replayVals){
		!! call standard drive method using parameters !!
	}

	public double[] getRecordValues(){
		!! return drivetrain values from joysticks !!
	}

	public void drive(double[] driveValues){
		!! set drivetrain values from parameters !!
	}
}


LIFT INHERITANCE EXAMPLE

public class Lift extends ReplayableSubsystem{
	public void setReplayValues(double[] replayVals){
		!! call standard lift method using parameters !!
	}
	
	public double[] getRecordValues(){
		!! return lift values from dpad/whatever is being used to control the lift !!
	}
	
	public void setLift(double[] liftValues){
		!! set lift power using parameter values !!
	}

}

// COMMAND USAGE EXAMPLE / SETTING UP OF BUTTONS

// !!! Robot.drivetrain is of type Drivetrain (which extends ReplayableSubsystem) !!!

//Create buttons to lauch commands
Button lBumper = new Button(xbox_controller_object, port#);
Button rBumper = new Button(xbox_controller_object, port#);

//set button to record the drivetrain for 5000 milliseconds and name the path as "driveToScale" for replaying
lBumper.whenPressed(new RecordSubsystem("driveToScale",5000,Robot.drivetrain));				
//	OR
//set button to record the path for however long the button is held for, and name the path as "driveToScale" for replaying
lBumper.whileHeld(new RecordSubsystem("driveToScale",Robot.drivetrain););
//after recording is done, file is stored on roborio at a file path equal to '/home/Documents/PantherRobotics/ReplayRecord/' plus whatever the name of the function is, so
//this one would be /home/Documents/PantherRobotics/ReplayRecord/driveToScale

//set button to replay the entire path known as "driveToScale" on the drivetrain subsystem
rBumper.whenPressed(new ReplaySubsystem("driveToScale",Robot.drivetrain));
//	OR
//set button to replay the path while the button is held AND there is still path left to run (hopefully, untested)
rBumper.whileHeld(new ReplaySubsystem("driveToScale",Robot.driveTrain));


