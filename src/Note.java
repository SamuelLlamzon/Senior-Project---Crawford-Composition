public class Note implements Comparable<Note>{
    private char rawNote;
    private int octave;
    private String accidentalSuffix;

    //Constructors
    
    //empty constructor
    public Note(){
        //This will contain a char (0-B) representing all 12 of the semitones a, a#, b, etc. as numbers in base_12.
        rawNote = ' ';
        //Length of the rawNoteSequence.
        octave = -1;
        accidentalSuffix = "";
    }

    //one note constructor, no specified ocatve;
    public Note(char noteValue){
        rawNote = noteValue;
        octave = 4;
        accidentalSuffix = Composer.assignSuffix(noteValue);
    }

    //one note constructor, specified ocatve;
    public Note(char noteValue, int specifiedOctave){
        rawNote = noteValue;
        octave = specifiedOctave;
        accidentalSuffix = Composer.assignSuffix(noteValue);
    }

    //
    //Mutators
    //

    //new note, same octave
    public void changeNote(char newNote){
        rawNote = newNote;
        accidentalSuffix = Composer.assignSuffix(newNote);
    }

    //new note, new octave
    public void changeNote(char newNote, int newOctave){
        rawNote = newNote;
        octave = newOctave;
        accidentalSuffix = Composer.assignSuffix(newNote);
    }

    //
    //Accessors
    //

    public int getPitchValue(){
        return (octave-1)*12 + Integer.parseInt(rawNote + "", 12);
    }

    public char getRawNote(){
        return rawNote;
    }

    public String getAccidentalSuffix(){
        return accidentalSuffix;
    }

    public String getTranslatedNote(){
        return Composer.toNote(this);
    }

    public int getNoteOctave(){
        return octave;
    }

    public Note getTransposedNote(int intervalOfTransposition){
        return new Note(Integer.toString((Integer.parseInt(rawNote + "", 12)+intervalOfTransposition)/12).charAt(0), octave + intervalOfTransposition % 12);
    }

    public String toString(){
        return "(" + rawNote + octave + ")";
    }

    public boolean isEqual(Note otherNote){
        return rawNote == otherNote.getRawNote() && octave == otherNote.getNoteOctave();
    }

    //This is almost a compareTo method...
    public int getInterval(Note otherNote){
        return otherNote.getRawNote() - rawNote;
    }

    public int getAbsoluteInterval(Note otherNote){
        return Math.abs(getInterval(otherNote));
    }

    //Overridden methods
    @Override
    public int compareTo(Note otherNote){
        return this.getPitchValue() - otherNote.getPitchValue();
    }
}