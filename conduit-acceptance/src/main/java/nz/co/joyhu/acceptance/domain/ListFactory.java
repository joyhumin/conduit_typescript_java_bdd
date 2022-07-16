package nz.co.joyhu.acceptance.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ListFactory {
    private final Random random;

    public ListFactory(Random random) {
        this.random = random;
    }

    public <T> List<T> someOf(Supplier<T> intFunction) {
        return someOf(intFunction, 20);
    }

    public <T> List<T> someOf(Supplier<T> intFunction, Integer listBound) {
        return IntStream.range(0, random.nextInt(listBound)).mapToObj(i -> intFunction.get()).collect(Collectors.toList());
    }
}
