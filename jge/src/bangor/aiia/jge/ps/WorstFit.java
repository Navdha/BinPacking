package bangor.aiia.jge.ps;

import java.util.ArrayList;
import java.util.List;

public class WorstFit extends AbstractBinPacking {

    private List<Bin> bins = new ArrayList<Bin>();

    public WorstFit(List<Integer> in, int binSize) {
        super(in, binSize);
    }

    @Override
    public int getResult() {        
       // bins.add(new Bin(binSize)); // add first bin
        for (Integer currentItem : in) {
            // iterate over bins and try to put the item into the best one it fits into 
        	//least space available in the bin
           int BinNumber = bins.size();
            int worstBin = -1;
            int worstBinAmount = binSize;
            for (int i = 0; i < BinNumber; i++){
            		if(worstBinAmount > bins.get(i).currentSize && (bins.get(i).currentSize + currentItem) <= bins.get(i).maxSize){
            			worstBinAmount = bins.get(i).currentSize;
            			worstBin = i;
            		}
            }
            if(worstBin == -1){
            	Bin newBin = new Bin(binSize);
                newBin.put(currentItem);
                bins.add(newBin);
            }
            else{
            	bins.get(worstBin).put(currentItem);
            }
           
        }
        return bins.size();
    }

    @Override
    public void printBestBins() {
    	System.out.println("Bins:");
        if (bins.size() == in.size()) {
        	System.out.println("each item is in its own bin");
        } else {
            for (Bin bin : bins) {
            	System.out.println(bin.toString());
            }
        }
    }
}
