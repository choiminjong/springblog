package com.springblog.springblog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@NoArgsConstructor
public class Board {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자이하로 작성해주세요")
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private Users users;

}
