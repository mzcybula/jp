package util;

public final class Preconditions {

    public static void checkArgument(boolean expression) {
        if (expression) {
            throw new IllegalArgumentException("Invalid parameters");
        }
    }

    public static <T> T checkNotNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        return object;
    }

    private Preconditions() {
    }
}
