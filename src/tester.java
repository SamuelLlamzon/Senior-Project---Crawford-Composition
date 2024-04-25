//import java.util.*;
import java.io.*;
public class tester {
    public static void main(String [] args){
        /*Neume Neumy = new Neume("4");
        System.out.println(Neumy);
        Neumy.addNote("3");
        System.out.println(Neumy);
        Neumy.removeNote();
        System.out.println(Neumy);
        Neumy.removeNote();
        System.out.println(Neumy);
        Neumy.removeNote();
        Integer n = 5;
        Integer m = 100;
        System.out.println(m.compareTo(n));
        String s = "a";
        String t = "g";
        System.out.println(s.compareTo(t));
        */
        BoundedNeume basicPhrase = new BoundedNeume(24, 36);
        for(int i=24; i<37; i++){
            basicPhrase.addNote(new Note(i));
        }
        try{
            String filename = "testFile " +System.currentTimeMillis()/1000+ ".txt";
            File output = new File(filename);
            output.createNewFile();

            FileWriter composerScribe = new FileWriter(filename);
            composerScribe.write("\\version \"2.24.3\"\r\n\r\n\\fixed a,, {\r\n\t");
            composerScribe.write(basicPhrase.getTranslatedNoteSequence());
            composerScribe.write(basicPhrase.getTransposedNeume(6).getTranslatedNoteSequence());
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
