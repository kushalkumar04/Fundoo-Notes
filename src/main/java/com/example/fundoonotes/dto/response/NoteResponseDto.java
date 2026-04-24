package com.example.fundoonotes.dto.response;


import lombok.Data;

@Data
public class NoteResponseDto {

    private Long id;
    private String title;
    private String description;
}