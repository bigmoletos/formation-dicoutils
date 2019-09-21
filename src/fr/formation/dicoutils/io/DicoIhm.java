package fr.formation.dicoutils.io;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class DicoIhm {
	
	/**
	 * Déclaration du logger associé à cette classe.
	 */
	private static final Logger LOGGER = Logger.getLogger(DicoIhm.class);
	
	private final Scanner scanner;

	public DicoIhm() {
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * Méthode permettant d'afficher le menu.
	 */
	public void displayMenu() {
		this.display("Menu :");
		this.display("\tA - Chercher un mot exact");
		this.display("\tB - Chercher par le début d'un mot");
		this.display("\tC - Chercher par la fin d'un mot");
		this.display("\tD - Chercher par le contenu d'un mot");
		this.display("\tE - Chercher par expression régluière");
		this.display("\texit - Quitter l'application");
	}
	
	public void display(String message) {
		// System.out.println(message);
		DicoIhm.LOGGER.info(message);
	}
	
	public void displayResults(String[] results) {
		this.display("Résultats trouvés :");
		for (String result : results) {
			this.display("\t- " + result);
		}
	}
	
	/**
	 * Méthode permettant de lire une saisie utilisateur.
	 */
	public String readAction() {
		String result = null;
		while (result == null) {
			String input = this.scanner.nextLine();
			if (input.length() > 0 && input.matches("A|B|C|D|E|exit")) {
				result = input;
			} else {
				this.display("L'action saisie n'est pas valide, "
						+ "veuillez choisir une action du menu.");
			}
		}
		return result;
	}
	
	/**
	 * Méthode de récupération d'une saisie utilisateur.
	 * 
	 * @param label le message a afficher avant de demander la saisie.
	 * @return String la saisie utilisateur.
	 */
	public String readInput(String label) {
		this.display(label);
		return this.scanner.nextLine();
	}
}
