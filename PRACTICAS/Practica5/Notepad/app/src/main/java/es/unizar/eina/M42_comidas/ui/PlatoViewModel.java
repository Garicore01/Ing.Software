package es.unizar.eina.M42_comidas.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Note;
import es.unizar.eina.M42_comidas.database.PlatoRepository;

public class PlatoViewModel extends AndroidViewModel {

    private PlatoRepository mRepository;

    private final LiveData<List<Note>> mAllNotes;

    public PlatoViewModel(Application application) {
        super(application);
        mRepository = new PlatoRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() { return mAllNotes; }

    public void insert(Note note) { mRepository.insert(note); }

    public void update(Note note) { mRepository.update(note); }
    public void delete(Note note) { mRepository.delete(note); }
}
