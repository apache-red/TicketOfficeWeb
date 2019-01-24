package by.htp.ticketoffice.exceptions;

public class DAOException extends ProjectException {

	private static final long serialVersionUID = 1L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(String msg, Exception e) {
		super(msg, e);
	}

}
