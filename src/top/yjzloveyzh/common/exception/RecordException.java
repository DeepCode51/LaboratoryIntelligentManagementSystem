/**
 *
 */
package top.yjzloveyzh.common.exception;

public class RecordException extends Exception{
    private static final long serialVersionUID = 7484041367089813273L;
    private String errorCode;
    private String message;

    public RecordException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
