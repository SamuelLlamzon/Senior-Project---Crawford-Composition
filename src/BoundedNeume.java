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

    public int mostFrequentOccurrences(){
        int highestFrequency = 0;
        for(int occurrences : frequencyArray){
            highestFrequency = Math.max(highestFrequency, occurrences);
        }
        return highestFrequency;
    }

    public int leastFrequentOccurrences(){
        if(super.getSequenceLength() == 0)
            return 0;
        int lowestFrequency = frequencyArray[0];
        for(int occurrences : frequencyArray){
            lowestFrequency = Math.min(lowestFrequency, occurrences);
        }
        return lowestFrequency;
    }
}