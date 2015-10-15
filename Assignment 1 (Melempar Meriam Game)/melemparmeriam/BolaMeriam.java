package melemparmeriam;

/**
 * Kelas ini memodelkan BolaMeriam yang digunakan oleh Meriam.
 * 
 * @author Aldi Fahrezi
 * @version 1.0.0
 */

public class BolaMeriam
{	
	protected double damageCoefficient;
	protected double speedX;
	protected double speedY;
	protected double posX;
	protected double posY;
	protected double presentTime;
	protected double collisionTime;
	
	public BolaMeriam() {
		damageCoefficient = 1.0;
		speedX = 0.0;
		speedY = 0.0;
		posX = 0.0;
		posY = 0.0;
		presentTime = 0.0;
		collisionTime = 0.0;
	}
	
	public BolaMeriam(double angle, double speed, double posX, double posY, double damageCoefficient) {
		double angleRad = angle * Math.acos(-1) / 180;
		double speedX = speed * Math.cos(angleRad);
		double speedY = speed * Math.sin(angleRad);
		
		this.damageCoefficient = damageCoefficient;
		this.speedX = speedX;
		this.speedY = speedY;
		this.posX = posX;
		this.posY = posY;
		this.presentTime = 0.0;
		collisionTime = getCollisionTime();
	}

	public BolaMeriam(double angle, double speed, double posX, double posY) {
		double angleRad = angle * Math.acos(-1) / 180;
		double speedX = speed * Math.cos(angleRad);
		double speedY = speed * Math.sin(angleRad);
		
		this.damageCoefficient = 1.0;
		this.speedX = speedX;
		this.speedY = speedY;
		this.posX = posX;
		this.posY = posY;
		this.presentTime = 0.0;
		collisionTime = getCollisionTime();
	}
	
	public void setInitialValues (double angle, double speed, double posX, double posY) {
		double angleRad = angle * Math.acos(-1) / 180;
		double speedX = speed * Math.cos(angleRad);
		double speedY = speed * Math.sin(angleRad);
		
		this.speedX = speedX;
		this.speedY = speedY;
		this.posX = posX;
		this.posY = posY;
		collisionTime = getCollisionTime();
	}

	public void setDamageCoefficient(double damageCoefficient) {
		this.damageCoefficient = damageCoefficient;
	}

	public double getCollisionTime() {
		return presentTime + Math.abs(2*speedY/MeriamConstants.GRAVITY);
	}
	
	public void fastForward(double time) {
		if (isFlying() == false) return;
		if (time < 0) return;
		if (presentTime + time > collisionTime) {
			fastForward(collisionTime - presentTime);
			return;
		}
		
		posX += speedX * time;
		
		posY += speedY * time + (0.5 * MeriamConstants.GRAVITY * time * time);
		if (Math.abs(posY) < MeriamConstants.EPS) posY = 0.0;
		
		speedY = speedY + (MeriamConstants.GRAVITY * time);
		
		presentTime = presentTime + time;
	}

	public boolean isFlying () {
		if (speedY < MeriamConstants.EPS && posY < MeriamConstants.EPS) {
			return false;
		}
		else {
			return true;
		}
	}

	public double getSpeed() {
		return Math.sqrt( (speedY * speedY) + (speedX * speedX) );
	}

	public double getDamage() {
		return getSpeed() * damageCoefficient;
	}

	public double getDamageCoefficient() {
		return damageCoefficient;
	}
	
	public double getTime() {
		return presentTime;
	}
	
	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
}
