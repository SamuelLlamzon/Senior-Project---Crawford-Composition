//import java.math.*;
import java.util.*;

public class Composer {
    //Samuel Llamzon
    //Senior Project: MUS-490
    //Experiments in Computer Composition
    public static void main(String [] args){
        Neume basicPhrase = new Neume();
        for(int i=0; i<10; i++){
            basicPhrase.addNote(randomNewNote());
        }
        System.out.println(basicPhrase);
    }
    //Methods
    public static String toNotes(List<Note> rawSequence){
        String translatedSequence = "";
        for(int i = 0; i < rawSequence.size(); i++){
            translatedSequence += toNote(rawSequence.get(i).getRawNote()) + " ";
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
}
