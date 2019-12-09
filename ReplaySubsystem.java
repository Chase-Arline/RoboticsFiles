
import java.util.*;
import java.io.*;

public class ReplaySubsystem extends Command{
	private final ReplayFile file;
	private final Replayable replay;
	
	public ReplaySubsystem(String nameOfAction, ReplayableSubsystem replayController) throws FileNotFoundException{
		this.file = new ReplayFile("/home/Documents/PantherRobotics/ReplayRecord/"+nameOfAction);
		this.replay=replayController;
		requires(replayController);
	}
	
	protected void initialize(){
		if(isFinished()){
			cancel();
		}
	}	
	
	protected void execute(){
		double[] doubles = file.nextValues();
		replay.setReplayValues(doubles);
	}

	protected boolean isFinished(){
		return !file.hasNextLine();
	}
}
