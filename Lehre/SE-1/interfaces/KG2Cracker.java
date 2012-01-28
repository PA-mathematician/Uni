public class KG2Cracker implements Cracker {
	private Secret secret;

	public void setSecret(Secret secret) {
		this.secret = secret;
	}
	
	private int keyLaenge(Secret s) {
		int[] testkey = new int[3];
		boolean richtigeLaenge = false;
		while(!richtigeLaenge){
			try{s.unlock(testkey);
				richtigeLaenge = true; 
				}
			catch(TooFewDigitsException m){ 
				richtigeLaenge = false;
				testkey = new int[testkey.length+1];
				}
			catch(TooManyDigitsException m){ 
				richtigeLaenge = true;
				testkey = new int[testkey.length-1];
				}
			catch(InvalidDigitException m) {
				richtigeLaenge = true;
				}
			catch(WrongCombinationException combination) {
				richtigeLaenge = true;
				};
			
			}
		return testkey.length;
	}

	public int[] crack(){
		
		int[] testkey = new int[keyLaenge(this.secret)];
		for(int counter=0; counter!=keyLaenge(this.secret); counter++) {
			int digicounter=0,testcounter=0;
			while(digicounter!=(counter+1)){
				testkey[counter]=testcounter;
				try{this.secret.unlock(testkey);}
				catch(TooFewDigitsException m){}
				catch(TooManyDigitsException m){}
				catch(InvalidDigitException m) {}
				catch(WrongCombinationException combination) {
					digicounter = combination.correctPlace();
					}
				testcounter++;
			}
		}
		return testkey;
	}

}