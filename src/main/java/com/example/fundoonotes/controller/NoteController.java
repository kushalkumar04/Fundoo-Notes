package com.example.fundoonotes.controller;

/*
 * Note Controller
 * Handles all note-related APIs including:
 * - Create Note
 * - Get All Notes
 * - Pin Note
 * - Archive Note
 * - Trash Note
 *
 * Uses token-based authentication via Authorization header
 */



import com.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.dto.response.NoteResponseDto;
import com.fundoonotes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    /*
     * Create a new note for authenticated user
     */
    @PostMapping
    public NoteResponseDto createNote(
            @Valid @RequestBody NoteRequestDto dto,
            @RequestHeader("Authorization") String token) {

        return noteService.createNote(dto, token);
    }

    /*
     * Get all notes of authenticated user
     */
    @GetMapping
    public List<NoteResponseDto> getAllNotes(
            @RequestHeader("Authorization") String token) {

        return noteService.getAllNotes(token);
    }

    /*
     * Pin or Unpin a note
     */
    @PatchMapping("/{noteId}/pin")
    public NoteResponseDto pinNote(
            @PathVariable Long noteId,
            @RequestHeader("Authorization") String token) {

        return noteService.pinNote(noteId, token);
    }

    /*
     * Archive or Unarchive a note
     */
    @PatchMapping("/{noteId}/archive")
    public NoteResponseDto archiveNote(
            @PathVariable Long noteId,
            @RequestHeader("Authorization") String token) {

        return noteService.archiveNote(noteId, token);
    }

    /*
     * Trash or Restore a note
     */
    @PatchMapping("/{noteId}/trash")
    public NoteResponseDto trashNote(
            @PathVariable Long noteId,
            @RequestHeader("Authorization") String token) {

        return noteService.trashNote(noteId, token);
    }
}