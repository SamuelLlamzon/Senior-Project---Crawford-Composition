import java.util.*;
import java.lang.Math;

public class BoundedNeume extends Neume{
    private int lowerBound, higherBound;
    private int[] frequencyArray;

    //
    //Constructors
    //

    //empty constructor
    public BoundedNeume(int lowestValue, int highestValue){
        super();
        lowerBound = lowestValue;
        higherBound = highestValue;
        frequencyArray = new int [highestValue - lowestValue + 1];
    }

    //one note constructor
    public BoundedNeume(int lowestValue, int highestValue, Note firstNote){
        super(firstNote);
        lowerBound = lowestValue;
        higherBound = highestValue;
        frequencyArray = new int [highestValue - lowestValue + 1];
    }

    //existing Neume constructor
    public BoundedNeume(int lowestValue, int highestValue, Neume oldNeume){
        super(oldNeume);
        lowerBound = lowestValue;
        higherBound = highestValue;
        frequencyArray = new int [highestValue - lowestValue + 1];
    }

    //
    // Accessor methods
    //

    public int getLowerBound(){
        return lowerBound;
    }

    public int getHigherBound(){
        return higherBound;
    }

    public int[] getFrequencyArray(){
        return frequencyArray;
    }

    public int getHighestFrequency(){
        int highestFrequency = 0;
        for(int occurrences : frequencyArray){
            highestFrequency = Math.max(highestFrequency, occurrences);
        }
        return highestFrequency;
    }

    public int getLowestFrequency(){
        if(super.getSequenceLength() == 0)
            return 0;
        int lowestFrequency = frequencyArray[0];
        for(int occurrences : frequencyArray){
            lowestFrequency = Math.min(lowestFrequency, occurrences);
        }
        return lowestFrequency;
    }

    public int getFrequencyRange(){
        return getHighestFrequency() - getLowestFrequency();
    }

    public List<Integer> mostFrequentNotes(){
        int highestFrequency = getHighestFrequency();
        List<Integer> frequentNotes = new ArrayList<Integer>();
        for(int i=0; i<frequencyArray.length; i++){
            if(frequencyArray[i]==highestFrequency){
                frequentNotes.add(lowerBound+i);
            }
        }
        return frequentNotes;
    }

    public List<Integer> lessFrequentNotes(){
        int highestFrequency = getHighestFrequency();
        List<Integer> frequentNotes = new ArrayList<Integer>();
        for(int i=0; i<frequencyArray.length; i++){
            if(frequencyArray[i]!=highestFrequency){
                frequentNotes.add(lowerBound+i);
            }
        }
        return frequentNotes;
    }

    public List<Integer> leastFrequentNotes(){
        int lowestFrequency = getLowestFrequency();
        List<Integer> infrequentNotes = new ArrayList<Integer>();
        for(int i=0; i<frequencyArray.length; i++){
            if(frequencyArray[i]==lowestFrequency){
                infrequentNotes.add(lowerBound+i);
            }
        }
        return infrequentNotes;
    }

    public List<Integer> unusedNotes(){
        List<Integer> infrequentNotes = new ArrayList<Integer>();
        for(int i=0; i<frequencyArray.length; i++){
            if(frequencyArray[i]==0){
                infrequentNotes.add(lowerBound+i);
            }
        }
        return infrequentNotes;
    }

    public int generateCompatiblePitchClass(){
        int generatedInt = (int)((higherBound-lowerBound+1)*(Math.random())) + lowerBound; // 24<=x<=51, lb=24, hb=51
        System.out.print(generatedInt + ", ");
        return generatedInt;
    }
    //
    // Mutators
    //

    @Override
    public void addNote(Note newNote){
        super.addNote(newNote);
        frequencyArray[newNote.getPitchValue()-lowerBound]++;
    }

    @Override
    public Note removeNote(){
        Note removedNote = super.removeNote();
        frequencyArray[removedNote.getPitchValue()-lowerBound]--;
        return removedNote;
    }
}