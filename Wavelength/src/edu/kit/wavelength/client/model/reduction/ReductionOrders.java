package edu.kit.wavelength.client.model.reduction;

import java.util.Arrays;
import java.util.List;

import edu.kit.wavelength.client.model.output.Full;
import edu.kit.wavelength.client.model.output.OutputSize;
import edu.kit.wavelength.client.model.output.Periodic;
import edu.kit.wavelength.client.model.output.ResultOnly;
import edu.kit.wavelength.client.model.output.Shortened;

/**
 * Static class giving access to all {@link ReductionOrder}s known to the model.
 *
 */
public final class ReductionOrders {

	/**
	 * Returns an unmodifiable list of all {@link ReductionOrder}s known to the
	 * model.
	 * 
	 * @return An unmodifiable list containing exactly one instance of all
	 *         {@link ReductionOrder}s known to the model
	 */
	public static List<ReductionOrder> all() {
		return Arrays.asList(new NormalOrder(), new CallByName(), new CallByValue(), new ApplicativeOrder());
	}

	/**
	 * Returns the {@link ReductionOrder} represented by a given string.
	 * 
	 * @param serialized
	 *            A serialized reduction order
	 * @return The {@link ReductionOrder} the given string refers to, if known to
	 *         the model
	 */
	public static ReductionOrder deserialize(String serialized) {
		if (serialized == null || serialized.isEmpty())
			throw new IllegalArgumentException("serialized must be non-empty");

		switch (serialized.charAt(0)) {
		case 'a':
			return new ApplicativeOrder();

		case 'N':
			return new CallByName();

		case 'V':
			return new CallByValue();

		case 'n':
			return new NormalOrder();
		}

		throw new IllegalArgumentException("serialized must represent a reduction order");
	}

	private ReductionOrders() {
	}
}
