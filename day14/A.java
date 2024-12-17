import java.util.List;

public class A {
    private List<Robot> robots;
    private int mapHeight;
    private int mapWidth;

    public A(List<Robot> robots, int mapWidth, int mapHeight){
        this.robots = robots;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    } 

    public void simulateSecond(){
        for(Robot r: this.robots){
            int px = r.getPx(); int py = r.getPy();
            int vx = r.getVx(); int vy = r.getVy();

            // Robot exceeds width of the map
            if(px + vx < 0){ // Exceeds to the left -> vx is negative
                int diff = Math.abs(vx + px);
                r.setPx(mapWidth-diff);
            }else if(px+vx > mapWidth-1){ // Exceeds to the left -> vx is positive
                int diff = vx - ((mapWidth-1)-px);
                r.setPx(diff-1);
            }else{
                r.setPx(px+vx);
            }
            
            if(py + vy < 0){ // Exceeds the map on top -> vy is negative
                int diff = Math.abs(vy + py);
                r.setPy(mapHeight-diff);
            }else if(py + vy > mapHeight-1){
                int diff = vy - ((mapHeight-1) - py);
                r.setPy(diff-1);
            }else{
                r.setPy(py+vy);
            }
        }
    }

    public int calculateSafetyFactor(){
        int []quadrants = {0, 0, 0, 0};
        int midX = (int) Math.floor(mapWidth/2);
        int midY = (int) Math.floor(mapHeight/2);

        for(Robot r: this.robots){
            int px = r.getPx(); int py = r.getPy();
            
            if(px < midX && py < midY){
                quadrants[0]++;
            }else if(px > midX && py < midY){
                quadrants[1]++;
            }else if(px < midX && py > midY){
                quadrants[2]++;
            }else if(px > midX && py > midY){
                quadrants[3]++;
            }
        }
        
        return quadrants[0]*quadrants[1]*quadrants[2]*quadrants[3];
    }

    // Debugging method
    public String mapWithBots(){
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<mapHeight; i++){
            for(int j=0; j<mapWidth; j++){
                for(Robot r: this.robots){
                    if(r.getPx() == j && r.getPy() == i){
                        count++;
                    }   
                }

                if(count > 0){
                    sb.append(Integer.toString(count));
                }else{
                    sb.append(".");
                }
                count = 0;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
