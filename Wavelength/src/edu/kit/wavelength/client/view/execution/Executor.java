package edu.kit.wavelength.client.view.execution;

import java.util.List;

import com.google.gwt.core.client.Scheduler;

import edu.kit.wavelength.client.model.ExecutionEngine;
import edu.kit.wavelength.client.model.library.Library;
import edu.kit.wavelength.client.model.output.OutputSize;
import edu.kit.wavelength.client.model.reduction.ReductionOrder;
import edu.kit.wavelength.client.model.term.Application;
import edu.kit.wavelength.client.model.term.LambdaTerm;

public class Executor {

	private List<ExecutionObserver> observers;
	
	private boolean autoRunning = false;
	private ExecutionEngine engine;
	
	public Executor(List<ExecutionObserver> observers) {
		this.observers = observers;
	}
	
	private void pushTerm(LambdaTerm t) {
		observers.forEach(o -> o.pushTerm(t));
	}
	
	private void scheduleExecution() {
		autoRunning = true;
		Scheduler.get().scheduleIncremental(() -> {
			boolean displayTerm = engine.stepForward(true);
			if (!autoRunning) {
				LambdaTerm current = engine.displayCurrent();
				pushTerm(current);
			} else if (displayTerm) {
				pushTerm(engine.getLast());
			}
			return autoRunning;
		});
	}
	
	public void start(String input, ReductionOrder order, OutputSize size, List<Library> libraries) {
		engine = new ExecutionEngine(input, order, size, libraries);
		scheduleExecution();
	}
	
	public void pause() {
		autoRunning = false;
	}

	public void unpause() {
		scheduleExecution();
	}
	
	public void terminate() {
		pause();
	}

	public void stepByStep(String input, ReductionOrder order, OutputSize size, List<Library> libraries) {
		engine = new ExecutionEngine(input, order, size, libraries);
	}
	
	/**
	 * Executes a single reduction of the current lambda term.
	 */
	public void stepForward() {
		engine.stepForward(true);
		LambdaTerm current = engine.displayCurrent();
		pushTerm(current);
	}
	
	/**
	 * Executes a single reduction of the supplied redex.
	 * @param term The redex to be evaluated. Must be a redex, otherwise
	 * an exception is thrown
	 */
	public void stepForward(Application redex) {
		engine.stepForward(redex);
		pushTerm(engine.getLast());
	}
	
	/**
	 * Reverts to the previously output lambda term.
	 */
	public void stepBackward() {
		engine.stepBackward();
	}

	/**
	 * Changes the active reduction order to the entered one.
	 * @param type The new reduction order.
	 */
	public void setReductionOrder(ReductionOrder reduction) {
		engine.setReductionOrder(reduction);
	}
	
	/**
	 * Returns a list of all lambda terms that have been displayed.
	 * @return A list of all lambda terms that have been displayed
	 */
	public List<LambdaTerm> getDisplayed() {
		return engine.getDisplayed();
	}
	
	/**
	 * Returns the last lambda term that has been displayed.
	 * @return The last lambda term that has been displayed
	 */
	public LambdaTerm getLast() {
		return engine.getLast();
	}
	
	/**
	 * Serializes the Executor by serializing its ExecutionEngine.
	 * @return The Executor serialized String representation
	 */
	public String serialize() {
		return null;
	}

}