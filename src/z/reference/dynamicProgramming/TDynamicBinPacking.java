package z.reference.dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TDynamicBinPacking {

    public List<File> files;
    public List<Bin> bins;
    public int currentBin;
    public int binCapacity;

    public TDynamicBinPacking(List<File> files, int binCapacity) {
        this.files = files;
        this.bins = new ArrayList<>();
        this.currentBin = 0;
        this.binCapacity = binCapacity;
    }

    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        files.add(new File(1, "Peter", 9));
        files.add(new File(2, "Steve", 3));
        files.add(new File(3, "Roger", 5));
        files.add(new File(4, "Lisa", 2));
        files.add(new File(5, "Andrew", 4));
        files.add(new File(6, "Patrick", 8));
        TDynamicBinPacking game = new TDynamicBinPacking(files, 8);
        game.solve();
        game.showBins();
    }

    private void solve() {
        /*Collections.sort(this.files, (fileOne, fileTwo) -> {
            return fileOne.size - fileTwo.size;
        });*/

        Collections.sort(this.files, Comparator.comparing(File::getSize).reversed());

        if (this.files.get(0).size > this.binCapacity) {
            System.out.println("No Solution Possible");
            return;
        }

        this.currentBin = 0;
        this.bins.add(new Bin(currentBin, this.binCapacity));
        for (File file : this.files) {
            int index = 0;
            while (index <= currentBin) {
                if (file.size > (this.bins.get(index).capacity - this.bins.get(index).filled)) {
                    index++;
                    continue;
                } else {
                    this.bins.get(index).files.add(file);
                    this.bins.get(index).filled = this.bins.get(index).filled + file.size;
                    break;
                }
            }
            if (index > currentBin) {
                this.bins.add(new Bin(++currentBin, this.binCapacity));
                this.bins.get(currentBin).files.add(file);
                this.bins.get(currentBin).filled = this.bins.get(currentBin).filled + file.size;
            }
        }

        System.out.println("No of Bins Required: " + (currentBin + 1));
        System.out.println();
        System.out.println("Showing Each Bin ********************** ");
        for (Bin bin : this.bins) {
            System.out.println(bin.id);
            System.out.println("Files: ");
            for (File file : bin.files) {
                System.out.print("ID: " + file.id + " Data: " + file.data + " Size: " + file.size + "\n");
            }
        }
    }

    private void showBins() {

    }
}

class Bin {
    public int id;
    public List<File> files;
    public int capacity;
    public int filled;

    public Bin(int id, int capacity) {
        this.id = id;
        this.files = new ArrayList<>();
        this.capacity = capacity;
        this.filled = 0;
    }
}

class File {
    public int id;
    public String data;
    public int size;

    public File(int id, String data, int size) {
        this.id = id;
        this.data = data;
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}