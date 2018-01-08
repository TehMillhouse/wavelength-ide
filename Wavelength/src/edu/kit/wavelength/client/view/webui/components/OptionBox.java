package edu.kit.wavelength.client.view.webui.components;

import java.util.List;

import edu.kit.wavelength.client.model.serialization.Serializable;
import edu.kit.wavelength.client.view.api.Deactivatable;
import edu.kit.wavelength.client.view.api.Readable;

/**
 * A OptionBox is an adapter class for a GWT ListBox.
 * 
 * It provides a means for the User to set Options for a calculation. This Box
 * can be blocked and unblocked if changing the Options must not be possible.
 */
public class OptionBox implements Deactivatable, Readable, Serializable {

	/**
	 * Constructs a new and empty OptionBox.
	 */
	public OptionBox(List<String> entries) {
		
	}

	@Override
	public void block() {

	}

	@Override
	public void unblock() {

	}

	@Override
	public String read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserialize(String serialized) {
		// TODO Auto-generated method stub
		
	}

}