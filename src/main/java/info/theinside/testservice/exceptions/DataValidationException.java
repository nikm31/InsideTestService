package info.theinside.testservice.exceptions;

import java.util.List;

public class DataValidationException extends RuntimeException {
    private final List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public DataValidationException(List<String> messages) {
        this.messages = messages;
    }
}
