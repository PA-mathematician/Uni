/**
 * Ein Cracker muss eigentlich nur wirklich cracken können.
 * Ansonsten wird dieses Interface als Schnittstelle* benutzt, mit dem zwischen
 * CrackExample, der CrackFactory und den konkreten Crackern "kommuniziert" wird.
 * 
 * Wenn du dir den Code in den genannten Klassen anschaust, wirst du merken:
 * In CrackExample wird nur auf Cracker, aber nicht z.B. auf KG1Cracker zugegriffen!
 *
 * Frage: Wie bzw. wann wird hier kommuniziert?
 */
public interface Cracker {

	/**
	 * Das secret, das geknackt werden soll, wird hier gesetzt.
	 * Das ist nötig, weil ein Interface in Java keine Anforderungen
	 * an den Konstruktor der instantiierenden Klasse stellen darf.
	 * 
	 * Frage: Warum nicht?
	 */
	public void setSecret(Secret secret);

	/**
	 * Das ist die Methode, die letztendlich das zuvor gesetzte secret knacken soll.
	 * Als Antwort soll das geknackte secret zurückgegeben werden.
	 */
	public int[] crack();
}




// * = sorry, das musste sein^^