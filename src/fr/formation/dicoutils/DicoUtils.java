package fr.formation.dicoutils;

import java.util.Arrays;

import org.apache.log4j.Logger;

import fr.formation.dicoutils.io.ClasspathDicoLoader;
import fr.formation.dicoutils.io.DicoIhm;
import fr.formation.dicoutils.io.DicoLoader;

/**
 * Classe principale de l'application comportant le point d'entrée (méthode
 * statique main) et la méthode d'exécution des traitements (run).
 */
public class DicoUtils implements Runnable {

	/**
	 * Déclaration du logger associé à cette classe.
	 */
	private static final Logger LOGGER = Logger.getLogger(DicoUtils.class);

	/**
	 * Point d'entrée de l'application. Création d'une instance de DicoUtils et
	 * appel de la méthode qui lance les traitements. Le fait de créer une instance
	 * de la classe dans laquel se trouve le point d'entrée est une convention
	 * souvent utilisée. Le but est de passer de la méthode statique limitante à une
	 * instance permettant d'utiliser les membres de classe.
	 * 
	 * @param args les arguments du programme.
	 */
	public static void main(String[] args) {
		DicoUtils.LOGGER.debug("Lancement du programme !");
		new DicoUtils().run();
	}

	private final DicoLoader loader;
	private final DicoIhm ihm;
	private DicoSearch search;

	public DicoUtils() {
		this.ihm = new DicoIhm();
		this.loader = new ClasspathDicoLoader();
//		this.loader = new HardDriveDicoLoader();
	}

	/**
	 * Lancement de l'algorithme principal permettant à l'utilisateur d'effectuer
	 * autant de recherches qu'il veut avant de quitter.
	 */
	@Override
	public void run() {
		DicoUtils.LOGGER.debug("Exécution de la méthode run !");
		String action = null;
		String[] words = this.readDico();
//		this.search = new ArrayDicoSearch(words);
		this.search = new CollectionDicoSearch(Arrays.asList(words));
		DicoUtils.LOGGER.debug("Nombre de mots dans le dictionnaire : " + words.length);
		while (!"exit".equals(action)) {
			this.ihm.displayMenu();
			action = this.ihm.readAction();
			switch (action) {
			case "A":
				String word = this.ihm.readInput("Saisir un mot exact : ");
				if (this.search.findExact(word)) {
					this.ihm.display("Le mot est bien dans le dico !");
				} else {
					this.ihm.display("Désolé, le mot '" + word + "' n'est pas dans le dico.");
				}
				break;
			case "B":
				String prefix = this.ihm.readInput("Saisir un préfix de mot : ");
				String[] results = this.search.findByPrefix(prefix);
				this.handleResults(results);
				break;
			case "C":
				String suffix = this.ihm.readInput("Saisir un suffixe de mot : ");
				this.handleResults(this.search.findBySuffix(suffix));
				break;
			case "D":
				String keyword = this.ihm.readInput("Saisir une chaîne : ");
				this.handleResults(this.search.findByContains(keyword));
				break;
			case "E":
				String regex = this.ihm.readInput("Saisir une expression régulière : ");
				this.handleResults(this.search.findByRegex(regex));
				break;
			case "exit":
				break;
			default:
				DicoUtils.LOGGER.error("L'action '" + action + "' n'est pas reconnue.");
			}
		}
	}

	/**
	 * Chargement du fichier dictionnaire contenu dans l'application
	 * (src/dictionnaire.txt). Pour accéder à un fichier qui se trouve avec les
	 * classes Java, il faut utiliser le Classpath. Doc ->
	 * https://fr.wikipedia.org/wiki/Chargeur_de_classe_Java
	 * 
	 * @return un tableau de tous les mots lus dans le fichier.
	 */
	private String[] readDico() {
		return this.loader.loadFile("./dictionnaire.txt");
	}

	/**
	 * Méthode interne à la classe (visibilité privée) et commune aux différentes
	 * méthodes de recherche.
	 * 
	 * @param results les résultats de la recherche.
	 */
	private void handleResults(String[] results) {
		if (results.length > 0) {
			this.ihm.displayResults(results);
		} else {
			this.ihm.display("Aucun résultat trouvé.");
		}
	}

}
