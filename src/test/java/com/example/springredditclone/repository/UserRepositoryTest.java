package com.example.springredditclone.repository;

import com.example.springredditclone.BaseTest;
import com.example.springredditclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        User expectedUserObject = new User(null, "test user", "secret password", "user@email.com", Instant.now(), true);
        User actualUserObject = userRepository.save(expectedUserObject);
        Assertions.assertThat(actualUserObject).usingRecursiveComparison().ignoringFields("userId").isEqualTo(expectedUserObject);
    }
}
