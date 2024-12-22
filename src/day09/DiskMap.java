package day09;

import java.util.ArrayList;

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

    public static class Block {
        int id;
        int count;

        public Block(int id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    public void format() {
        ArrayList<Block> blocks = new ArrayList<>();
        boolean isFileBlock = true;
        int id = 0;

        for (char c : line.toCharArray()) {
            int number = Character.getNumericValue(c);
            if (isFileBlock) {
                if (blocks.isEmpty() || blocks.get(blocks.size() - 1).id != id) {
                    blocks.add(new Block(id, number));
                } else {
                    blocks.get(blocks.size() - 1).count += number;
                }
                id++;
            } else {
                blocks.add(new Block(-1, number));
            }

            isFileBlock = !isFileBlock;
        }

        StringBuilder newLine = new StringBuilder();
        for (Block block : blocks) {
            if (block.id == -1) {
                newLine.append(".".repeat(block.count));
            } else {
                newLine.append(String.valueOf(block.id).repeat(block.count));
            }
        }
        this.formattedLine = newLine.toString();
        System.out.println("Formatted Line Length: " + this.formattedLine.length());
    }

    public void compress() {
        StringBuilder string = new StringBuilder(formattedLine);
        int leftEmpty;
        int rightFile;

        while (true) {
            leftEmpty = getLeftEmpty(string);
            rightFile = getRightFile(string);

            if (leftEmpty == -1 || rightFile == -1 || leftEmpty > rightFile) {
                break;
            }

            string.setCharAt(leftEmpty, string.charAt(rightFile));
            string.setCharAt(rightFile, '.'); //no new charAt needed
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
    public void calcChecksum() {
        int pos = 0;
        StringBuilder idBuilder = new StringBuilder();

        for (char c : this.compressedLine.toCharArray()) {
            if (c == '.') {
                idBuilder.setLength(0);
                continue;
            }

            if (Character.isDigit(c)) {
                idBuilder.append(c);
            } else {
                if (idBuilder.length() > 0) {
                    int id = Integer.parseInt(idBuilder.toString());
                    checksum += (long) id * pos;
                    idBuilder.setLength(0);
                }
            }
            pos++;
        }

        if (idBuilder.length() > 0) {
            int id = Integer.parseInt(idBuilder.toString());
            checksum += (long) id * pos;
        }
    }


    public long getChecksum() {
        return checksum;
    }

    public String getFormattedLine() {
        return formattedLine;
    }

    public String getCompressedLine() {
        return compressedLine;
    }

    //für test
    public void setCompressedLine(String compressedLine) {
        this.compressedLine = compressedLine;
    }

    public String toString(){
        return  "\nCheckSum: " + checksum; //"DiskMap compressed:\n" + this.compressedLine +"DiskMap formatted:\n" + this.formattedLine +
    }
}
