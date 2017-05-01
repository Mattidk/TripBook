package dk.mathiaspedersen.tripbook.data.exceptions;

import android.support.annotation.NonNull;

public class InvalidUserException extends Exception {

    public InvalidUserException() {
    }

    public InvalidUserException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public InvalidUserException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InvalidUserException(@NonNull Throwable throwable) {
        super(throwable);
    }
}