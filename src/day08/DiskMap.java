package day08;

public class DiskMap {
    private long checksum = 0;
    private String line;
    private String formattedLine;
    private String compressedLine;

    DiskMap(String line) {
        this.line = line;
    }

    public void format(){
        int id = 0;
        String newLine = "";
        boolean isFileBlock = true; //notFreeSpace

        for(int i = 0; i < line.length(); i++){

            int number = Character.getNumericValue(line.charAt(i));

            if(isFileBlock) {
                for(int j = 0; j < number; j++){
                    newLine = newLine.concat(Integer.toString(id)); // 2 -> 00
                }
                id++;

            } else {
                for(int j = 0; j < number; j++) {
                    newLine = newLine.concat(".");
                }
            }

            isFileBlock = !isFileBlock;
        }

        this.formattedLine = newLine;
    }

    public void compress(){
        this.compressedLine = "";

    }

    //String layout = diskMap.format();
    //String compressedFiles = layout.compress();
    //long checksum = compressedFiles.getChecksum();

    //for each file
    //compress() //recursive?

    public long getChecksum() {
        return checksum;
    }

    public String toString(){
        return "DiskMap formatted:\n" + this.formattedLine + "\nDiskMap compressed:\n" + this.compressedLine;
    }
}
