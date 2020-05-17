package exceptions;

public class InvalidActionInThisGraphException extends Exception{
	public InvalidActionInThisGraphException(String invalidActionInSimpleGraph) {
		super(invalidActionInSimpleGraph);
	}
}
