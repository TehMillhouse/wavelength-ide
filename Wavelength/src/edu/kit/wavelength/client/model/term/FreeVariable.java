package edu.kit.wavelength.client.model.term;

/**
 * Represents a free variable in the untyped lambda calculus.
 * 
 * A free variable has a name. Unlike the name of the variable
 * bound during an abstraction, this name is not a preferred name,
 * but fixed, since it is free over the entire lambda term that
 * is being represented. Changing this name would therefore
 * change the lambda term.
 *
 */
public final class FreeVariable implements LambdaTerm {

	/**
	 * Creates a new free variable term.
	 * @param name The name of the free variable being referenced
	 */
	public FreeVariable(String name) {
		
	}
	
	@Override
	public <T> T acceptVisitor(Visitor<T> v) {
		return null;
	}
	
	/**
	 * Returns the name of the free variable.
	 * @return The name of the free variable
	 */
	public String getName() {
		return null;
	}
	
	@Override
	public boolean equals(Object other) {
		return false;
	}

	@Override
	public String serialize() {
		return null;
	}

}
