import java.util.*;
import java.io.*;

public class ReplayFile extends File {
	private final BufferedWriter wr;
	private final Scanner in;

	public ReplayFile(String fileLoc) throws IOException, FileNotFoundException{
		super(fileLoc);
		this.wr = new BufferedWriter(new FileWriter(this));
		if (!this.exists()){
			throw new FileNotFoundException();
		}
		in = new Scanner(this);
	}
	
	public double[] nextValues(){
		String[] vals=in.nextLine().trim().split(" ");
		double[] doubles = new double[vals.length];
		for(int i=0;i<vals.length;i++){
			doubles[i]=Double.parseDouble(vals[i]);
		}
		return doubles;
	}
	
	public void writeValues(double[] vals) throws IOException{
		for(double val:vals){
			wr.write(String.valueOf(val));	
			wr.write(" ");
		}
		wr.newLine();
		wr.flush();
	}	
	//Removes a piece of the file, determined by the two percents given. i.e. if 20 and 50 are given, files lines not written inside of the 20-50% range of the file will be removed. 
	//This can be useful for editing last parts of a replay that may have deadstops (the user stopped moving the robot before ending recording)
	public void editFile(int prePercent, int postPercent) throws IOException{
		Scanner newScan = new Scanner(this);			
		int lines = 0;
		Queue<String> relevantLines = new LinkedList<>();
		//count number of lines in file
		while (newScan.hasNextLine()){ newScan.nextLine(); lines++; }
		//skip to the relevant line start		
		for(int i=0;i<prePercent*lines/100&&newScan.hasNextLine();i++){
			in.nextLine();
		}
		for(int i=0;i<postPercent*lines/100&&newScan.hasNextLine();i++){
			relevantLines.add(newScan.nextLine());
		}
		FileWriter fw = new FileWriter(this.getPath(),false);
		while (!relevantLines.isEmpty()) fw.write(relevantLines.poll());
		fw.flush();
		fw.close();
	}	

	public void close() throws IOException {
		wr.flush();
		wr.close();
	}

	public boolean hasNextLine(){
		return in.hasNextLine();
	}
}
