package com.example.fundoonotes.controller;

/*
 * Note Controller
 * -------------------------------------------------------
 * Handles all Note-related REST APIs in Fundoo Notes.
 *
 * Functionalities:
 * 1. Create Note
 * 2. Get All Notes (for logged-in user)
 * 3. Update Note
 * 4. Delete Note
 * 5. Pin / Unpin Note
 * 6. Archive / Unarchive Note
 * 7. Trash / Restore Note
 *
 * All APIs are secured using token-based authentication
 * passed via the Authorization header.
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
     * Create a new note for the authenticated user
     */
    @PostMapping
    public NoteResponseDto createNote(
            @Valid @RequestBody NoteRequestDto dto,
            @RequestHeader("Authorization") String token) {

        return noteService.createNote(dto, token);
    }

    /*
     * Fetch all notes belonging to the authenticated user
     */
    @GetMapping
    public List<NoteResponseDto> getAllNotes(
            @RequestHeader("Authorization") String token) {

        return noteService.getAllNotes(token);
    }

    /*
     * Update an existing note
     */
    @PutMapping("/{noteId}")
    public NoteResponseDto updateNote(
            @PathVariable Long noteId,
            @Valid @RequestBody NoteRequestDto dto,
            @RequestHeader("Authorization") String token) {

        return noteService.updateNote(noteId, dto, token);
    }

    /*
     * Delete a note permanently
     */
    @DeleteMapping("/{noteId}")
    public String deleteNote(
            @PathVariable Long noteId,
            @RequestHeader("Authorization") String token) {

        noteService.deleteNote(noteId, token);
        return "Note deleted successfully";
    }

    /*
     * Toggle pin status of a note
     */
    @PatchMapping("/{noteId}/pin")
    public NoteResponseDto pinNote(
            @PathVariable Long noteId,
            @RequestHeader("Authorization") String token) {

        return noteService.pinNote(noteId, token);
    }

    /*
     * Toggle archive status of a note
     */
    @PatchMapping("/{noteId}/archive")
    public NoteResponseDto archiveNote(
            @PathVariable Long noteId,
            @RequestHeader("Authorization") String token) {

        return noteService.archiveNote(noteId, token);
    }

    /*
     * Toggle trash status of a note
     */
    @PatchMapping("/{noteId}/trash")
    public NoteResponseDto trashNote(
            @PathVariable Long noteId,
            @RequestHeader("Authorization") String token) {

        return noteService.trashNote(noteId, token);
    }
}