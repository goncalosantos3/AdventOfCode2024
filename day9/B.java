// Source code is decompiled from a .class file using FernFlower decompiler.
package day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B {
   private String fileSystem;
   private List<Integer> convertedFileSystem;
   private List<Integer> compactedFileSystem;

   public B(String fs) {
      this.fileSystem = fs;
      this.convertedFileSystem = new ArrayList<>();
      this.compactedFileSystem = new ArrayList<>();
   }

   public List<Integer> convert() {
      char[] fs = this.fileSystem.toCharArray();
      Integer id = 0;

      for(int i = 0; i < fs.length; i++) {
         int j;
         if (i % 2 != 0) {
            for(j = 0; j < Character.getNumericValue(fs[i]); ++j) {
               this.convertedFileSystem.add(-1);
            }
         } else {
            for(j = 0; j < Character.getNumericValue(fs[i]); ++j) {
               this.convertedFileSystem.add(id);
            }

            id = id + 1;
         }
      }

      return this.convertedFileSystem;
   }

    public void swapRange(int i, int j, int k, int h, List<Integer> list){
        int aux;
        int index = 0;

        while(index < j-i+1){
            aux = list.get(k + index);
            list.set(k + index, list.get(i + index));
            list.set(i + index, aux);
            index++;
        }
    }

    public List<Integer> compact() {
        Set<Integer> treatedIds = new HashSet<>();
        int size = this.convertedFileSystem.size();

        for(int i = size-1 ; i > 0; i--) {
            int neededSpace = 0;
            if(this.convertedFileSystem.get(i) != -1 && !treatedIds.contains(this.convertedFileSystem.get(i))) {
                int id = this.convertedFileSystem.get(i);
                treatedIds.add(id);
                neededSpace = 1;

                int k = i;
                // Get the needed size for this file block
                while(k > 0 && this.convertedFileSystem.get(--k) == id){
                    neededSpace++;
                }

                for(int j = 0; j < i; j++) {
                    if (this.convertedFileSystem.get(j) == -1) {
                        int space = 1;
                        int h = j;
                        while(this.convertedFileSystem.get(++h) == -1){
                            space++;
                        }

                        // Block file fits in available space
                        if(neededSpace <= space){
                            swapRange(j, j+neededSpace-1, i-neededSpace+1, i,this.convertedFileSystem);
                            break;
                        }
                    }
                }
            }


            int ref = i - (neededSpace - 1); 
            while(i > ref){
                System.out.print(""); i--;
            }
        }

        this.compactedFileSystem = new ArrayList<>(this.convertedFileSystem);
        return this.compactedFileSystem;
    }

    public long calculateCheckSum() {
      long checkSum = 0L;

      for(int i = 0; i < this.compactedFileSystem.size(); ++i) {
         if ((Integer)this.compactedFileSystem.get(i) != -1) {
            checkSum += i * this.compactedFileSystem.get(i);
         }
      }

      return checkSum;
    }

   public List<Integer> getConvertedFS() {
      return this.convertedFileSystem;
   }
}
