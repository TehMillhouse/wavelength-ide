package edu.kit.wavelength.client.model.library;

import java.util.List;

import edu.kit.wavelength.client.model.serialization.Serializable;
import edu.kit.wavelength.client.model.term.LambdaTerm;

/**
 * This interface is used to interact with the different libraries provided by
 * the application.
 * 
 * Each library contains a set of {@link LambdaTerm}s and their assigned names.
 * These names can be used in place of terms to both shorten terms and make them
 * easier to understand.
 */
public interface Library extends Serializable {

	/**
	 * Returns the {@link LambdaTerm} with the specified name.
	 * 
	 * @param name
	 *            The name assigned to the desired term
	 * @return The {@link LambdaTerm} with the entered name, null if the library
	 *         does not contain a term with this name
	 */
	LambdaTerm getTerm(String name);

	/**
	 * Determines whether the library contains a {@link LambdaTerm} with the
	 * specified name.
	 * 
	 * @param name
	 *            The name to search the library for
	 * @return {@link true} if the library contains a {@link LambdaTerm} with the
	 *         entered name and {@link false} otherwise
	 */
	boolean containsName(String name);

	/**
	 * Returns the library's name.
	 * 
	 * @return The name of the library
	 */
	String getName();

	/**
	 * Returns info for the terms that this library provides.
	 * 
	 * @return infos
	 */
	List<TermInfo> getTermInfos();

}
