package com.springblog.springblog.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class UserDTO {
    private Long id;
    private String title;
    private String content;
}
