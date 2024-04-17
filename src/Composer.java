//import java.math.*;
import java.util.*;
import java.io.*;
public class Composer {
    //Samuel Llamzon
    //Senior Project: MUS-490
    //Experiments in Computer Composition
    public static void main(String [] args){
        Neume basicPhrase = new Neume();
        for(int i=0; i<20; i++){
            boolean candidateSelected = false;
            //blank note so that the variable gets instantiated before the loop.
            Note candidate = new Note();
            //candidate
            while(!candidateSelected){
                candidate = randomNewNote();
                boolean validCandidate = true;
                if(validCandidate && !notForbiddenRepetition(basicPhrase, candidate)){
                    validCandidate = false;
                }
                candidateSelected = validCandidate;
            }
            basicPhrase.addNote(candidate);

        }
        System.out.println(basicPhrase.getTranslatedNoteSequence());
        try{
            String filename = "rawMusicTextOutput-" +System.currentTimeMillis()+ ".txt";
            File output = new File(filename);
            output.createNewFile();

            FileWriter composerScribe = new FileWriter(filename);
            composerScribe.write("\\version \"2.24.3\"\r\n\r\n{\r\n\t");
            composerScribe.write(basicPhrase.getTranslatedNoteSequence());
            composerScribe.write("\r\n}");
            /*composerScribe.write("\\version \"2.24.3\"\r\n\r\n{\r\n\t");
            composerScribe.write(basicPhrase.getTranslatedNoteSequence());
            composerScribe.write("\r\n}");*/
            composerScribe.close();

            Runtime activeRuntime = Runtime.getRuntime();
            Process generateLilypondPDF = activeRuntime.exec("lilypond " + filename);
            generateLilypondPDF.destroy();
        }
        catch (IOException exception){
            
        }
    }

    //Methods

    public static String toNotes(List<Note> rawSequence){
        String translatedSequence = "";
        for(int i = 0; i < rawSequence.size(); i++){
            translatedSequence += toNote(rawSequence.get(i).getRawNote()) + "\' ";
        }
        return translatedSequence;
    }
    public static String toNote(char rawNote){
        if(rawNote == '0') return "a";
        if(rawNote == '1') return "ais";
        if(rawNote == '2') return "b";
        if(rawNote == '3') return "c";
        if(rawNote == '4') return "cis";
        if(rawNote == '5') return "d";
        if(rawNote == '6') return "dis";
        if(rawNote == '7') return "e";
        if(rawNote == '8') return "f";
        if(rawNote == '9') return "fis";
        if(rawNote == 'A') return "g";
        return "gis";
    }
    public static Note randomNewNote(){
        return new Note(Integer.toString((int)(12*Math.random()), 12).charAt(0)); // 0<= x < 12
    }

    //Testing methods

    public static boolean notForbiddenRepetition(Neume existingNoteSequence, Note candidate){
        boolean isValid = true;
        List<Note> noteSequence = existingNoteSequence.getNoteSequence();
        for(int i=0; isValid && i<6 && i<noteSequence.size(); i++){
            //Note note1 = noteSequence.get(noteSequence.size()-1-i);
            isValid =  !noteSequence.get(noteSequence.size()-1-i).isEqual(candidate);
        }
        return isValid;
    }

}
