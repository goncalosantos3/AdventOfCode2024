package day15;

import java.util.ArrayList;
import java.util.List;

public class A {
    private List<List<Character>> map;
    private Robot robot;

    public A(List<List<Character>> map, Robot r){
        this.map = map;
        this.robot = r;
    }

    public int moveUp(int x, int y){
        int upMoves = 1;
        int newY = y - 1;

        if(map.get(newY).get(x) == '#'){  // Wall
            return 0;
        }else if(map.get(newY).get(x) == 'O'){ // Box
            int upMovesAux = moveUp(x, newY);
            if(upMovesAux == 0){
                return 0;
            }
            upMoves += upMovesAux;
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
        }else if(map.get(y).get(newX) == 'O'){ // Box
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

    public int moveDown(int x, int y){
        int downMoves = 1;
        int newY = y + 1;

        if(map.get(newY).get(x) == '#'){  // Wall
            return 0;
        }else if(map.get(newY).get(x) == 'O'){ // Box
            int downMovesAux = moveDown(x, newY);
            if(downMovesAux == 0){
                return 0;
            }
            downMoves += downMovesAux;
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
        }else if(map.get(y).get(newX) == 'O'){ // Box
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
        int x = robot.getPosition().getX(); int y = robot.getPosition().getY();
        List<Integer> robotMovements = this.robot.getMoves();
        int moves;

        int move_nr = 1;
        for(Integer move: robotMovements){
            if(move == 0){ // Up
                moves = moveUp(x, y);
                if(moves > 0){
                    int i = 0;
                    y -= moves;
                    while(i < moves){   
                        map.get(y).set(x, map.get(y+1).get(x)); 
                        y++;
                        i++;
                    }
                    map.get(y).set(x, '.');
                    y--;
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
                moves = moveDown(x, y);
                if(moves > 0){
                    int i = 0;
                    y += moves;
                    while(i < moves){   
                        map.get(y).set(x, map.get(y-1).get(x)); 
                        y--;
                        i++;
                    }
                    map.get(y).set(x, '.');
                    y++;
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
            System.out.println("Move " + move_nr++ + "(" + moves + ")");
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

