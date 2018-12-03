public class Body {
    private double myXPos;
    private double myYPos;
    private double myXVel;
    private double myYVel;
    private double myMass;
    private String myFileName;

    /**
     * Create a Body given parameters
     * @param xp - initial x position
     * @param yp - initial y position
     * @param xv - initial x velocity
     * @param yv - initial y velocity
     * @param mass - mass of object
     * @param filename - filename of image for object animation
     */
    public Body (double xp, double yp, double xv, double yv, double mass, String filename){
        myXPos = xp;
        myYPos = yp;
        myXVel = xv;
        myYVel = yv;
        myMass = mass;
        myFileName = filename;
    }

    /**
     * Copy constructor: copy instance variables from one body to this body
     * @param b - body used to initialize this body
     */
    public Body (Body b){
        myXPos = b.getX();
        myYPos = b.getY();
        myXVel = b.getXVel();
        myYVel = b.getYVel();
        myMass = b.getMass();
        myFileName = b.getName();
    }

    /**
     * Return x position
     * @return myXPos (x position)
     */
    public double getX(){
        return myXPos;
    }

    /**
     * Return y position
     * @return myYPos (y position)
     */
    public double getY(){
        return myYPos;
    }

    /**
     * Return x velocity
     * @return myXVel (x velocity)
     */
    public double getXVel(){
        return myXVel;
    }

    /**
     * Return y velocity
     * @return myYVel (y velocity)
     */
    public double getYVel(){
        return myYVel;
    }

    /**
     * Return mass of object
     * @return myMass (object mass)
     */
    public double getMass(){
        return myMass;
    }

    /**
     * Return name of file
     * @return myFileName (name of file)
     */
    public String getName(){
        return myFileName;
    }

    /**
     * Return the distance between this body and another
     * @param b - the other body to which distance is calculated
     * @return distance between this body and body b
     */
    public double calcDistance(Body b){
        return Math.sqrt((Math.pow(myXPos - b.getX(), 2)) + (Math.pow(myYPos - b.getY(), 2)));
    }

    /**
     * Return the force exerted on this body by body p
     * @param p - body exerting force on this body
     * @return - force exerted on this body by body p
     */
    public double calcForceExertedBy(Body p){
        return (6.67e-11 * myMass * p.getMass()) / (Math.pow(calcDistance(p), 2));
    }

    /**
     * Return force exerted in X direction on this body by body p
     * @param p - body exerting force on this body
     * @return - return X component of force exerted on this body by body p
     */
    public double calcForceExertedByX (Body p){
        return (calcForceExertedBy(p) * (p.getX() - myXPos)) / calcDistance(p);
    }

    /**
     * Return force exerted in Y direction on this body by body p
     * @param p - body exerting force on this body
     * @return - return Y component of force exerted on this body by body p
     */
    public double calcForceExertedByY (Body p){
        return (calcForceExertedBy(p) * (p.getY() - myYPos)) / calcDistance(p);
    }

    /**
     * Return total force exerted in X direction on this body by bodies in array
     * @param bodies - array of all bodies
     * @return - return total force exerted in X direction on this body by bodies in array
     */
    public double calcNetForceExertedByX (Body [] bodies){
        double xForce = 0;
        for (Body b : bodies){
            if (!b.equals(this)){
                xForce += calcForceExertedByX(b);
            }
        }
        return xForce;
    }

    /**
     * Return total force exerted in Y direction on this body by bodies in array
     * @param bodies - array of all bodies
     * @return - return total force exerted in Y direction on this body by bodies in array
     */
    public double calcNetForceExertedByY (Body [] bodies){
        double yForce = 0;
        for (Body b : bodies){
            if (!b.equals(this)){
                yForce += calcForceExertedByY(b);
            }
        }
        return yForce;
    }

    /**
     * Update myXPos, myYPos, myXVel, myYVel, with given parameters and calculations
     * @param deltaT - incremental time steps
     * @param xforce - net force exerted in X direction by other bodies
     * @param yforce - net force exerted in Y direction by other bodies
     */
    public void update(double deltaT, double xforce, double yforce){
        double accelX = xforce / myMass;
        double accelY = yforce / myMass;
        double newXVel = myXVel + (deltaT * accelX);
        double newYVel = myYVel + (deltaT * accelY);
        double newXPos = myXPos + (deltaT * newXVel);
        double newYPos = myYPos + (deltaT * newYVel);
        myXPos = newXPos;
        myYPos = newYPos;
        myXVel = newXVel;
        myYVel = newYVel;
    }

    /**
     * Draws body
     */
    public void draw(){
        StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
    }
}
