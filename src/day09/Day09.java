package day09;

public class Day09 {

    public static void main(String[] args) {
        final String line = DataReader.readFile();

        DiskMap diskMap = new DiskMap(line);
        System.out.println(diskMap);

        //System.out.println("Disk Map Length: " + line.length());
        //System.out.println("Disk Map Length formatted: " + diskMap.getFormattedLine().size());
        //System.out.println("Disk Map Length compressed: " + diskMap.getCompressedLine().size());
    }
}
//not 3078395984414 //not 92625219623