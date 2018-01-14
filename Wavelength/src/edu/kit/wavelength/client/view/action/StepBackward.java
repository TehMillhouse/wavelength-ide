package edu.kit.wavelength.client.view.action;

import edu.kit.wavelength.client.view.App;

/**
 * This class removes the last shown reduction step from the output.
 */
public class StepBackward implements Action {
	
	private static App app = App.get();

	/**
	 * Removes the last shown step from the output.
	 */
	@Override
	public void run() {
		// TODO: remove step from output? 
		app.executor().stepBackward();
		if (app.executor().getDisplayed().isEmpty()) {
			app.stepBackwardButton().lock();
		}
	}

}
