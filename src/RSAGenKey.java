import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSAGenKey {

	public static void main(String[] args) {
		int bits = 0;
		BigInteger p = null;
		BigInteger q = null;
		BigInteger e = null; 
		if(args.length == 1) {
			bits = Integer.parseInt(args[0]);
		
			p = BigInteger.probablePrime(bits, new Random());
			q = BigInteger.probablePrime(bits, new Random());
		}
		else if(args.length == 3) {
			p = new BigInteger(args[0]);
			q = new BigInteger(args[1]);
			e = new BigInteger(args[2]);
			
		} else {
			System.err.println("Need one or three arguments!");
			System.exit(-1);
		}
		
		BigInteger n = p.multiply(q);
		BigInteger O = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		if(e == null)
			e = findE(O);
		
		BigInteger d = e.modInverse(O);
		
		try {
			createKeyFiles(e,d,n);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	private static void createKeyFiles(BigInteger e, BigInteger d, BigInteger n) throws IOException {
		BufferedWriter pubWriter = new BufferedWriter(new FileWriter("pub_key.txt"));
		
		pubWriter.write("e = " + e.toString());
		pubWriter.newLine();
		pubWriter.write("n = " + n.toString());
		pubWriter.newLine();
		pubWriter.close();
		
		BufferedWriter priWriter = new BufferedWriter(new FileWriter("pri_key.txt"));
		priWriter.write("d = " + d.toString());
		priWriter.newLine();
		priWriter.write("n = " + n.toString());
		priWriter.newLine();
		priWriter.close();
		
	}

	private static BigInteger findE(BigInteger o) {
		BigInteger eCanidate = new BigInteger(o.bitLength(), new Random());
		
		while(eCanidate.compareTo(o) >= 0 || !eCanidate.gcd(o).equals(BigInteger.ONE)) {
			eCanidate = new BigInteger(o.bitLength(), new Random()); 
		}
		
		return eCanidate;
	}
}
