package fr.formation.dicoutils;

/**
 * Implémentation des méthodes de recherche avec un tableau de mots représentant
 * le dictionnaire.
 */
public class ArrayDicoSearch implements DicoSearch {

	/**
	 * Dictionnaire mémorisé pour éviter de le passer en argument à chaque méthode
	 * de recherche. Le type utilisé est un tableau pour accentuer l'algorithmie
	 * utilisée pour les différentes recherches.
	 */
	private final String[] words;

	public ArrayDicoSearch(String[] words) {
		this.words = words;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean findExact(String word) {
		// Le compilateur java nous force à toujours initialiser les variables locales,
		// même si les types primitifs ont une valeur par défaut. C'est une "sécurité"
		// supplémentaire impliquant que vous avez réfléchi à votre valeur initiale
		// avant de lire votre variable.
		boolean found = false;
		for (String dicoWord : this.words) {
			if (dicoWord.equals(word)) {
				found = true;
				// Si le mot est trouvé, pas besoin de parcourir le reste des mots du
				// dictionnaire. On sort immédiatement de la boucle avec l'instruction break.
				break;
			}
		}
		return found;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findByPrefix(String prefix) {
		String results = "";
		for (String dicoWord : this.words) {
			if (dicoWord.startsWith(prefix)) {
				if (!results.isEmpty()) {
					results += ":";
				}
				results += dicoWord;
			}
		}
		return results.split(":");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findBySuffix(String suffix) {
		String results = "";
		for (String dicoWord : this.words) {
			if (dicoWord.endsWith(suffix)) {
				if (!results.isEmpty()) {
					results += ":";
				}
				results += dicoWord;
			}
		}
		return results.split(":");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findByContains(String word) {
		String results = "";
		for (String dicoWord : this.words) {
			if (dicoWord.contains(word)) {
				if (!results.isEmpty()) {
					results += ":";
				}
				results += dicoWord;
			}
		}
		return results.split(":");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findByRegex(String expression) {
		String results = "";
		for (String dicoWord : this.words) {
			if (dicoWord.matches(expression)) {
				if (!results.isEmpty()) {
					results += ":";
				}
				results += dicoWord;
			}
		}
		return results.split(":");
	}

}
