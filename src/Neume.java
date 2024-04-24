import java.util.*;

public class Neume{
    private List<Note> rawNoteSequence;
    private int sequenceLength;
    //private int[] frequencyArray; 

    //Constructors
    
    //empty constructor
    public Neume(){
        //This ArrayList will contain note objects, each with their own information regarding octave and pitch class.
        rawNoteSequence = new ArrayList<Note>();
        //Length of the rawNoteSequence.
        sequenceLength = 0;
        //frequencyArray = new int [12];
    }

    //one note constructor
    public Neume(Note firstNote){
        rawNoteSequence = new ArrayList<Note>();
        rawNoteSequence.add(firstNote);
        sequenceLength = 1;
        //frequencyArray = new int [12];
        //frequencyArray[firstNote.getRawNote()]++;
    }

    //existing Neume constructor
    public Neume(Neume oldNeume){
        rawNoteSequence = oldNeume.rawNoteSequence; //I know I should probably use an accessor method... bleh.
        sequenceLength = oldNeume.sequenceLength;
        //frequencyArray = oldNeume.frequencyArray;
    }

    //Mutators

    public void addNote(Note newNote){
        rawNoteSequence.add(newNote);
        sequenceLength += 1;
    }
    
    //Probably won't use this one, but...
    public void removeNote(){
        rawNoteSequence.remove(sequenceLength-1);
        sequenceLength -= 1;
    }

    //No excerpt method will be created as one can simply toString(), do some String manipulation, and create a new Neume.
    //Accessors
    public List<Note> getNoteSequence(){
        return rawNoteSequence;
    }

    public String getTranslatedNoteSequence(){
        return Composer.toNotes(rawNoteSequence);
    }

    public int getSequenceLength(){
        return sequenceLength;
    }

    public String toString(){
        return rawNoteSequence.toString();
    }

    public Neume getTransposedNeume(int intervalOfTransposition){
        Neume transposedNeume = new Neume();
        for(Note rawNote : rawNoteSequence){
            transposedNeume.addNote(rawNote.getTransposedNote(intervalOfTransposition));
        }
        return new Neume(transposedNeume);
    }
}