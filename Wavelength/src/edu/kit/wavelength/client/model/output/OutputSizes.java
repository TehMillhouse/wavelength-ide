package edu.kit.wavelength.client.model.output;

import java.util.List;

/**
 * Static class giving access to all output sizes known to the model.
 *
 */
public final class OutputSizes {

	/**
	 * Returns an unmodifiable list of all output sizes known to the model.
	 * @return An unmodifiable list containing all output sizes known to the
	 * model
	 */
	public static List<OutputSize> all() {
		return null;
	}
	
	/**
	 * Returns the output size referred to by a given string.
	 * @param serialized The string to be deserialized
	 * @return The output size that the given string represents, if known to the model
	 */
	public static OutputSize deserialize(String serialized) {
		return null;
	}
	
	private OutputSizes() {
	}
}
