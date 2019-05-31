package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;

@Controller
@RequestMapping("/")
public class NoteController {


	private NoteDAO noteDao;

	@Autowired
	public NoteController(NoteDAO noteDAO)
	{
		this.noteDao = noteDAO;
	}

	private Note note = new Note();

	@GetMapping
	public String indexPage(ModelMap model) {

		model.addAttribute("notes", noteDao.getAllNotes());

		return "index";
	}

	// Add New Note

	@PostMapping("add")
	public String addNote(HttpServletRequest request, HttpServletResponse respose, ModelMap model) {

		if (request.getParameter("noteContent").isEmpty()) {
			model.addAttribute("message", "Content cannot be empty");
			return "index";
		}

		else {
			note.setNoteTitle(request.getParameter("noteTitle"));
			note.setNoteContent(request.getParameter("noteContent"));
			note.setNoteStatus(request.getParameter("noteStatus"));
			note.setCreatedAt(LocalDateTime.now());

			noteDao.saveNote(note);
			model.addAttribute("notes", noteDao.getAllNotes());
			return "redirect:/";

		}

	}

	// Delete a Note

	@GetMapping("delete")
	public String deleteNote(@RequestParam String noteId) {

		noteDao.deleteNote(Integer.parseInt(noteId));

		return "redirect:/";

	}

	// Update a Note

	@PostMapping("update")
	public String updateNote(HttpServletRequest request, HttpServletResponse response) {

		Note updateNote = new Note();
		updateNote.setNoteId(Integer.parseInt(request.getParameter("noteId")));
		updateNote.setNoteTitle(request.getParameter("noteTitle"));
		updateNote.setNoteContent(request.getParameter("noteContent"));
		noteDao.UpdateNote(updateNote);

		return "redirect:/";
	}

	@GetMapping("edit")
	public String editNote(@RequestParam int noteId, ModelMap model) {

		Note note = noteDao.getNoteById(noteId);

		model.addAttribute("notes", note);

		return "update";
	}
}
