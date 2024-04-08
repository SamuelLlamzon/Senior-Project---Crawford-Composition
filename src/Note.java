public class Note{
    private char rawNote;
    private int octave;

    //Constructors
    
    //empty constructor
    public Note(){
        //This will contain a char (0-B) representing all 12 of the semitones a, a#, b, etc. as numbers in base_12.
        rawNote = ' ';
        //Length of the rawNoteSequence.
        octave = -1;
    }

    //one note constructor, no specified ocatve;
    public Note(char noteValue){
        rawNote = noteValue;
        octave = 4;
    }

    //one note constructor, specified ocatve;
    public Note(char noteValue, int specifiedOctave){
        rawNote = noteValue;
        octave = specifiedOctave;
    }

    //
    //Mutators
    //

    //new note, same octave
    public void changeNote(char newNote){
        rawNote = newNote;
    }

    //new note, new octave
    public void changeNote(char newNote, int newOctave){
        rawNote = newNote;
        octave = newOctave;
    }

    //
    //Accessors
    //

    public char getRawNote(){
        return rawNote;
    }

    public String getTranslatedNote(){
        return Composer.toNote(rawNote);
    }

    public int getNoteOctave(){
        return octave;
    }

    public String toString(){
        return "(" + rawNote + octave + ")";
    }
}