package nz.co.joyhu.acceptance.domain;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {

    @Test
    public void User_is_a_valid_bean() {
        assertThat(User.class, hasValidGettersAndSetters());
    }

    @Test
    public void User_has_toString() {
        ToStringVerifier.forClass(User.class).withClassName(NameStyle.SIMPLE_NAME).verify();
    }

    @Test
    public void User_has_equality() {
        // use Google bean-matchers need to do both hashcode & equality check
        EqualsVerifier.forClass(User.class).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }
}