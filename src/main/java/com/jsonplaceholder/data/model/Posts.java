package com.jsonplaceholder.data.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Posts {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
