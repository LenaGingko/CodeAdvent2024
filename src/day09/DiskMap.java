package day09;

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

        StringBuilder string = new StringBuilder(formattedLine);
        int leftEmpty;
        int rightFile;

        while(true){
            //Update
            leftEmpty = getLeftEmpty(string);
            rightFile = getRightFile(string);

            if (leftEmpty == -1 || rightFile == -1 || leftEmpty > rightFile) {
                break;
            }

            //Austausch
            string.setCharAt(leftEmpty, string.charAt(rightFile));
            string.setCharAt(rightFile, '.'); //kein neuer charAt Aufruf nötig
        }
        this.compressedLine = string.toString();
    }

    private int getLeftEmpty(StringBuilder string){
        for (int i = 0; i < string.length(); i++) {
            if(string.charAt(i) == '.'){
                return i;
            }
        }
        return -1;
    }

    private int getRightFile(StringBuilder string){
        for (int i = string.length()-1; i >= 0; i--) {
            if(string.charAt(i) != '.'){
                return i;
            }
        }
        return -1;
    }
    //long checksum = compressedFiles.getChecksum();

    public long getChecksum() {
        return checksum;
    }

    public String toString(){
        return "DiskMap formatted:\n" + this.formattedLine + "\nDiskMap compressed:\n" + this.compressedLine;
    }
}
