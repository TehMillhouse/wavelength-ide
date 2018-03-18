package edu.kit.wavelength.client.model.library;

import java.util.Arrays;
import java.util.List;

import edu.kit.wavelength.client.model.term.Abstraction;
import edu.kit.wavelength.client.model.term.Application;
import edu.kit.wavelength.client.model.term.BoundVariable;
import edu.kit.wavelength.client.model.term.LambdaTerm;
import edu.kit.wavelength.client.model.term.NamedTerm;

/**
 * A {@link Library} contains definitions for {@link LambdaTerms}s for tuples
 * (pairs) and lists.
 * 
 * The terms are encoded by the Church encoding. The library contains tuples and
 * the simple operations 'first' and 'second'. In addition this library supports
 * lists. A list node is represented by a pair. The library also supports basic
 * operations on lists.
 */
public final class TuplesAndLists implements Library {

	public static final char ID = 't';
	public static final String NAME = "Church Tuples and Lists";

	// true and false needed for more advanced list operations
	private final NamedTerm tru = new NamedTerm("true",
			new Abstraction("t", new Abstraction("f", new BoundVariable(2))));
	private final NamedTerm fal = new NamedTerm("false",
			new Abstraction("t", new Abstraction("f", new BoundVariable(1))));

	// library terms for tuples
	private final NamedTerm pair = new NamedTerm("pair", new Abstraction("x", new Abstraction("y", new Abstraction("z",
			new Application(new Application(new BoundVariable(1), new BoundVariable(3)), new BoundVariable(2))))));
	private final NamedTerm first = new NamedTerm("first", new Abstraction("p",
			new Application(new BoundVariable(1), new Abstraction("x", new Abstraction("y", new BoundVariable(2))))));
	private final NamedTerm second = new NamedTerm("second", new Abstraction("p",
			new Application(new BoundVariable(1), new Abstraction("x", new Abstraction("y", new BoundVariable(1))))));

	// Build a list where each list node is pair (a,b).
	// Where 'a' is the head of the list and 'b' the tail (possibly containing
	// more pairs).
	// A List containing only one Element 'a' should be constructed by:
	// '(prepend a newList)'
	// same as 'false'
	private final NamedTerm newList = new NamedTerm("newList",
			new Abstraction("t", new Abstraction("f", new BoundVariable(1))));
	// same as 'pair'
	private final NamedTerm prepend = new NamedTerm("prepend",
			new Abstraction("x", new Abstraction("y",
					new Abstraction("z", new Application(new Application(new BoundVariable(1), new BoundVariable(3)),
							new BoundVariable(2))))));
	// same as 'first'
	private final NamedTerm head = new NamedTerm("head", new Abstraction("p",
			new Application(new BoundVariable(1), new Abstraction("x", new Abstraction("y", new BoundVariable(2))))));
	// same as 'second'
	private final NamedTerm tail = new NamedTerm("tail", new Abstraction("p",
			new Application(new BoundVariable(1), new Abstraction("x", new Abstraction("y", new BoundVariable(1))))));
	private final NamedTerm isEmpty = new NamedTerm("isEmpty",
			new Abstraction("l", new Application(new Application(new BoundVariable(1),
					new Abstraction("h", new Abstraction("t", new Abstraction("d", fal)))), tru)));

	private final List<NamedTerm> definitions = Arrays.asList(tru, fal, pair, first, second, newList, prepend, head,
			tail, isEmpty);

	@Override
	public LambdaTerm getTerm(String name) {
		for (NamedTerm t : definitions) {
			if (t.getName().equals(name)) {
				return t.getInner();
			}
		}
		return null;
	}

	@Override
	public boolean containsName(String name) {
		for (NamedTerm t : definitions) {
			if (t.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public StringBuilder serialize() {
		return new StringBuilder("" + ID);
	}
	
	@Override
	public List<TermInfo> getTermInfos() {
		return Arrays.asList(new TermInfo("true", "Used as a literal or 'true a b' yields a."),
                             new TermInfo("false", "Used as a literal or 'false a b' yields b."),
		                     new TermInfo("pair a b", "'pair a b' yields (a, b)."),
		                     new TermInfo("newList", "'newList' yields []."),
		                     new TermInfo("first a", "'first a' yields a[0] for a : pair or a : list."),
		                     new TermInfo("second a", "'second a' yields a[1] for a : pair or a[1:end] for a : list."),
		                     new TermInfo("prepend a b", "'prepend a b' yields a:b for a : list."),
		                     new TermInfo("head a", "'first a' yields a[0] for a : list or a : pair."),
		                     new TermInfo("tail a", "'second a' yields a[1:end] for a : list or a[1] for a : pair."),
		                     new TermInfo("isEmpty a", "'isEmpty a' yields true if a == [] or false if a != [] for a : list."));
	}
	
}
