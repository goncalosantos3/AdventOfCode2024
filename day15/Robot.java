package day15;
import java.util.List;

public class Robot{
    // 0 -> Up; 1 -> Right; 2 -> Down; 3 -> Left 
    private Position p;
    private List<Integer> moves;

    public Robot(int x, int y){
        this.p = new Position(x, y);
    }

    public void setMoves(List<Integer> moves){
        this.moves = moves;
    }

    public void setPosition(Position p){
        this.p = p;
    }

    public Position getPosition(){ return this.p;}
    public List<Integer> getMoves(){ return this.moves;}

    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        sb.append("Robot at position: " + this.p.getX() + "; " + this.p.getY() + "!\n");
        sb.append("Robot movements:\n");
        for(int i=0; i<this.moves.size(); i++){
            if(this.moves.get(i) == '0'){
                sb.append("^");
            }else if(this.moves.get(i) == '1'){
                sb.append(">");
            }else if(this.moves.get(i) == '2'){
                sb.append("v");
            }else if(this.moves.get(i) == '3'){
                sb.append("<");
            }else{
                sb.append(Integer.toString(this.moves.get(i)));
            }
        }

        return sb.toString();
    }
}