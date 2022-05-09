package nz.co.joyhu.acceptance.steps;

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
