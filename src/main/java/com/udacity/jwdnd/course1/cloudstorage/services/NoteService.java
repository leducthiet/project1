package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotesByUser(Integer userId) {
        return noteMapper.getAllNotesByUser(userId);
    }

    public int insertNote(Note note) {
        return noteMapper.insertNote(note);
    }

    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }

}
