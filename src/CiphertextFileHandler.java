import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class CiphertextFileHandler {
	private String myFilename;
	private BufferedWriter myBufferedWriter = null;
	private Scanner myScanner = null;
	
	public CiphertextFileHandler(String filename) {
		myFilename = filename;
	}
	
	public void writeNextBigInt(BigInteger in) throws IOException {
		if(myBufferedWriter == null) {
			myBufferedWriter = new BufferedWriter(new FileWriter(myFilename));
		}
		
		myBufferedWriter.write(in.toString() + " ");
	}
	
	public void close() throws IOException {
		if(myBufferedWriter != null) {
			try {
				myBufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(myScanner != null) {
			myScanner.close();
		}
	}

	public BigInteger readNextBigInt() throws IOException {
		if(myScanner == null) {
			myScanner = new Scanner(new File(myFilename));
			
		}
		
		if(myScanner.hasNextBigInteger()) {
			return myScanner.nextBigInteger();
		}
		else
			return null;
	}
}
