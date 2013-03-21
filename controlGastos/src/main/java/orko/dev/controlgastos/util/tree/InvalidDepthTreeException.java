package orko.dev.controlgastos.util.tree;

public class InvalidDepthTreeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDepthTreeException() {
		super();
	}

	public InvalidDepthTreeException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidDepthTreeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidDepthTreeException(String arg0) {
		super(arg0);
	}

	public InvalidDepthTreeException(Throwable arg0) {
		super(arg0);
	}

	
}
