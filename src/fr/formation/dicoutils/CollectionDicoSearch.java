package fr.formation.dicoutils;

import java.util.ArrayList;
import java.util.List;

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
}
