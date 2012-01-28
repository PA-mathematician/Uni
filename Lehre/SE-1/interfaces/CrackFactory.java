/**
 * Die CrackFactory ist eigentlich nur dafür da, einen UncleCracker zu erstellen.
 * Welche konkreten Cracker zur Verfügung stehen, steht hier im statischen Array
 * availableCrackers. Man könnte das natürlich auch in eine externe Config-Datei
 * auslagern, aber darauf kommt es hier ja nicht an ;)
 *
 * Angenommen, du willst eine neue Variante eines Crackers implementieren,
 * schreibe eine neue Klasse MeinCracker (o.ä.) und füge zum Array noch
 * MeinCracker.class hinzu - das Komma nicht vergessen!
 * Beim nächsten Programmstart steht es dann auch zur Verfügung.
 */
public class CrackFactory {
	private static int crackTool = -1;
	private static Class[] availableCrackers = {
		KG1Cracker.class,
		KG2Cracker.class,
		KG3Cracker.class,
		KG4Cracker.class,
		KG5Cracker.class
	};

	/**
	 * Instantiiert einen konkreten Cracker anhand des statischen
	 * Attributes crackTool. Wie das genau passiert, ist erstmal nicht wichtig;
	 * nach der Klausur kannst du hier schauen:
	 * http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html
	 *
	 * Beachte, dass hier der Typ "Cracker" zurückgegeben wird
	 */
	public static Cracker buildCracker() {
		IO.println("[CrackFactory] Erstelle neuen " + availableCrackers[crackTool].getName());
		
		try {
			return (Cracker) availableCrackers[crackTool].newInstance();
		} catch (IllegalAccessException e) {
			System.err.println("The choosen Cracker doesn't provide a default constructor, but I need one.");
		} catch (InstantiationException e) {
			System.err.println("The choosen Cracker isn't a instantiatable Class at all.");
		}
		return null;
	}

	// Die drei sind echt nur langweilige Getter und Setter ;)

	// Meta-Aufgabe:
	//  * Warum hab ich die als Getter/Setter implementiert?
	//  * Was könnte hier zu einem Problem werden? (Denke auch an Erweiterungen des Programms)

	public static void setCrackTool(int ct) {
		crackTool = ct;
	}

	public static int getCrackTool() {
		return crackTool;
	}

	public static Class[] getAvailableCrackers() {
		return availableCrackers;
	}

}