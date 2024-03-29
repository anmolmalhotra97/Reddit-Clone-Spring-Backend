package com.example.springredditclone.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data //provides @Getter and @Setter
@Builder //uses builder design patterns to create objects of our classes
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank(message = "Post name cannot be empty of Null")
    private String postName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    //since we can have large chunks of data in this field.
    //Database should store the property as Large object
    //BLOB (Byte Array - Binary data)
    // JPA deserializes the data upon retrieval of field from DB
    private String description;

    private Integer voteCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;
}
