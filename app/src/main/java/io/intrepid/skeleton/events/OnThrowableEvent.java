package io.intrepid.skeleton.events;

public class OnThrowableEvent {
    private Throwable throwable;

    public OnThrowableEvent(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
