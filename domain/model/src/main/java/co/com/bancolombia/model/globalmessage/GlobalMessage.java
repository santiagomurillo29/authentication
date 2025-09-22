package co.com.bancolombia.model.globalmessage;

public enum GlobalMessage {

    BAD_PARAMETER(GlobalMessage.STATUS_CODE_400, "The email is already registered"),
    NOT_FOUND_USER(GlobalMessage.STATUS_CODE_404, "User not found"),
    NOT_FOUND_ROLE(GlobalMessage.STATUS_CODE_404, "Role not found"),
    NOT_FOUND_EMAIL(GlobalMessage.STATUS_CODE_404, "Email not found"),
    DATABASE_ERROR(GlobalMessage.STATUS_CODE_500, "Database is down"),
    BAD_CREDENTIALS(GlobalMessage.STATUS_CODE_500, "Bad credentials");

    public static final String STATUS_CODE_400 = "400";
    public static final String STATUS_CODE_404 = "404";
    public static final String STATUS_CODE_500 = "500";

    private final String statusCode;
    private final String message;

    GlobalMessage(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
