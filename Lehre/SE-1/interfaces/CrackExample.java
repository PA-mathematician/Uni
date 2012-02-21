/**
 * Beispielanwendung die den Sinn von Interfaces motivieren soll.
 * In Anlehnung an Blatt 12, Aufgabe 2 soll hier ein secret geknackt werden,
 * dabei wird eine Auswahl an verschiedenen Möglichkeiten zum Knacken gegeben,
 * wobei auf die Funktionalität nur durch Interfaces zugegriffen wird.
 */
public class CrackExample {
	
	public static void main(String[] args) {
		// Fabrik einstellen
		chooseCracker();

		// Wichtige Objekte erzeugen
		Secret secret = buildSecret();
		Cracker cracker = CrackFactory.buildCracker();
		cracker.setSecret(secret);

		IO.println("[CrackExample] Starte cracken");

		int[] combination = cracker.crack();

		// Erfolg verkünden
		IO.println("\n[CrackExample] gecrackt. Die Schlossnummer war:");
		for (int i = 0; i < combination.length; i++) {
			IO.print("'" + combination[i] + "'");
		}
		IO.println("");
		IO.println("[CrackExample] Versuche benötigt: " + secret.accessCount());
	}

	/**
	 * Helfer, der vom Benutzer eine Zahl abfragt
	 * und ggf. damit ein neues Secret erzeugt.
	 */
	private static Secret buildSecret() {
		IO.print("[CrackExample] Falls ein genaues Schloss erzeugt werden soll, eine Zahl >0 eingeben, ansonsten 0: ");
		int secretNo = IO.readInt();

		if (secretNo != 0) {
			IO.println("[CrackExample] Erzeuge neues Schloss mit " + secretNo + " ");
			return new Secret(secretNo);
		}
		IO.println("[CrackExample] Erzeuge neues Schloss");
		return new Secret();
	}


	/**
	 * Noch ein Helfer, zeigt, was die Fabrik kann und lässt den
	 * Benutzer die konkrete Cracker-Implementierung auswählen.
	 */
	private static void chooseCracker() {
		IO.println("[CrackFactory] Aus folgenden Cracker-Implementierungen eine auswählen:");
		for(int i = 0; i < CrackFactory.getAvailableCrackers().length; i++) {
			IO.println("  [" + i + "] " + CrackFactory.getAvailableCrackers()[i].getName());
		}
		while (CrackFactory.getCrackTool() < 0 || CrackFactory.getCrackTool() >= CrackFactory.getAvailableCrackers().length) {
			IO.print("[CrackFactory] Nummer eingeben: ");
			CrackFactory.setCrackTool(IO.readInt());
		}
	}
}