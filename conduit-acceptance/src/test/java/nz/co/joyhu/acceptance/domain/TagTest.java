package nz.co.joyhu.acceptance.domain;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;

public class TagTest {

    @Test
    public void Tag_is_a_valid_bean() {
        assertThat(Tag.class, hasValidGettersAndSetters());
    }

    @Test
    public void Tag_has_toString() {
        ToStringVerifier.forClass(Tag.class).withClassName(NameStyle.SIMPLE_NAME).verify();
    }

    @Test
    public void Tag_has_equality() {
        // Signals that getClass is used in the implementation of the equals method, instead of an instanceof check
        EqualsVerifier.forClass(Tag.class).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }
}