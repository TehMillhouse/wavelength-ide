package edu.kit.wavelength.client.model.term.parsing;

// A type of token
enum TokenType {
	LBRACKET {
		@Override
		public String toString() {
			return "\"(\"";
		}
	},
	RBRACKET {
		@Override
		public String toString() {
			return "\")\"";
		}
	},
	NAME {
		@Override
		public String toString() {
			return "a variable";
		}
	},
	LAMBDA {
		@Override
		public String toString() {
			return "\"λ\"";
		}
	},
	DOT {
		@Override
		public String toString() {
			return "\".\"";
		}
	},
	EQUALS {
		@Override
		public String toString() {
			return "\"=\"";
		}
	},
	NEWLINE {
		@Override
		public String toString() {
			return "\n";
		}
	},
	SPACE {
		@Override
		public String toString() {
			return "a whitespace";
		}
	}
}
