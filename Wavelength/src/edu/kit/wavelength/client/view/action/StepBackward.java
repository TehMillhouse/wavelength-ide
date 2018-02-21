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
		if (app.executor().isPaused() && app.executor().canStepBackward()) {
			app.executor().stepBackward();
			Control.updateControls();
			return;
		}
		if (app.executor().isRunning() && app.executor().canStepBackward()) {
			app.executor().pause();
			Control.updateControls();
			return;
		}

		app.executor().terminate();
		app.outputArea().clear();
		Control.updateControls();
	}

}
