package nz.co.joyhu.acceptance.domain;

import org.springframework.stereotype.Component;

import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomStrings.someAlphanumericString;

@Component
public class UserFactory {
    public User random() {
        final User newUser = new User();
        newUser.setUsername(someAlphaString(7));
        newUser.setPassword(someAlphanumericString(10, 17));
        newUser.setEmail(generateRandomEmail());
        return newUser;
    }

    private String generateRandomEmail() {
        return someAlphaString(5, 10) + "@" + someAlphanumericString(5) + "." + someAlphaString(3);
    }

    public User randomExistUser() {
        final User existUser = new User();
        existUser.setEmail("test@gmail.com");
        existUser.setUsername("test");
        existUser.setPassword("test");
        return existUser;
    }


}
