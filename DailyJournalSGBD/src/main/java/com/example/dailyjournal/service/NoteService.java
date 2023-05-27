package com.example.dailyjournal.service;

import com.example.dailyjournal.model.Book;
import com.example.dailyjournal.model.Note;
import com.example.dailyjournal.repository.NoteRepository;

import java.util.List;

public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public void save(Note note) {
        noteRepository.save(note);
    }


    public void delete(Note note) {
        noteRepository.delete(note);
    }


    public List<Book> getAllByUserId(Long userId) {
        return noteRepository.getAllByUserId(userId);
    }
}
