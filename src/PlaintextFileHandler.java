import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class PlaintextFileHandler {

	private static int THREE_CHARS = 3;
	private String myFilename;
	private BufferedReader myBufferedReader = null;
	private BufferedWriter myBufferedWriter = null;
	
	public PlaintextFileHandler(String filename) {
		myFilename = filename;
	}
	
	public long readThreeMoreLetters() throws IOException {
		if(myBufferedReader == null) {
			myBufferedReader = new BufferedReader(new FileReader(myFilename));
		}
		char[] threeChars = new char[THREE_CHARS];
		return threeCharToLong(threeChars, myBufferedReader.read(threeChars, 0, THREE_CHARS));
	}
	
	public void close() {
		if(myBufferedReader != null) {
			try {
				myBufferedReader.close();
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
	
	private long threeCharToLong(char[] threeChar, int size) {
		if(size < 0) {
			return size;
		}
		
		long result = 0;
		
		for(int i = 0; i <size; i++) {
			result = (result * 100) + charToLong(threeChar[i]);
		}
		
		//shift over so its tailing spaces not leading spaces on the last number
		result *= Math.pow(100.0, 3.0 - size);
		
		return result;
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
		   case ',':
		     return Comma;

		   case '.':
		     return Period;

		   case ' ':
		     return Space;
		     
		   case '\n':
		   	 return NewLine;
		   	 
		   case '\r':
			   return CarriageReturn;

		   default:
		     throw new java.lang.IllegalArgumentException(
		         "Character must be upper case A-Z or { '\'', ':', ',', '.', '?', ' '} -- found: " + in);
		 }
	}
	
	public char longToChar(long in) {
		switch ( (int)in )
		 {
		   case 0:
		     return ' ';
		   case 1:
		     return 'A';
		   case 2:
		     return 'B';
		   case 3:
		     return 'C';
		   case 4:
		     return 'D';
		   case 5:
		     return 'E';
		   case 6:
		     return 'F';
		   case 7:
		     return 'G';
		   case 8:
		     return 'H';
		   case 9:
		     return 'I';
		   case 10:
		     return 'J';
		   case 11:
		     return 'K';
		   case 12:
		     return 'L';
		   case 13:
		     return 'M';
		   case 14:
		     return 'N';
		   case 15:
		     return 'O';
		   case 16:
		     return 'P';
		   case 17:
		     return 'Q';
		   case 18:
		     return 'R';
		   case 19:
		     return 'S';
		   case 20:
		     return 'T';
		   case 21:
		     return 'U';
		   case 22:
		     return 'V';
		   case 23:
		     return 'W';
		   case 24:
		     return 'X';
		   case 25:
		     return 'Y';
		   case 26:
		     return 'Z';
		   case 27:
		     return ',';
		   case 28:
		     return '.';
		   case 29:
		     return '\n';
		   case 30:
		     return '\r';

		   default:
		     throw new java.lang.IllegalArgumentException("Argument must be be on interval [0, 30]");
		   }
	}
	
	//Make up the letters that will be in the enumeration
	public static long Space = 0;
	public static long A = 1;
	public static long B = 2;
	public static long C = 3;
	public static long D = 4;
	public static long E = 5;
	public static long F = 6;
	public static long G = 7;
	public static long H = 8;
	public static long I = 9;
	public static long J = 10;
	public static long K = 11;
	public static long L = 12;
	public static long M = 13;
	public static long N = 14;
	public static long O = 15;
	public static long P = 16;
	public static long Q = 17;
	public static long R = 18;
	public static long S = 19;
	public static long T = 20;
	public static long U = 21;
	public static long V = 22;
	public static long W = 23;
	public static long X = 24;
	public static long Y = 25;
	public static long Z = 26;
	public static long Comma = 27;
	public static long Period = 28;
	public static long NewLine = 29;
	public static long CarriageReturn = 30;

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
