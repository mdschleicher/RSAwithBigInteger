import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlaintextFileHandler {

	private static int THREE_CHARS = 3;
	private String myFilename;
	private FileReader myFileReader = null;
	private BufferedWriter myBufferedWriter = null;
	
	public PlaintextFileHandler(String filename) {
		myFilename = filename;
	}
	
	public long readThreeMoreLetters() throws IOException {
		if(myFileReader == null) {
			myFileReader = new FileReader(myFilename);
		}
		
		long result = 0;
		int currentChar = -1;
		for(int i = 0; i <THREE_CHARS; i++) {
			
			currentChar = myFileReader.read();
			
			if(currentChar == -1) {
				if(i == 0)
					return -1;
				
				//shift it over so we have trailing extra spaces instead of leading extra space
				for(;i <THREE_CHARS; i++) {
					result = (result * 100) + Space;
				}
				return result;
			}
			
			long currentResult = charToLong((char)currentChar);
			
			//skip any non A-Z or Space
			while(currentResult == -1) {
				currentChar = myFileReader.read();
				
				if(currentChar == -1) {
					//shift it over so we have trailing extra spaces instead of leading extra space
					for(;i <THREE_CHARS; i++) {
						result = (result * 100) + Space;
					}
					return result;
				}
				
				currentResult = charToLong((char)currentChar);
			}
			
			result = (result * 100) + charToLong((char)currentChar);
		}
		
		return result;
	}
	
	public void close() {
		if(myFileReader != null) {
			try {
				myFileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(myBufferedWriter != null) {
			try {
				myBufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private long charToLong(char in) {
		 // substitute the old char for our CASCII value
		 switch ( Character.toUpperCase(in) )
		 {
		   case 'A':
		     return A;

		   case 'B':
		     return B;

		   case 'C':
		     return C;

		   case 'D':
		     return D;

		   case 'E':
		     return E;

		   case 'F':
		     return F;

		   case 'G':
		     return G;

		   case 'H':
		     return H;

		   case 'I':
		     return I;

		   case 'J':
		     return J;

		   case 'K':
		     return K;

		   case 'L':
		     return L;

		   case 'M':
		     return M;

		   case 'N':
		     return N;

		   case 'O':
		     return O;

		   case 'P':
		     return P;

		   case 'Q':
		     return Q;

		   case 'R':
		     return R;

		   case 'S':
		     return S;

		   case 'T':
		     return T;

		   case 'U':
		     return U;

		   case 'V':
		     return V;

		   case 'W':
		     return W;

		   case 'X':
		     return X;

		   case 'Y':
		     return Y;

		   case 'Z':
		     return Z;
		     
		   case ' ':
			 return Space;

		   default:
		     return -1;
		 }
	}
	
	public char longToChar(long in) {
		switch ( (int)in )
		 {
		   case 0:
		     return 'A';
		   case 1:
		     return 'B';
		   case 2:
		     return 'C';
		   case 3:
		     return 'D';
		   case 4:
		     return 'E';
		   case 5:
		     return 'F';
		   case 6:
		     return 'G';
		   case 7:
		     return 'H';
		   case 8:
		     return 'I';
		   case 9:
		     return 'J';
		   case 10:
		     return 'K';
		   case 11:
		     return 'L';
		   case 12:
		     return 'M';
		   case 13:
		     return 'N';
		   case 14:
		     return 'O';
		   case 15:
		     return 'P';
		   case 16:
		     return 'Q';
		   case 17:
		     return 'R';
		   case 18:
		     return 'S';
		   case 19:
		     return 'T';
		   case 20:
		     return 'U';
		   case 21:
		     return 'V';
		   case 22:
		     return 'W';
		   case 23:
		     return 'X';
		   case 24:
		     return 'Y';
		   case 25:
		     return 'Z';
		   case 26:
			 return ' ';


		   default:
		     throw new java.lang.IllegalArgumentException("Argument must be be on interval [0, 26]");
		   }
	}
	
	//Make up the letters that will be in the enumeration
	public static long A = 0;
	public static long B = 1;
	public static long C = 2;
	public static long D = 3;
	public static long E = 4;
	public static long F = 5;
	public static long G = 6;
	public static long H = 7;
	public static long I = 8;
	public static long J = 9;
	public static long K = 10;
	public static long L = 11;
	public static long M = 12;
	public static long N = 13;
	public static long O = 14;
	public static long P = 15;
	public static long Q = 16;
	public static long R = 17;
	public static long S = 18;
	public static long T = 19;
	public static long U = 20;
	public static long V = 21;
	public static long W = 22;
	public static long X = 23;
	public static long Y = 24;
	public static long Z = 25;
	public static long Space = 26;

	public void writeNextLong(long in) throws IOException {
		if(myBufferedWriter == null) {
			myBufferedWriter = new BufferedWriter(new FileWriter(myFilename));
		}
		//start at 2, then 1, then 0, then break
		for(int i = THREE_CHARS - 1; i >= 0; i--) {
			long currentShift = (long) Math.pow(100.0, (double) i);
			long current = in / currentShift;
			in %= currentShift;
			
			myBufferedWriter.write(longToChar(current));
		}
		
	}
}
