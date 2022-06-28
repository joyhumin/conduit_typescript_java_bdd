package nz.co.joyhu.acceptance.steps;

/**
 * Holder is used in steps to hold data across different step definitions
 *
 */

public class GenericHolder<T> {

    private T value;

    public GenericHolder() {
    }

    public GenericHolder(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "\n" + value;
    }
}
