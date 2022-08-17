package com.example.springredditclone.repository;

import com.example.springredditclone.BaseTest;
import com.example.springredditclone.model.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest extends BaseTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldSavePost() {
        Post expectedPostObject = new Post(null, "First Post", "http://url.site", "Test", 0, null, Instant.now(), null);
        Post actualPostObject = postRepository.save(expectedPostObject);

        Assertions.assertThat(actualPostObject).usingRecursiveComparison().ignoringFields("postId").isEqualTo(expectedPostObject);
    }
}
