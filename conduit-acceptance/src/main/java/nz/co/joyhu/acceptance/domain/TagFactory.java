package nz.co.joyhu.acceptance.domain;

import org.springframework.stereotype.Component;

import java.util.List;

import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;

@Component
public class TagFactory {
    private final ListFactory listFactory;

    public TagFactory(ListFactory listFactory) {
        this.listFactory = listFactory;
    }


    public Tag random() {
        Tag tag = new Tag();
        tag.setTagName(someAlphaString(10));
        return tag;
    }

    public List<Tag> randomTags() {
        return listFactory.someOf(this::random);
    }
}
