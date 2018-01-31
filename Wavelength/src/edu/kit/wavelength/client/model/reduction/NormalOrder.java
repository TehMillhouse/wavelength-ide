package edu.kit.wavelength.client.model.reduction;

import edu.kit.wavelength.client.model.term.Abstraction;
import edu.kit.wavelength.client.model.term.Application;
import edu.kit.wavelength.client.model.term.BoundVariable;
import edu.kit.wavelength.client.model.term.FreeVariable;
import edu.kit.wavelength.client.model.term.IsAbstractionVisitor;
import edu.kit.wavelength.client.model.term.IsApplicationVisitor;
import edu.kit.wavelength.client.model.term.IsRedexVisitor;
import edu.kit.wavelength.client.model.term.LambdaTerm;
import edu.kit.wavelength.client.model.term.NameAgnosticVisitor;
import edu.kit.wavelength.client.model.term.PartialApplication;

/**
 * The normal reduction order for the untyped lambda calculus.
 * 
 * The leftmost outermost redex is selected for reduction.
 *
 */
public final class NormalOrder implements ReductionOrder {

	@Override
	public Application next(LambdaTerm term) {
		return term.acceptVisitor(new NormalOrderVisitor());
	}

	@Override
	public String getName() {
		return "Normal";
	}

	@Override
	public String serialize() {
		return null;
	}

	private class NormalOrderVisitor extends NameAgnosticVisitor<Application> {

		@Override
		public Application visitPartialApplication(PartialApplication app) {
			return app.getRepresented().acceptVisitor(this);
		}

		@Override
		public Application visitAbstraction(Abstraction abs) {
			return abs.getInner().acceptVisitor(this);
		}

		@Override
		public Application visitApplication(Application app) {
			if (app.acceptVisitor(new IsRedexVisitor())) {
				return app;
			} else {
				Application leftResult = app.getLeftHandSide().acceptVisitor(this);
				if (leftResult != null) {
					return leftResult;
				} else {
					return app.getRightHandSide().acceptVisitor(this);
				}
			}
		}

		@Override
		public Application visitBoundVariable(BoundVariable var) {
			return null;
		}

		@Override
		public Application visitFreeVariable(FreeVariable var) {
			return null;
		}

	}
}
