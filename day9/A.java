package day9;

import java.util.ArrayList;
import java.util.List;

public class A {
    private String fileSystem;
    private List<Integer> convertedFileSystem;
    private List<Integer> compactedFileSystem;

    public A(String fs){
        this.fileSystem = fs;
        this.convertedFileSystem = new ArrayList<>();
        this.compactedFileSystem = new ArrayList<>();
    }

    public List<Integer> convert(){
        char[] fscs = fileSystem.toCharArray();

        Integer id = 0;
        for(int i = 0; i < fscs.length; i++){
            if(i%2 == 0){ // File-block;
                for(int j = 0; j < Character.getNumericValue(fscs[i]); j++){
                    this.convertedFileSystem.add(id);
                }
                id++;
            }else{ // Empty-block
                for(int j=0;j < Character.getNumericValue(fscs[i]);j++){
                    this.convertedFileSystem.add(-1);
                }
            }
        }

        return this.convertedFileSystem;
    }

    public List<Integer> compact(){
        for(int i=0; i<this.convertedFileSystem.size(); i++){    
            if(this.convertedFileSystem.get(i) == -1){
                for(int j = this.convertedFileSystem.size() - 1; j > i; j--){
                    if(this.convertedFileSystem.get(j) != -1){
                        this.convertedFileSystem.set(i, this.convertedFileSystem.get(j));
                        this.convertedFileSystem.set(j, -1);
                        break;
                    }
                }
            }
            this.compactedFileSystem.add(this.convertedFileSystem.get(i));
        }

        return this.compactedFileSystem;
    }

    public long calculateCheckSum(){
        long checkSum = 0;

        for(int i=0; i<this.compactedFileSystem.size(); i++){
            if(this.compactedFileSystem.get(i) != -1){
                checkSum += i * this.compactedFileSystem.get(i);
            }
        }
        return checkSum;
    }

    public List<Integer> getConvertedFS(){
        return this.convertedFileSystem;
    }
}
