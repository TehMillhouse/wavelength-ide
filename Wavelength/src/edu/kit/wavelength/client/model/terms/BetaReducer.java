package edu.kit.wavelength.client.model.terms;

public class BetaReducer extends TermTransformer {
	
	private final Application toReduce;
	
	public BetaReducer(Application toReduce) {
		this.toReduce = toReduce;
	}

	@Override
	public LambdaTerm visitAbstraction(Abstraction abs) {
		return null;
	}

	@Override
	public LambdaTerm visitApplication(Application app) {
		return null;
	}

	@Override
	public LambdaTerm visitBoundVariable(BoundVariable var) {
		return null;
	}

	@Override
	public LambdaTerm visitFreeVariable(FreeVariable var) {
		return null;
	}
 
}
