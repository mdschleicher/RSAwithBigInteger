import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class CiphertextFileHandler {
	private String myFilename;
	private BufferedWriter myBufferedWriter = null;
	private BufferedReader myBufferedReader = null;
	
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
		if(myBufferedReader != null) {
			try {
				myBufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public BigInteger readNextBigInt() throws IOException {
		if(myBufferedReader == null) {
			myBufferedReader = new BufferedReader(new FileReader(myFilename));
		}
		
		String currentLine = myBufferedReader.readLine();
		
		if(currentLine == null) {
			return null;
		}
		
		return new BigInteger(currentLine);
	}
}
