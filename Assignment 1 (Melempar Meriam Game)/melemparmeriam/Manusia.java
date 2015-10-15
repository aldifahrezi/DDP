package melemparmeriam;

/**
 * Kelas ini memodelkan Manusia yang terdapat pada game.
 * 
 * @author Aldi Fahrezi
 * @version 1.0.0
 */

public class Manusia
{	
	protected double maxHealth;
	protected double health;
	protected double distanceDamageResistance;
	protected double maxMovement;
	protected double position;
	
	public Manusia() {
		maxHealth = 100.0;
		health = maxHealth;
		distanceDamageResistance = 1.0;
		maxMovement = 0.0;
		position = 100.0;
	}
	
	public Manusia(double position) {
		maxHealth = 100.0;
		health = maxHealth;
		distanceDamageResistance = 1.0;
		maxMovement = 0.0;
		this.position = position;
	}
	
	public Manusia(double maxHealth, double distanceDamageResistance, double maxMovement, double position) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.distanceDamageResistance = distanceDamageResistance;
		this.maxMovement = maxMovement;
		this.position = position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public double getPosition() {
		return position;
	}
	
	public void setHealth(double health) {
		if (health > maxHealth) {
			return;
		}
		else {
			this.health = health;
		}
	}

	public double getHealth() {
		return health;
	}
	
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxMovement(double maxMovement) {
		this.maxMovement = maxMovement;
	}

	public double getMaxMovement() {
		return maxMovement;
	}

	public double getDistanceDamageResistance() {
		return distanceDamageResistance;
	}

	public boolean isAlive() {
		if (health < MeriamConstants.EPS) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void giveDamage(double initialDamage, double attackPosition) {
		double attackDistance = Math.abs(position - attackPosition);
		
		double damage = initialDamage - (distanceDamageResistance * attackDistance);
		if (damage < 0) {
			damage = 0.0;
		}
		
		health = health - damage;

		if (health < MeriamConstants.EPS) health = 0.0;
	}

	public void giveDamage(double damage) {
		if (damage < 0) {
			damage = 0.0;
		}
		
		health = health - damage;

		if (health < MeriamConstants.EPS) health = 0.0;
	}
	
	public boolean move(double distance) {
		if (Math.abs(distance) > maxMovement) {
			return false;
		}
		
		position = position + distance;
		
		return true;
	}
}
