package edu.kit.wavelength.client.action;

import edu.kit.wavelength.client.UIState;
import edu.kit.wavelength.client.view.Hideable;
import edu.kit.wavelength.client.view.Writable;

/**
 * This action causes the application to leave the current Exercise state.
 */
public class ExitExerciseMode implements Action {

	private UIState state;
	private Writable editor;
	private Hideable exercisePanel;
	private Writable output;

	@Override
	public void run() {
		// state.exitExercise();
	}

}
