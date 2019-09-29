package fr.formation.dicoutils;

/**
 * Interface permettant de définir un moyen commun de rechercher dans un
 * dictionnaire de mots. Chaque classe qui implémente cette interface doit
 * conserver le dictionnaire en tant que membre. En effet les méthodes de
 * recherche ne prennent pas le dictionnaire en paramètre, seulement le mot-clé
 * à rechercher.
 */
public interface DicoSearch {

	/**
	 * Recherche de l'existance d'un mot exact dans le dictionnaire.
	 * 
	 * @param word le mot exact recherché.
	 * @return boolean vrai si et seulement si le mot est présent dans le
	 *         dictionnaire.
	 */
	public boolean findExact(String word);

	/**
	 * Recherche des mots du dictionnaire qui commencent par la chaîne donnée en
	 * paramètre.
	 * 
	 * @param prefix le mot-clé à rechercher.
	 * @return String[] un tableau de tous les mots correspondants.
	 */
	public String[] findByPrefix(String prefix);

	/**
	 * Recherche des mots du dictionnaire qui se terminent par la chaîne donnée en
	 * paramètre.
	 * 
	 * @param suffix le mot-clé à rechercher.
	 * @return String[] un tableau de tous les mots correspondants.
	 */
	public String[] findBySuffix(String suffix);

	/**
	 * Recherche des mots du dictionnaire qui contiennent la chaîne donnée en
	 * paramètre.
	 * 
	 * @param word le mot-clé à rechercher.
	 * @return String[] un tableau de tous les mots correspondants.
	 */
	public String[] findByContains(String word);

	/**
	 * Recherche des mots du dictionnaire qui correspondent à l'expression donnée en
	 * paramètre.
	 * 
	 * @param expression la regex, autrement appelée expression régulière ou
	 *                   rationnelle.
	 * @return String[] un tableau de tous les mots correspondants.
	 */
	public String[] findByRegex(String expression);

	/**
	 * @description
	 *
	 * @return String
	 *
	 * @method findByAll
	 * @class DicoSearch
	 * @version 1.0
	 * @param expression
	 * @date samedi 21 sept. 2019
	 * @see
	 *
	 **/
	public String[] findByAll(String expression);
}
