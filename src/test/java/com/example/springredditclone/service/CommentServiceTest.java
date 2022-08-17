package com.example.springredditclone.service;

import com.example.springredditclone.exceptions.SpringRedditException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class CommentServiceTest {

    @Test
    @DisplayName("Test should pass when comment does not contain swear words")
    public void shouldNotContainSwearWordsInsideComment() {
        CommentService commentService = new CommentService();
        //USING JUNIT
        //Assertions.assertFalse(commentService.containsSwearWords("this is a clean comment"));

        //USING ASSERTJ
        assertThat(commentService.containsSwearWords("this is a comment")).isFalse();
    }

    @Test
    @DisplayName("Should throw exception when exception contains swear words")
    public void shouldFailWhenCommentContainsSwearWords() {
        CommentService commentService = new CommentService();

        //USING JUNIT
//        SpringRedditException exception = Assertions.assertThrows(SpringRedditException.class, () -> {
//            commentService.containsSwearWords("this is a shitty comment");
//        });
//        Assertions.assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));

        //USING ASSERTJ
        assertThatThrownBy(() -> {
            commentService.containsSwearWords("this is a shitty comment");
        }).isInstanceOf(SpringRedditException.class).hasMessage("Comments contains unacceptable language");
    }

}