import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class RSADecrypt {

	private static BigInteger n;
	private static BigInteger d;
	
	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("Needs two arguments.");
			System.exit(-1);
		}
		
		try {
			readKeyFile(args[1]);
		} catch (IOException e) {
			System.err.println("Error reading key file.");
			System.exit(-1);
		}
		
		CiphertextFileHandler input = new CiphertextFileHandler(args[0]);
		PlaintextFileHandler output = new PlaintextFileHandler(args[0].split("\\.")[0] + ".dec");
		
		try {
			BigInteger current = null;
			while((current = input.readNextBigInt()) != null)  {
				output.writeNextLong(current.modPow(d,n).longValue());
			}
			
			input.close();
			output.close();
		} catch (IOException e) {
			System.err.println("Error writing plaintext or reading ciphertext.");
			System.exit(-1);
		}
		
		
	}
	
	public static void readKeyFile(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		
		String[] splitLine = in.readLine().split(" ");
		if(splitLine[0].equals("d")) {
			d = new BigInteger(splitLine[2]);
		} else {
			System.err.println("Invalid key file, did not find d.");
			System.exit(-1);
		}
		
		splitLine = in.readLine().split(" ");
		if(splitLine[0].equals("n")) {
			n = new BigInteger(splitLine[2]);
		} else {
			System.err.println("Invalid key file, did not find n.");
			System.exit(-1);
		}
		
		in.close();
	}
}
