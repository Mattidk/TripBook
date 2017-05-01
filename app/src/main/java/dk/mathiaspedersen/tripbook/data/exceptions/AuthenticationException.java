package dk.mathiaspedersen.tripbook.data.exceptions;

import android.support.annotation.NonNull;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
    }

    public AuthenticationException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public AuthenticationException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AuthenticationException(@NonNull Throwable throwable) {
        super(throwable);
    }
}