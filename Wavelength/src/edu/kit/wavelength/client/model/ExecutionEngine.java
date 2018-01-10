package edu.kit.wavelength.client.model;

import java.util.List;

import edu.kit.wavelength.client.model.library.Library;
import edu.kit.wavelength.client.model.output.OutputSize;
import edu.kit.wavelength.client.model.reduction.ReductionOrder;
import edu.kit.wavelength.client.model.term.Application;
import edu.kit.wavelength.client.model.term.LambdaTerm;

/**
 * An execution engine manages the reduction of a lambda term.
 * It keeps the history of terms and which of these terms were
 * displayed and is able to reduce the current term according
 * to a reduction order or reduce a specific redex in the current
 * term. It also keeps track of which terms should be displayed and
 * is able to revert to the previous displayed term.
 * 
 */
public class ExecutionEngine {

	/**
	 * Creates a new execution engine.
	 * @param input The textual representation of a lambda term to be handled
	 * @param order The reduction order to be used by default
	 * @param size The output size to be used
	 * @param libraries The libraries to be taken into consideration during parsing
	 */
	public ExecutionEngine(String input, ReductionOrder order, OutputSize size, List<Library> libraries) {
		
	}

	/**
	 * Executes a single reduction of the current lambda term.
	 * @param enablePartialApplication Whether partial applications
	 * and acceleration are enabled
	 * @return Whether this step is displayed
	 */
	public boolean stepForward(boolean enablePartialApplication) {
		return false;
	}
	
	/**
	 * Executes a single reduction of the supplied redex.
	 * @param redex The redex to be evaluated. Must be a redex, otherwise an
	 * exception is thrown
	 */
	public void stepForward(Application redex) {

	}
	
	/**
	 * Determines whether the execution is finished according to the current reduction order.
	 * @return {@code true} if the current reduction order does not provide another redex,
	 * {@code false} otherwise
	 */
	public boolean isFinished() {
		return false;
	}
	
	/**
	 * Reverts to the previously output lambda term.
	 */
	public void stepBackward() {
	
	}
	
	/**
	 * Returns a list of all lambda terms that have been displayed.
	 * @return A list of all lambda terms that have been displayed
	 */
	public List<LambdaTerm> getDisplayed() {
		return null;
	}
	
	/**
	 * Returns the last lambda term that has been displayed.
	 * @return The last lambda term that has been displayed
	 */
	public LambdaTerm getLast() {
		return null;
	}
	
	/**
	 * Displays the currently reduced term, adding it to the list of displayed terms.
	 * @return the current term
	 */
	public LambdaTerm displayCurrent() {
		return null;
	}
	
	/**
	 * Changes the active reduction order to the entered one.
	 * @param reduction The new reduction order
	 */
	public void setReductionOrder(ReductionOrder reduction) {
		
	}
	
	/**
	 * Serializes the ExecutionEngine by serializing its current OutputSize, ReductionOrder and the terms it holds.
	 * @return The ExecutionEngine's serialized String representation
	 */
	public String serialize() {
		return null;
	}
}
