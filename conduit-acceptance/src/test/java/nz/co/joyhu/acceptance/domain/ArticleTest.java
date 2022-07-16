package nz.co.joyhu.acceptance.domain;

import com.google.code.beanmatchers.BeanMatchers;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;
import static shiver.me.timbers.data.random.RandomTimes.someTime;

public class ArticleTest {
    @Before
    public void setUp() throws Exception {
        BeanMatchers.registerValueGenerator(()-> someTime().toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(), OffsetDateTime.class);
    }

    @Test
    public void Article_is_a_valid_bean() {
        assertThat(Article.class, hasValidGettersAndSetters());
    }

    @Test
    public void Article_has_toString() {
        ToStringVerifier.forClass(Article.class).withClassName(NameStyle.SIMPLE_NAME).verify();
    }

    @Test
    public void Article_has_equality() {
        EqualsVerifier.forClass(Article.class).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }
}