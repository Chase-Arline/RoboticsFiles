import java.io.*;
import java.util.*;

public class RecordSubsystem extends Command{
	private final ReplayFile file;	
	private int ms; 
	private final Replayable record;
 
	//Used for recording for a finite amount of time
	public RecordSubsystem(String nameOfAction, int ms, ReplayableSubsystem recordSource){
		this.file = new ReplayFile("/home/Documents/PantherRobotics/ReplayRecord/"+nameOfAction);
	 	this.ms=ms;
	 	this.record=recordSource;
	}
 
	//Used for recording for an infinite amount of time
	public RecordSubsystem(String nameOfAction, Replayable recordSource){
		this.file = new ReplayFile("/home/Documents/PantherRobotics/ReplayRecord/"+nameOfAction);
		this.ms=-1;
	 	this.record=recordSource;
	}
	
	protected void execute(){
	 	double[] vals = record.getRecordValues();
		file.writeValues(vals);
	 	ms=ms-20;
	}
	
	protected boolean isFinished(){
 		return ms<20 && ms!=-1;
	}
 
	protected void end(){
		file.close();
	}
}

