package day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class B {
    private List<List<Character>> map;
    private Robot robot;

    public B(List<List<Character>> map, Robot r){
        this.map = map;
        this.robot = r;
    }

    public void cleanUp(){
        for(int i=0; i<this.map.size(); i++){
            for(int j=0; j<this.map.get(0).size()-1; j++){
                if(this.map.get(i).get(j) == '['){
                    if(this.map.get(i).get(j) == this.map.get(i).get(j+1)){
                        this.map.get(i).set(j, '.');
                    }
                }else if(this.map.get(i).get(j) == ']'){
                    if(this.map.get(i).get(j) == this.map.get(i).get(j+1)){
                        this.map.get(i).set(j+1, '.');
                    }
                }else if(this.map.get(i).get(j) == '@'){
                    if(this.map.get(i).get(j-1) == '['){
                        this.map.get(i).set(j-1, '.');
                    }else if (this.map.get(i).get(j+1) == ']'){
                        this.map.get(i).set(j+1, '.');
                    }
                }
            }
        }
    }

    public void moveUp(int x, int y){
        int newY = y - 1;

        if(map.get(newY).get(x) == '[' || map.get(newY).get(x) == ']'){
            moveUp(x, newY);
            if(map.get(newY).get(x) == '['){
                moveUp(x+1, newY);
            }else{
                moveUp(x-1, newY);
            }
            this.map.get(newY).set(x, this.map.get(y).get(x));
        }else if(map.get(newY).get(x) == '.'){
            this.map.get(newY).set(x, this.map.get(y).get(x));
        }

        if(this.map.get(y).get(x) == '@'){
            this.map.get(newY).set(x, '@');
        }
    }

    public int checkMoveUp(int x, int y){
        int upMoves = 1;
        int newY = y - 1;

        if(map.get(newY).get(x) == '#'){  // Wall
            return 0;
        }else if(map.get(newY).get(x) == '[' || map.get(newY).get(x) == ']'){ // Box
            int upMovesAux1 = checkMoveUp(x, newY);
            int upMovesAux2;
            if(map.get(newY).get(x) == '['){
                upMovesAux2 = checkMoveUp(x+1, newY);
            }else{
                upMovesAux2 = checkMoveUp(x-1, newY);
            }
               
            if(upMovesAux1 == 0 || upMovesAux2 == 0){ // Check if the box hits a wall in either side
                return 0;
            }
            upMoves += upMovesAux1;
        }else{ // Empty
            return 1;
        }
        return upMoves;
    }

    public int moveRight(int x, int y){
        int rightMoves = 1;
        int newX = x + 1;

        if(map.get(y).get(newX) == '#'){  // Wall
            return 0;
        }else if(map.get(y).get(newX) == '[' || map.get(y).get(newX) == ']'){ // Box
            int rightMovesAux = moveRight(newX, y);
            if(rightMovesAux == 0){
                return 0;
            }
            rightMoves += rightMovesAux;
        }else{ // Empty
            return 1;
        }

        return rightMoves;
    }

    public void moveDown(int x, int y){
        int newY = y + 1;

        if(map.get(newY).get(x) == '[' || map.get(newY).get(x) == ']'){
            moveDown(x, newY);
            if(map.get(newY).get(x) == '['){
                moveDown(x+1, newY);
            }else{
                moveDown(x-1, newY);
            }

            this.map.get(newY).set(x, this.map.get(y).get(x));
        }else if(map.get(newY).get(x) == '.'){
            this.map.get(newY).set(x, this.map.get(y).get(x));
        }
    }

    public int checkMoveDown(int x, int y){
        int downMoves = 1;
        int newY = y + 1;

        if(map.get(newY).get(x) == '#'){  // Wall
            return 0;
        }else if(map.get(newY).get(x) == '[' || map.get(newY).get(x) == ']'){ // Box
            int downMoves1 = checkMoveDown(x, newY);
            int downMoves2;
            if(map.get(newY).get(x) == '['){
                downMoves2 = checkMoveDown(x+1, newY);
            }else{
                downMoves2 = checkMoveDown(x-1, newY);
            }
               
            if(downMoves1 == 0 || downMoves2 == 0){ // Check if the box hits a wall in either side
                return 0;
            }
            downMoves += downMoves1;
        }else{ // Empty
            return 1;
        }
        return downMoves;
    }

    public int moveLeft(int x, int y){
        int leftMoves = 1;
        int newX = x - 1;

        if(map.get(y).get(newX) == '#'){  // Wall
            return 0;
        }else if(map.get(y).get(newX) == '[' || map.get(y).get(newX) == ']'){ // Box
            int leftMovesAux = moveLeft(newX, y);
            if(leftMovesAux == 0){
                return 0;
            }
            leftMoves += leftMovesAux;
        }else{ // Empty
            return 1;
        }

        return leftMoves;
    }

    public void performMovements(){
        Map<Integer, String> maped_moves = new HashMap<>(); maped_moves.put(0, "up"); maped_moves.put(1, "right"); maped_moves.put(2, "down"); maped_moves.put(3, "left"); 
        int x = robot.getPosition().getX(); int y = robot.getPosition().getY();
        List<Integer> robotMovements = this.robot.getMoves();
        int moves;
        
        int move_nr = 1;
        for(Integer move: robotMovements){
            if(move == 0){ // Up
                moves = checkMoveUp(x, y);
                if(moves > 0){
                    moveUp(x, y);
                    cleanUp();
                    this.map.get(y--).set(x, '.');
                }      
            }else if(move == 1){ // Right
                moves = moveRight(x, y);
                if(moves > 0){
                    int i = 0;
                    x += moves;
                    while(i < moves){   
                        map.get(y).set(x, map.get(y).get(x-1)); 
                        x--;
                        i++;
                    }
                    map.get(y).set(x, '.');
                    x++;
                }
            }else if(move == 2){ // Down
                moves = checkMoveDown(x, y);
                if(moves > 0){
                    moveDown(x, y);
                    cleanUp();
                    this.map.get(y++).set(x, '.');
                }
            }else{ // Left
                moves = moveLeft(x, y);
                if(moves >  0){
                    int i = 0;
                    x -= moves;
                    while(i < moves){   
                        map.get(y).set(x, map.get(y).get(x+1)); 
                        x++;
                        i++;
                    }
                    map.get(y).set(x, '.');
                    x--;
                }
            }
            move_nr++;
            System.out.println("Move " + move_nr + ": " + maped_moves.get(move) + " (" + moves + ")");
            System.out.println(this.mapToString());
        }

        this.robot.setPosition(new Position(x, y)); // Update robot position
        this.robot.setMoves(new ArrayList<>()); // All moves were performed
    }

    public int getSumGPSBoxes(){
        int sum = 0;

        for(int i=0; i<this.map.size(); i++){
            for(int j=0; j<this.map.get(0).size(); j++){
                if(this.map.get(i).get(j) == 'O'){
                    sum += 100*i + j;
                }
            }
        }

        return sum;
    }

    public String mapToString(){
        StringBuilder sb = new StringBuilder();

        for(List<Character> row: this.map){
            for(Character c: row){
                sb.append(c);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

