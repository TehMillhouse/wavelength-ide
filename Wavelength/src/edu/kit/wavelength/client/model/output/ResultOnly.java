package edu.kit.wavelength.client.model.output;

import java.util.List;

/**
 * Output size that displays no terms live and
 * only displays the very last term in the end.
 *
 */
final class ResultOnly implements OutputSize {

	@Override
	public boolean displayLive(int step) {
		return false;
	}

	@Override
	public List<Integer> displayAtEnd(int totalSteps, int lastDisplayed) {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

}