package HW1;

// This is a class used to create new UPC codes to test
// It does not need to be coded by students or even given to them

public class UPCcreator {


	public static void main(String[] args) {
		
		// The number I want in the bar code
		String number = "123456789012";
		
		String[] numPatL = new String[10];
		numPatL[0] = "0001101";
		numPatL[1] = "0011001";
		numPatL[2] = "0010011";
		numPatL[3] = "0111101";
		numPatL[4] = "0100011";
		numPatL[5] = "0110001";
		numPatL[6] = "0101111";
		numPatL[7] = "0111011";
		numPatL[8] = "0110111";
		numPatL[9] = "0001011";

		// Create the inverses for the right side
		String[] numPatR = new String[10];
		for (int i=0; i<numPatL.length; i++) {

			numPatR[i] = "";
			for (int j=0; j<numPatL[i].length(); j++) {
				if (numPatL[i].substring(j, j+1).compareTo("0") == 0) {
					numPatR[i] = numPatR[i] + 1;
				} else {
					numPatR[i] = numPatR[i] + 0;
				}
				
			}
			//System.out.println(numPatL[i] + "\n" + numPatR[i] + "\n");
		}
		
		
		String pattern = "101";
		
		// Form the 0/1 string
		for (int i=0; i<6; i++) {
			int index = number.charAt(i) - '0';
			pattern += numPatL[index];
		}
		
		pattern += "01010";
	
		for (int i=6; i<12; i++) {
			int index = number.charAt(i) - '0';
			pattern += numPatR[index];
		}
		
		pattern += "101";
		
		
		System.out.println(pattern);
		
		
		// If I want to reverse it...
//		String test = "";
//		for (int i=0; i<pattern.length(); i++) {
//			test = pattern.substring(i, i+1) + test;
//		}
		//System.out.println(test);
		//pattern = test;
		
		// There should be 95 0/1 in the pattern
		
		// Now create the barcode image
		DUImage barCode = new DUImage(200, 100);
		
		
		// Each bar can be 1,2,3 or 4 times the xdim (we can set this) - this is 
		//   called the module width
		int moduleWidth = 2;
		
		// The start, end and guard patterns extend 5x the xdim lower than the other bars
		// The first and last digits also extend this same distance down
		// Not sure if this 5x includes the module width as well - would seem like it should...
		
		int barXoffset = 5;
		for (int i=0; i<pattern.length(); i++) {
		
			// Select the bar color
			int barColor = 0;
			if (pattern.charAt(i) == '0') {
				barColor = 255;  // 0s are white
			} else {
				barColor = 0;    // 1s are black
			}
		
			// Make the bar
			int barYoffset = 5;
			int barLength;
			
			// Make the bar longer for the guards and the first/last character
			barLength = 95;
			if (!((i<10) || (i>=45 && i<50) || (i >= 85))) {
				
				// Commented out at the moment
				// It looks better with the little blocks sticking down, but
				// it then makes it obvious if it is upside down or not and I want
				// that to be something they need to figure out in their code.
				//barLength = 95 - (xdim * moduleWidth * 5);
			}
		
			for (int z=0; z<moduleWidth; z++) {
				for (int y=0; y<barLength; y++) {
					barCode.setRGB((i*moduleWidth)+barXoffset+z, y+barYoffset, barColor, barColor, barColor);
				}
			}
			
		}
		
		barCode.write("barcode1Mutation.png");
		

	}

}

