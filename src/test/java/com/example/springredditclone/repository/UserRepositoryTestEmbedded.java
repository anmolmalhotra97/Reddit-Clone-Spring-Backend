package com.example.springredditclone.repository;

import com.example.springredditclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.util.Optional;

//Provided by spring test framework
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTestEmbedded {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        User user = new User(null, "test user", "secret password", "user@email.com", Instant.now(), true);
        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);
    }

    @Test
    @Sql("classpath:test-data.sql")
    //uses a sql file to pre-populate the data and then comparisons are made on that basis
    public void shouldSaveUsersThroughSqlFile() {
        Optional<User> test = userRepository.findByUserName("testuser_sql");
        Assertions.assertThat(test).isNotEmpty();
    }
}
