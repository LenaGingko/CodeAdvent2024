package day09;

import java.util.ArrayList;

public class DiskMap {
    private long checksum = 0;
    private final String line;
    private ArrayList<Block> formattedLine = new ArrayList<>();
    private String compressedLine;

    public DiskMap(String line) {
        this.line = line;
        this.format();
        this.compress();
        this.calcChecksum();
    }

    public static class Block {
        int id;
        int count;
        public Block(int id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    public void format() {
        boolean isFileBlock = true;
        int id = 0;
        int currentId = -1;

        for (char c : line.toCharArray()) {
            int number = Character.getNumericValue(c);

            if (isFileBlock) {
                if (currentId != id) {
                    formattedLine.add(new Block(id, number));
                    currentId = id; // Update current ID
                } else {
                    // Extend the current block
                    Block lastBlock = formattedLine.get(formattedLine.size() - 1);
                    lastBlock.count += number;
                }
                id++;
            } else {
                if (!formattedLine.isEmpty() && formattedLine.get(formattedLine.size() - 1).id == -1) {
                    // Extend the last free block
                    Block lastBlock = formattedLine.get(formattedLine.size() - 1);
                    lastBlock.count += number;
                } else {
                    // Add a new free block
                    formattedLine.add(new Block(-1, number));
                }
            }
            isFileBlock = !isFileBlock;
        }
    }


    public void compress() {
        //ArrayList<Block> newCompressedLine = new ArrayList<>();
        StringBuilder string = new StringBuilder(formatAsString(this.formattedLine));
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

            System.out.println("Test: " + string);
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

    public void calcChecksum(){
        int pos = 0;
        for (char c: compressedLine.toCharArray()) {
            if(c == '.'){
                break; }

            checksum += (long) Character.getNumericValue(c) * pos;
            System.out.print( pos + " * " + Character.getNumericValue(c) + " = " + checksum + " | ");
            if(pos % 10 == 0){ System.out.print("\n"); }
            pos++;
        }
    }

    public long getChecksum() {
        return checksum;
    }

    public ArrayList<Block> getFormattedLine() {
        return formattedLine;
    }

    public String getCompressedLine() {
        return compressedLine;
    }

    //für test
    public void setCompressedLine(String compressedLine) {
        this.compressedLine = compressedLine;
    }

    public String toString() {
        return "Checksum: " + checksum;
    }

    public String formatAsString(ArrayList<Block> line) {
        StringBuilder sb = new StringBuilder();
        for (Block block : line) {
            if (block.id == -1) {
                sb.append(".".repeat(block.count));
            } else {
                sb.append(String.valueOf(block.id).repeat(block.count));
            }
        }
        return sb.toString();
    }
}
