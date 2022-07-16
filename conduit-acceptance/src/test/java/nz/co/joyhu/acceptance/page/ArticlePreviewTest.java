package nz.co.joyhu.acceptance.page;

import com.google.code.beanmatchers.BeanMatchers;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;
import static shiver.me.timbers.data.random.RandomTimes.someTime;

public class ArticlePreviewTest {


    @Before
    public void setUp(){
        BeanMatchers.registerValueGenerator(() -> someTime().toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime(), OffsetDateTime.class);
    }

    @Test
    public void ArticlePreview_is_a_valid_bean() {
        assertThat(ArticlePreview.class, hasValidGettersAndSetters());
    }

    @Test
    public void ArticlePreview_has_toString() {
        assertThat(ArticlePreview.class, hasValidBeanToString()); // TODO: what's the difference?
        ToStringVerifier.forClass(ArticlePreview.class).withClassName(NameStyle.SIMPLE_NAME).verify();
    }

    @Test
    public void ArticlePreview_has_equality() {
        assertThat(ArticlePreview.class, hasValidBeanEquals()); // TODO:same here, check the google bean matcher can
        // saturate the coverage
        assertThat(ArticlePreview.class, hasValidBeanHashCode());
        EqualsVerifier.forClass(ArticlePreview.class).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }
}