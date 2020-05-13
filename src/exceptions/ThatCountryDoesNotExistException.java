package exceptions;

public class ThatCountryDoesNotExistException extends Exception{
	public ThatCountryDoesNotExistException(String message) {
		super(message);
	}
}
