package com.example.fundoonotes.service;

import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;

import java.util.List;

public interface NoteService {

    NoteResponseDto createNote(NoteRequestDto dto, String token);

    List<NoteResponseDto> getAllNotes(String token);
    NoteResponseDto updateNote(Long noteId, NoteRequestDto dto, String token);

    void deleteNote(Long noteId, String token);

    @Override
    public NoteResponseDto updateNote(Long noteId, NoteRequestDto dto, String token) {

        Note note = getNoteByIdAndUser(noteId, token);

        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());

        Note updated = noteRepository.save(note);

        return mapToDto(updated);
        @Override
        public void deleteNote(Long noteId, String token) {

            Note note = getNoteByIdAndUser(noteId, token);

            noteRepository.delete(note);
        }
    }
}