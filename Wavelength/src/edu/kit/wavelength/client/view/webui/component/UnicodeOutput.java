package edu.kit.wavelength.client.view.webui.component;

import edu.kit.wavelength.client.view.api.Output;

/**
 * Displays lambda terms in text format with unicode symbols.
 */
public class UnicodeOutput implements Output {

	@Override
	public void lock() {		
	}

	@Override
	public void unlock() {		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public boolean isShown() {
		return false;
	}

	@Override
	public boolean isLocked() {
		return false;
	}

	@Override
	public void write(String input) {
		
	}
	
	@Override
	public void removeLastTerm() {
		
	}
	
	public void setTerm(UnicodeTerm term) {
		write(term.getRepresentation());
	}

}
