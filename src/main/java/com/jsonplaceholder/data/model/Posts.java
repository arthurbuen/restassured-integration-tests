package com.jsonplaceholder.data.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Posts {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
