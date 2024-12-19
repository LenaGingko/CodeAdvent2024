package day09;

public class DiskMap {
    private long checksum = 0;
    private final String line;
    private String formattedLine;
    private String compressedLine;


    DiskMap(String line) {
        this.line = line;
        this.format();
        this.compress();
        this.calcChecksum();
    }

    public void format(){
        int id = 0;
        StringBuilder newLine = new StringBuilder();
        boolean isFileBlock = true; //notFreeSpace

        for(int i = 0; i < line.length(); i++){
            int number = Character.getNumericValue(line.charAt(i));
            if(isFileBlock) {
                for(int j = 0; j < number; j++){
                    newLine.append(id); // 2 -> 00
                }
                id++;
            } else {
                for(int j = 0; j < number; j++) {
                    newLine.append('.');
                }
            }
            isFileBlock = !isFileBlock;
        }
        this.formattedLine = newLine.toString();
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
    private void calcChecksum(){
        int pos = 0;
        for (char c: compressedLine.toCharArray()) {
            if(c == '.'){
                break; }

            checksum += (long) Character.getNumericValue(c) * pos;
            if(pos < 10){System.out.println( pos + " * " + Character.getNumericValue(c) + " = " + checksum);}
            pos++;
        }
    }

    public long getChecksum() { //1156401938 too low //92625219623 too low
        return this.checksum;
    }

    public String getFormattedLine() {
        return this.formattedLine;
    }

    public String toString(){
        return  "DiskMap compressed:\n" + this.compressedLine +"\nCheckSum: " + checksum; //"DiskMap compressed:\n" + this.compressedLine +
    }
}
