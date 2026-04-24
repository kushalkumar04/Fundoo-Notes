package com.example.fundoonotes.service.impl;

/*
 * Note Service Implementation
 * Contains business logic for notes
 */

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;
import com.fundoonotes.entity.Note;
import com.fundoonotes.entity.User;
import com.fundoonotes.repository.NoteRepository;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.NoteService;
import com.fundoonotes.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    private User getUserFromToken(String token) {
        Long userId = tokenUtil.decodeToken(token);
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public NoteResponseDto createNote(NoteRequestDto dto, String token) {

        User user = getUserFromToken(token);

        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        note.setUser(user);

        Note saved = noteRepository.save(note);

        NoteResponseDto response = new NoteResponseDto();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());

        return response;
    }

    @Override
    public List<NoteResponseDto> getAllNotes(String token) {

        User user = getUserFromToken(token);

        return noteRepository.findByUserId(user.getId())
                .stream()
                .map(note -> {
                    NoteResponseDto dto = new NoteResponseDto();
                    dto.setId(note.getId());
                    dto.setTitle(note.getTitle());
                    dto.setDescription(note.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}