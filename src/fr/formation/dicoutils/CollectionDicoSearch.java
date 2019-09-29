package fr.formation.dicoutils;

import java.util.ArrayList;
import java.util.List;

import fr.formation.dicoutils.io.DicoIhm;

/**
 * Implémentation des méthodes de recherche avec une collection de mots
 * représentant le dictionnaire.
 */
public class CollectionDicoSearch implements DicoSearch {

	/**
	 * Dictionnaire mémorisé pour éviter de le passer en argument à chaque méthode
	 * de recherche. Le type utilisé est une collection java pour utiliser les
	 * méthodes utiles facilitant la recherche.
	 */
	private final List<String> words;
	DicoIhm readClavier = new DicoIhm();

	public CollectionDicoSearch(List<String> words) {
		this.words = words;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean findExact(String word) {
		return this.words.contains(word);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findByPrefix(String prefix) {
		List<String> results = new ArrayList<>();
		for (String dicoWord : this.words) {
			if (dicoWord.startsWith(prefix)) {
				results.add(dicoWord);
			}
		}
		// Transformation de collection en tableau, il faut donner le type à utiliser
		// pour le tableau avec un argument. L'argument utilisé est toujours un tableau
		// de taille 0 pour passer uniquement l'information du type d'élément à
		// utiliser.
		return results.toArray(new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findBySuffix(String suffix) {
		List<String> results = new ArrayList<>();
		for (String dicoWord : this.words) {
			if (dicoWord.endsWith(suffix)) {
				results.add(dicoWord);
			}
		}
		return results.toArray(new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findByContains(String word) {
		List<String> results = new ArrayList<>();
		for (String dicoWord : this.words) {
			if (dicoWord.contains(word)) {
				results.add(dicoWord);
			}
		}
		return results.toArray(new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findByRegex(String expression) {
		List<String> results = new ArrayList<>();
		for (String dicoWord : this.words) {
			if (dicoWord.matches(expression)) {
				results.add(dicoWord);
			}
		}
		return results.toArray(new String[0]);
	}

	/**
	 * {@inheritDoc} by franck
	 */
	@Override
	public String[] findByAll(String expression) {
		if (readClavier.readAction() == "A") {
//			contient seulement le mot
			expression = "^" + expression + "$";
			System.out.println("regex: " + expression);
//			return regex;
		} else if (readClavier.readAction() == "B") {
			// commence par
			expression = "^" + expression;
			System.out.println("regex: " + expression);
//			return regex;
		} else if (readClavier.readAction() == "C") {
			// termine par
			expression = expression + "$";
			System.out.println("regex: " + expression);
//			return regex;
		} else if (readClavier.readAction() == "D") {
			// contient les valeurs
			expression = (expression) + "+";
			System.out.println("regex: " + expression);
//			return regex;
		} else if (readClavier.readAction() == "exit") {
			// contient les valeurs
//			return regex;
		}
		List<String> results = new ArrayList<>();
		for (String dicoWord : this.words) {
			if (dicoWord.matches(expression)) {
				results.add(dicoWord);
			}
		}
		return results.toArray(new String[0]);
	}
}
