package com.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CheckSentenceRequest {
    @NotBlank
    @Size(min = 1, max = 2048)
    private String sentence;
}
