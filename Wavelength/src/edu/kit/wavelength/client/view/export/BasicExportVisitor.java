package edu.kit.wavelength.client.view.export;

import java.util.List;
import java.util.Objects;

import edu.kit.wavelength.client.model.library.Library;
import edu.kit.wavelength.client.model.term.Abstraction;
import edu.kit.wavelength.client.model.term.Application;
import edu.kit.wavelength.client.model.term.BoundVariable;
import edu.kit.wavelength.client.model.term.FreeVariable;
import edu.kit.wavelength.client.model.term.NamedTerm;
import edu.kit.wavelength.client.model.term.PartialApplication;
import edu.kit.wavelength.client.model.term.ResolvedNamesVisitor;

/**
 * A basic Visitor to create a string representing a given lambda term. The
 * Visitor sets a minimal number of brackets to correctly describe the lambda
 * term.
 *
 * It also provides a means of varying the representation of a lambda term by
 * overwriting its strategy methods.
 */
public class BasicExportVisitor extends ResolvedNamesVisitor<StringBuilder> {

	protected final String lambda;

	/*
	 * A flag that determines if an abstraction or application should be
	 * surrounded by brackets. All flags should be false at the end of a visit
	 * method.
	 */
	protected boolean bracketsForApp;
	protected boolean bracketsForAbs;

	/**
	 * Creates a new Visitor for {@link LambdaTerm}s.
	 *
	 * It will build a String (represented by a {@link StringBuilder}) from the
	 * lambda term with a custom representation for the lambda-letter.
	 *
	 * @param libraries
	 *            the libraries of the application that are used in this term
	 * @param lambdaRepresentation
	 *            the string representation of the lambda letter
	 */
	public BasicExportVisitor(List<Library> libraries, String lambdaRepresentation) {
		super(libraries);
		Objects.requireNonNull(lambdaRepresentation);

		lambda = lambdaRepresentation;
		bracketsForApp = false;
		bracketsForAbs = false;
	}

	@Override
	public StringBuilder visitApplication(Application app) {
		Objects.requireNonNull(app);

		// Store bracketsFlag for later use.
		final boolean brackets = bracketsForApp;
		setFlags(false);

		// An abstraction inside a application should get brackets.
		bracketsForAbs = true;
		StringBuilder leftSide = app.getLeftHandSide().acceptVisitor(this);

		// If the right term of an application is an application it should get
		// brackets because implicit brackets bind from left to right.
		bracketsForAbs = true;
		bracketsForApp = true;
		StringBuilder rightSide = app.getRightHandSide().acceptVisitor(this);

		StringBuilder result = formatApplication(leftSide, rightSide);
		if (brackets) {
			result.insert(0, "(").append(")");
		}
		return result;
	}

	@Override
	public StringBuilder visitNamedTerm(NamedTerm term) {
		Objects.requireNonNull(term);

		setFlags(false);
		return formatText(new StringBuilder(term.getName()));
	}

	@Override
	public StringBuilder visitPartialApplication(PartialApplication app) {
		Objects.requireNonNull(app);

		setFlags(false);
		return app.getRepresented().acceptVisitor(this);
	}

	@Override
	public StringBuilder visitFreeVariable(FreeVariable var) {
		Objects.requireNonNull(var);

		setFlags(false);
		return new StringBuilder(var.getName());
	}

	@Override
	protected StringBuilder visitBoundVariable(BoundVariable var, String resolvedName) {
		Objects.requireNonNull(var);
		Objects.requireNonNull(resolvedName);

		setFlags(false);
		return new StringBuilder(resolvedName);
	}

	@Override
	protected StringBuilder visitAbstraction(Abstraction abs, String resolvedName) {
		Objects.requireNonNull(abs);
		Objects.requireNonNull(resolvedName);

		// Store bracketsFlag for later use and reset flags.
		final boolean brackets = bracketsForAbs;
		setFlags(false);

		StringBuilder absVariable = new StringBuilder(resolvedName);
		StringBuilder innerTerm = new StringBuilder(abs.getInner().acceptVisitor(this));

		StringBuilder result = new StringBuilder();
		result.append(formatLambda(absVariable)).append(innerTerm);

		if (brackets) {
			result.insert(0, "(").append(")");
		}
		return result;
	}

	/**
	 * Sets all Flags for brackets. Flags should be false at the end of each
	 * visit method.
	 *
	 * @param set
	 *            {@link true} if the Flags should be set and {@link false}
	 *            otherwise
	 */
	protected final void setFlags(Boolean set) {
		bracketsForAbs = set;
		bracketsForApp = set;
	}

	/**
	 * A strategy method to allow inheriting classes to define the
	 * representation of text in the constructed String.
	 *
	 * @param text
	 *            the text of the lambda term
	 * @return the text as it should be represented in the final string
	 */
	protected StringBuilder formatText(StringBuilder text) {
		assert (text != null);

		return text;
	}

	/**
	 * A strategy method to allow inheriting classes to define the
	 * representation of an abstraction in the constructed String.
	 *
	 * The default setting produces a string containing the lambda letter,
	 * followed by the abstraction variable and a dot (e.g. '\x.').
	 *
	 * @param absVariable
	 *            the variable of the abstraction
	 * @return the left part of an abstraction as it should be represented in
	 *         the final string
	 */
	protected StringBuilder formatLambda(StringBuilder absVariable) {
		assert (absVariable != null);

		return absVariable.insert(0, lambda).append(". ");
	}

	/**
	 * A strategy method to allow inheriting classed to define the
	 * representation of an application in the constructed String.
	 *
	 * The default setting seperates the left and right side of the
	 * application by a space.
	 *
	 * @param leftSide
	 *            the left Side of the application
	 * @param rightSide
	 * 						the right Side of the application
	 * @return the application as it should be represented in the
	 * 				 final string
	 */
	protected StringBuilder formatApplication(StringBuilder leftSide, StringBuilder rightSide) {
		assert (leftSide != null && rightSide != null);

		return leftSide.append(" ").append(rightSide);
	}
}
