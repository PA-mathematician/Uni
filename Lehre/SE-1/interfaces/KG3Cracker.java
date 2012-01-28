public class KG3Cracker implements Cracker {
	private Secret secret;
	private int[] combination = new int[4];
	private int oldCorrectDigits = 0;
	private int correctDigits = 0;
	private int value = 0; // debugging value --> InvalidDigitException
	private int position = 0;

	/**
	 * TODO dok me
	 */
	public void setSecret(Secret secret) {
		this.secret = secret;
	}

	public int[] crack() {
		try
		{
			secret.unlock(combination);
		}
		catch(TooFewDigitsException e) // add a position to the lock until it matches the locks positions
		{
			combination = new int[combination.length+1];
			for(int i=0; i<combination.length-1; i++)
			{
				combination[i+1] = combination[i];
			}
			combination[0] = 0;
			return crack();
		}
		catch(TooManyDigitsException e) // removes a position from the lock until it matches the locks positions
		{
			System.out.println("Zu viele Stellen. Verringere...");
			combination = new int[combination.length-1];
			for(int i=0; i<combination.length; i++)
			{
				if(i == 0)
				{
					combination[i] = combination[i];
				}
				else
				{
					combination[i-1] = combination[i];
				}
			}
			return crack();
		}
		catch(InvalidDigitException e)
		{
			System.out.println("Invalid value for the lock. Caused by " + value);
		}
		catch(WrongCombinationException e)
		{
			oldCorrectDigits = correctDigits;
			correctDigits = e.correctPlace();
			
			if(correctDigits > oldCorrectDigits) // if the current correct places are higher, we got a correct value -> step to the next position
			{
				value = 0;
				position++;
				return crack();
			}
			else if(correctDigits < oldCorrectDigits) // if the current correct places are smaller, we overextended a value that was correct -> reduce this value
			{
				combination[position]--;
				return crack();
			}
			else // if the current and the old correct places match increase the value at the current position
			{
				combination[position]++;
				value++;
				return crack();
			}
		}
		
		System.out.println("*KLICK* Lockpicking succesfull. Combination is:\n\n===================");		
		for(int i=0; i<combination.length; i++)
		{
		System.out.print(combination[i] + " ");
		}
		System.out.println("\n===================");
		return combination;

	}


}