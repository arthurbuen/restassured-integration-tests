package com.jsonplaceholder.data.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Comments {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
