package melemparmeriam;

/**
 * Kelas ini memodelkan Meriam yang terdapat di game.
 * 
 * @author Aldi Fahrezi
 * @version 1.0.0
 */

public class Meriam
{
	protected double speed;
	protected double angle; //in degrees
	protected boolean isLoaded;
	protected double position;
	protected BolaMeriam bola;
	
	public Meriam() {
		speed = 100.0;
		angle = 45;
		position = 0.0;
		bola = new BolaMeriam();
		isLoaded = true;
	}
	
	public Meriam(double speed, double position) {
		this.speed = speed;
		this.position = position;
		angle = 45;
		bola = new BolaMeriam();
		isLoaded = true;
	}

	public Meriam(double speed, double position, BolaMeriam bola) {
		this.speed = speed;
		this.position = position;
		angle = 45;
		this.bola = bola;
		isLoaded = true;
	}
	
	public boolean reload(BolaMeriam bola) {
		if (isLoaded) {
			return false;
		}

		this.bola = bola;
		isLoaded = true;

		return true;
	}

	public boolean unload() {
		if (!isLoaded) {
			return false;
		}

		this.bola = null;
		isLoaded = false;

		return true;
	}

	public double getSpeed() {
		return speed;
	}

	public boolean setSpeed(double speed) {
		if (speed < 0) {
			return false;
		}
		
		this.speed = speed;
		
		return true;
	}
	
	public double getAngle() {
		return angle;
	}

	public boolean setAngle(double angle) {
		if (angle < MeriamConstants.EPS || angle > 180 - MeriamConstants.EPS) {
			return false;
		}
		
		this.angle = angle;
		
		return true;
	}
	
	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}
	
	public BolaMeriam fire() {
		if (isLoaded == false) return null;
		
		this.bola.setInitialValues(angle, speed, position, 0);
		
		BolaMeriam bola = this.bola;

		this.bola = null;
		isLoaded = false;

		return bola;
	}
	
	public boolean isLoaded() {
		return this.isLoaded;
	}
}
