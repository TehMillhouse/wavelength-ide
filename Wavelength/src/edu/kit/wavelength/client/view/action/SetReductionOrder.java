package edu.kit.wavelength.client.view.action;

import edu.kit.wavelength.client.model.reduction.ReductionOrder;
import edu.kit.wavelength.client.model.reduction.ReductionOrders;
import edu.kit.wavelength.client.view.App;

/**
 * This action changes the reduction order for the further execution.
 */
public class SetReductionOrder implements Action {
	
	private static App app = App.get();

	@Override
	public void run() {
		if (app.executor().isTerminated()) {
			return;
		}
		String orderName = app.reductionOrderBox().getSelectedItemText();
		ReductionOrder newOrder = ReductionOrders.all().stream().filter(o -> o.getName().equals(orderName)).findFirst()
				.get();
		app.executor().setReductionOrder(newOrder);
		
		Control.updateControls();
	}

}