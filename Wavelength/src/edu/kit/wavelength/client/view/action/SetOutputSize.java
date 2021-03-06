package edu.kit.wavelength.client.view.action;

import edu.kit.wavelength.client.model.output.OutputSize;
import edu.kit.wavelength.client.model.output.OutputSizes;
import edu.kit.wavelength.client.view.App;

/**
 * Changes the output size to the selected one. This will only affect upcoming
 * display of lambda terms, it has no effect on the already displayed terms.
 */
public class SetOutputSize implements Action {

	private static App app = App.get();

	@Override
	public void run() {
		if (app.executor().isTerminated()) {
			return;
		}
		String sizeName = app.outputSizeBox().getSelectedItemText();
		OutputSize newSize = OutputSizes.all().stream().filter(s -> s.getName().equals(sizeName)).findFirst().get();
		app.executor().setOutputSize(newSize);

		Control.updateControls();
	}

}
