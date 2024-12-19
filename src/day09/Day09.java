package day09;

public class Day09 {

    public static void main(String[] args) {
        final String line = DataReader.readFile();

        DiskMap diskMap = new DiskMap(line);
        diskMap.format();
        //System.out.println("First " + diskMap);
        diskMap.compress();
        System.out.println("Second " + diskMap);

        //System.out.println("Disk Map Length: " + line.length());
        //System.out.println("Disk Map: " + line); //length 19999
    }
}
