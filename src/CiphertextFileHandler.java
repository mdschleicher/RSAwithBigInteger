import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class CiphertextFileHandler {
	private String myFilename;
	private BufferedWriter myBufferedWriter = null;
	
	public CiphertextFileHandler(String filename) {
		myFilename = filename;
	}
	
	public void writeNextBigInt(BigInteger in) throws IOException {
		if(myBufferedWriter == null) {
			myBufferedWriter = new BufferedWriter(new FileWriter(myFilename));
		}
		
		myBufferedWriter.write(in.toString());
		myBufferedWriter.newLine();
	}
	
	public void close() {
		if(myBufferedWriter != null) {
			try {
				myBufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
