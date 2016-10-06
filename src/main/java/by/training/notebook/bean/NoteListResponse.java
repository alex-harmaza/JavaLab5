package by.training.notebook.bean;

import by.training.notebook.bean.Response;
import by.training.notebook.bean.entity.Note;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class NoteListResponse extends Response {

    private Note[] notes;


    public NoteListResponse(Note[] notes){
        super();
        setNotes(notes);
    }


    public Note[] getNotes() {
        return notes;
    }

    public void setNotes(Note[] notes) {
        this.notes = notes;
    }
}
