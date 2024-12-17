public class Robot {
    private int px;
    private int py;
    private int vx;
    private int vy;

    public Robot(int px, int py, int vx, int vy){
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        // System.out.println("Robot:\nPosition: " + px + "," + py + "; Velocity: " + vx + "," + vy);
    }

    public int getPx(){return this.px;}
    public void setPx(int px){this.px = px;}
    public int getPy(){return this.py;}
    public void setPy(int py){this.py = py;}
    public int getVx(){return this.vx;}
    public void setVx(int vx){this.vx = vx;}
    public int getVy(){return this.vy;}
    public void setVy(int vy){this.vy = vy;}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Robot position: "); sb.append(px); sb.append(","); sb.append(py);
        sb.append("; ");
        sb.append("Robot velocity: "); sb.append(vx); sb.append(","); sb.append(vy);

        return sb.toString();
    }
}
