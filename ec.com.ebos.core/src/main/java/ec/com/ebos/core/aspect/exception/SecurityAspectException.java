package ec.com.ebos.core.aspect.exception;

import ec.com.ebos.core.root.exception.RootException;

public class SecurityAspectException extends RootException {

	private static final long serialVersionUID = 1305855571537286057L;

	/**
	 * Constructor por defecto
	 */
	public SecurityAspectException() {
		super();
	}

	/**
	 * Constructor en base a un mensaje
	 * 
	 * @param summary
	 *            Mensaje de error
	 */
	public SecurityAspectException(String summary, Object... args) {
		super(summary, args.toString());
	}
}
