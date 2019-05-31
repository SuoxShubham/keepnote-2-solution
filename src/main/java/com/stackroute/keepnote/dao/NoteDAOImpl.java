package com.stackroute.keepnote.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stackroute.keepnote.model.Note;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */

	@Autowired
	private SessionFactory sessionFactory;

	public NoteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {

			note.setCreatedAt(LocalDateTime.now());
			sessionFactory.getCurrentSession().save(note);

			return true;
	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {

			Note note = (Note) sessionFactory.getCurrentSession().load(Note.class, noteId);
			sessionFactory.getCurrentSession().delete(note);
			sessionFactory.getCurrentSession().flush();
			return true;

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {
//		Session session = sessionFactory.getCurrentSession();


		List<Note> allNotes = sessionFactory.getCurrentSession().createQuery("from Note order by createdAt desc").list();
		System.out.println(allNotes);
		return allNotes;

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		Note note = sessionFactory.getCurrentSession().load(Note.class, noteId);
		Hibernate.initialize(note);
		return note;

	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {

		note.setCreatedAt(LocalDateTime.now());
		sessionFactory.getCurrentSession().update(note);
		return true;
	}

}
