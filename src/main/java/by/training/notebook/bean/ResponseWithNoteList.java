package by.training.notebook.bean;

import by.training.notebook.bean.entity.Note;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class ResponseWithNoteList extends Response {

    private Note[] noteList;


    public ResponseWithNoteList(Note[] noteList){
        super(true);
        setNoteList(noteList);
    }


    public Note[] getNoteList() {
        return noteList;
    }

    public void setNoteList(Note[] noteList) {
        this.noteList = noteList;
    }
}
