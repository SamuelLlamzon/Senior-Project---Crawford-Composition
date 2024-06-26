//import java.math.*;
import java.util.*;
import java.io.*;
public class Composer {
    //Samuel Llamzon
    //Senior Project: MUS-490
    //Experiments in Computer Composition
    public static void main(String [] args){

        //instantiates the first Neume object.
        BoundedNeume basicPhrase = new BoundedNeume(24, 51); //A3 - C5
        for(int i=0; i<20; i++){
            boolean candidateSelected = false;

            //blank note so that the variable gets instantiated before the loop.
            Note candidate = null;

            //candidate verification and selection.
            while(!candidateSelected){
                candidate = null;

                //If there are notes that have occurred too frequently, pick a less frequent note.
                if(candidate == null && basicPhrase.getFrequencyRange()>=4){
                    List<Integer> infrequentNotes = basicPhrase.lessFrequentNotes();
                    candidate = new Note(infrequentNotes.get((int)(infrequentNotes.size()*Math.random())));
                }
                if(candidate == null){
                    //If there are no preferred candidates candidate = randomNewNote();
                    candidate = new Note(basicPhrase.generateCompatiblePitchClass());
                }
                
                //Checks against candidate rules
                candidateSelected = checkCandidateValidity(basicPhrase, candidate);
            }
            basicPhrase.addNote(candidate);

        }
        //System.out.println(basicPhrase.getTranslatedNoteSequence());

        //File generation
        generateMusicalScore(basicPhrase);
    }

    //Methods

    public static String toNotes(List<Note> rawSequence){
        String translatedSequence = "";
        for(Note currentNote : rawSequence){
            translatedSequence += toNote(currentNote);
            for(int i = 1; i < currentNote.getNoteOctave(); i++){
                translatedSequence += "\'";
            }
            translatedSequence += " ";
        }
        return translatedSequence;
    }
    public static String toNote(Note rawNote){
        char rawNoteValue = rawNote.getRawNote();
        if(isBlackKey(rawNoteValue) && rawNote.getAccidentalSuffix().equals("es")){
            if(rawNoteValue == '1') return "bes";
            if(rawNoteValue == '4') return "des";
            if(rawNoteValue == '6') return "ees";
            if(rawNoteValue == '9') return "ges";
            return "aes";
        }
        else{
            if(rawNoteValue == '0') return "a";
            if(rawNoteValue == '1'){
                rawNote.setAccidentalSuffix("is");
                return "ais";
            }
            if(rawNoteValue == '2') return "b";
            if(rawNoteValue == '3') return "c";
            if(rawNoteValue == '4'){
                rawNote.setAccidentalSuffix("is");
                return "cis";
            }
            if(rawNoteValue == '5') return "d";
            if(rawNoteValue == '6'){
                rawNote.setAccidentalSuffix("is");
                return "dis";
            }
            if(rawNoteValue == '7') return "e";
            if(rawNoteValue == '8') return "f";
            if(rawNoteValue == '9'){
                rawNote.setAccidentalSuffix("is");
                return "fis";
            }
            if(rawNoteValue == 'A') return "g";

            rawNote.setAccidentalSuffix("is");
            return "gis";
        }
    }
    public static Note randomNewNote(){
        return new Note(Integer.toString((int)(12*Math.random()), 12).charAt(0)); // 0<= x < 12
    }

    //Testing methods

    public static boolean checkCandidateValidity(Neume basicPhrase, Note candidate){
        boolean validCandidate = true;
            //Check for forbidden repetitions
            if(validCandidate && !notForbiddenRepetition(basicPhrase, candidate)){
                validCandidate = false;
            }
            //if(validCandidate && )
            return validCandidate;
    }

    public static boolean notForbiddenRepetition(Neume existingNoteSequence, Note candidate){
        boolean isValid = true;
        List<Note> noteSequence = existingNoteSequence.getNoteSequence();
        for(int i=0; isValid && i<6 && i<noteSequence.size(); i++){
            //Note note1 = noteSequence.get(noteSequence.size()-1-i);
            isValid = !noteSequence.get(noteSequence.size()-1-i).isEqual(candidate);
        }
        return isValid;
    }

    // Resource method to determine whether a piece is sharp or flat. Utilized in the Note constructor.

    public static String assignSuffix(char noteValue){
        if(isBlackKey(noteValue)){
            if((int)(Math.random()*2)==0){
                return "is";
            }
            else{
                return "es";
            }
        }
        return "";
    }

    public static boolean isBlackKey(char noteValue){
        //System.out.println(noteValue);
        int pitchClass = Integer.parseInt(noteValue + "", 12);
        return pitchClass == 1 || pitchClass == 4 || pitchClass == 6 || pitchClass == 9 || pitchClass == 11;
    }
    /*public static boolean notForbiddenIntervalRepetition(Neume existingNoteSequence, Note candidate){
        boolean isValid = true;
        List<Note> noteSequence = existingNoteSequence.getNoteSequence();
        for(int i=0; isValid && i<6 && i<noteSequence.size(); i++){
            //Note note1 = noteSequence.get(noteSequence.size()-1-i);
            isValid =  !noteSequence.get(noteSequence.size()-1-i).isEqual(candidate);
        }
        return isValid;
    }*/

    //
    // File generation methods
    //

    public static void generateMusicalScore(Neume basicPhrase){
        try{
            String filename = "rawMusicTextOutput-" +System.currentTimeMillis()+ ".txt";
            File output = new File(filename);
            output.createNewFile();

            FileWriter composerScribe = new FileWriter(filename);
            composerScribe.write("\\version \"2.24.3\"\r\n\r\n\\fixed a,, {\r\n\t");
            composerScribe.write(basicPhrase.getTranslatedNoteSequence());
            composerScribe.write(basicPhrase.getTransposedNeume(1).getTranslatedNoteSequence());
            composerScribe.write("\r\n}");
            /*composerScribe.write("\\version \"2.24.3\"\r\n\r\n{\r\n\t");
            composerScribe.write(basicPhrase.getTranslatedNoteSequence());
            composerScribe.write("\r\n}");*/
            composerScribe.close();
                
            Runtime activeRuntime = Runtime.getRuntime();
            Process generateLilypondPDF = activeRuntime.exec("lilypond " + filename);
            generateLilypondPDF.waitFor();
            generateLilypondPDF.destroy();
        }
        catch (IOException exception){
            
        }
        catch(InterruptedException calledException){
            System.out.println("File generation process interrupted");
        }
    }
}
