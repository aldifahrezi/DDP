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
	
	/**
	* Constructor ini adalah constructor default untuk objek Manusia
	*
	*/
	public Manusia() {
		maxHealth = 100.0;
		health = maxHealth;
		distanceDamageResistance = 1.0;
		maxMovement = 0.0;
		position = 100.0;
	}
	
	/**
	* Constructor ini adalah constructor yang membawa parameter position sebagai nilai awal
	* 
	* @param position posisi objek pada sumbu x
	*/
	public Manusia(double position) {
		maxHealth = 100.0;
		health = maxHealth;
		distanceDamageResistance = 1.0;
		maxMovement = 0.0;
		this.position = position;
	}
	
	/**
	* Constructor ini adalah constructor yang membawa berbagai parameter sebagai nilai awal
	* 
	* @param maxHealth health maksimal yang bisa dimiliki objek
	* @param distanceDamageResistance besar ketahanan objek terhadap jarak objek terhadap bola meriam.
	* @param maxMovement besarnya nilai movement yang dapat dilakukan dalam 1 turn
	* @param position posisi objek pada sumbu x
	*/
	public Manusia(double maxHealth, double distanceDamageResistance, double maxMovement, double position) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.distanceDamageResistance = distanceDamageResistance;
		this.maxMovement = maxMovement;
		this.position = position;
	}

	/**
	* Method yang mengset posisi x yang dimiliki objek
	*
	* @param position posisi x baru yang dimiliki objek
	*/
	public void setPosition(double position) {
		this.position = position;
	}

	/**
	* Method yang mengembalikan posisi x yang dimiliki objek
	*
	* @return double, yaitu posisi objek pada sumbu x
	*/
	public double getPosition() {
		return position;
	}
	
	/**
	* Method yang mengeset health objek tanpa melewati batas max health
	*
	* @param health health baru yang dimiliki objek
	*/
	public void setHealth(double health) {
		if (health > maxHealth) {
			return;
		}
		else {
			this.health = health;
		}
	}

	/**
	* Method yang mengembalikan health yang dimiliki objek
	*
	* @return health yang dimiliki objek
	*/
	public double getHealth() {
		return health;
	}
	
	/**
	* Method yang mengubah max health yang dimiliki objek
	*
	* @param maxHealth max health baru yang dimiliki objek
	*/
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	* Method yang mengembalikan max health yang dimiliki objek
	*
	* @return max health yang dimiliki objek
	*/
	public double getMaxHealth() {
		return maxHealth;
	}

	/**
	* Method yang mengubah batas movement yang bisa dilakukan objek dalam 1 turn
	*
	* @param maxMovement max movement baru yang dimiliki objek
	*/
	public void setMaxMovement(double maxMovement) {
		this.maxMovement = maxMovement;
	}

	/**
	* Method yang mengembalikan max movement yang bisa dilakukan objek dalam 1 turn
	*
	* @return max movement yang bisa dilakukan objek dalam 1 turn
	*/
	public double getMaxMovement() {
		return maxMovement;
	}

	/**
	* Method yang mengembalikan resistance objek terhadap jarak serangan bola meriam
	*
	* @return nilai distanceDamageResistance objek
	*/
	public double getDistanceDamageResistance() {
		return distanceDamageResistance;
	}

	/**
	* Method yang membalikkan nilai dalam boolean apakah objek masih hidup atau tidak
	*
	* @return 
	*	- true, jika objek masih hidup
	*	- false, jika objek sudah mati
	*/
	public boolean isAlive() {
		if (health < MeriamConstants.EPS) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	* Method yang memberikan damage pada objek dengan rumus <i>initialDamage - (distanceDamageResistance * attackDistance)</i>
	*
	* @param initialDamage Damage awal sebelum diperhitungkan distanceDamageResistance
	* @param attackPosition Letak serangan dilakukan
	*/
	public void giveDamage(double initialDamage, double attackPosition) {
		double attackDistance = Math.abs(position - attackPosition);
		
		double damage = initialDamage - (distanceDamageResistance * attackDistance);
		if (damage < 0) {
			damage = 0.0;
		}
		
		health = health - damage;

		if (health < MeriamConstants.EPS) health = 0.0;
	}
	
	/**
	* Method yang menggerakkan posisi dari objek, (tidak melewati batas maxMovement objek)
	*
	* @param distance perpindahan yang ingin dilakukan pada objek
	*
	* @return
	* 	- true, jika gerakan berhasil dilakukan
	*	- false, jika gerakan tidak berhasil dilakukan
	*/
	public boolean move(double distance) {
		if (Math.abs(distance) > maxMovement) {
			return false;
		}
		
		position = position + distance;
		
		return true;
	}
}
