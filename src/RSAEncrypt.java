import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class RSAEncrypt {

	private static BigInteger n;
	private static BigInteger e;
	
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
		
		PlaintextFileHandler input = new PlaintextFileHandler(args[0]);
		CiphertextFileHandler output = new CiphertextFileHandler(args[0].split("\\.")[0] + ".enc");
		
		long currentNumber = 0;
		try {
			while((currentNumber = input.readThreeMoreLetters()) != -1) {
				BigInteger currentBigInt = BigInteger.valueOf(currentNumber);
				output.writeNextBigInt(currentBigInt.modPow(e,n));
			}
			
			input.close();
			output.close();
		} catch (IOException e) {
			System.err.println("Error reading plaintext or writing ciphertext.");
			System.exit(-1);
		}
		
		System.out.println("Success! See " + args[0].split("\\.")[0] + ".enc" + " for the encrypted message.");
	}
	
	public static void readKeyFile(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		
		String[] splitLine = in.readLine().split(" ");
		if(splitLine[0].equals("e")) {
			e = new BigInteger(splitLine[2]);
		} else {
			System.err.println("Invalid key file, did not find e.");
			System.exit(-1);
		}
		
		splitLine = in.readLine().split(" ");
		if(splitLine[0].equals("n")) {
			n = new BigInteger(splitLine[2]);
			if(n.compareTo(BigInteger.valueOf((long)262626)) <= 0) {
				System.err.println("n is not greater than max encoded value. Please provide a different key file");
				System.exit(-1);
			}
		} else {
			System.err.println("Invalid key file, did not find n.");
			System.exit(-1);
		}
		
		in.close();
	}
}
