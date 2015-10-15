package melemparmeriam;

/**
 * Kelas ini memodelkan Bola Meriam yang digunakan oleh Meriam.
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
	
	/**
	* Constructor ini merupakan constructor default tanpa param
	*/
	public BolaMeriam() {
		damageCoefficient = 1.0;
		speedX = 0.0;
		speedY = 0.0;
		posX = 0.0;
		posY = 0.0;
		presentTime = 0.0;
		collisionTime = 0.0;
	}
	
	/**
	* Constructor ini mengeset semua state yang dimiliki class BolaMeriam
	*
	* @param angle sudut tembak meriam
	* @param speed kecepatan awal BolaMeriam
	* @param posX posisi X Bola Meriam
	* @param posY posisi Y Bola Meriam
	* @param damageCoefficient besaran yang mengendalikan damage yang dihasilkan Bola Meriam
	* 
	*/
	public BolaMeriam(double angle, double speed, double posX, double posY, double damageCoefficient) {
		// Konversi angle dan speed menjadi speedX, speedY
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

	/**
	* Constructor ini mengset semua state yang dimiliki class BolaMeriam, dengan damageCoefficient diset default (1.0)
	*
	* @param angle sudut tembak meriam
	* @param speed kecepatan awal BolaMeriam
	* @param posX posisi X Bola Meriam
	* @param posY posisi Y Bola Meriam
	*
	*/
	public BolaMeriam(double angle, double speed, double posX, double posY) {
		// Konversi angle dan speed menjadi speedX, speedY
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
	
	/**
	* Method ini mengubah nilai nilai awal yang dimiliki oleh objek dari class BolaMeriam
	*
	* @param angle sudut tembak meriam
	* @param speed kecepatan awal BolaMeriam
	* @param posX posisi X Bola Meriam
	* @param posY posisi Y Bola Meriam
	*/
	public void setInitialValues (double angle, double speed, double posX, double posY) {
		// Konversi angle dan speed menjadi speedX, speedY
		double angleRad = angle * Math.acos(-1) / 180;
		double speedX = speed * Math.cos(angleRad);
		double speedY = speed * Math.sin(angleRad);
		
		this.speedX = speedX;
		this.speedY = speedY;
		this.posX = posX;
		this.posY = posY;
		collisionTime = getCollisionTime();
	}

	/**
	* Method untuk mengubah damageCoefficient dari objek
	*
	* @param damageCoefficient damageCoefficient baru
	*/
	public void setDamageCoefficient(double damageCoefficient) {
		this.damageCoefficient = damageCoefficient;
	}

	/**
	* Method untuk mendapatkan collisionTime dari BolaMeriam, dipanggil melalui constructor
	*
	* @return waktu terjadinya collision BolaMeriam
	*/
	public double getCollisionTime() {
		return presentTime + Math.abs(2*speedY/MeriamConstants.GRAVITY);
	}
	
	/**
	* Method untuk memajukan state BolaMeriam selama <i>time</i> detik (sesuai hukum fisika)
	*
	* @param time Waktu fast forward
	*/
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

	/**
	* Method yang memberi tahu keadaan objek sekarang, (masih melayang atau berhenti)
	*
	* @return boolean true, jika objek belum berhenti, dan false jika objek sudah berhenti.
	*/
	public boolean isFlying () {
		if (speedY < MeriamConstants.EPS && posY < MeriamConstants.EPS) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	* Method yang mengembalikan kecepatan tangensial objek
	*
	* @return double, yaitu besar kecepatan tangensial
	*/
	public double getSpeed() {
		return Math.sqrt( (speedY * speedY) + (speedX * speedX) );
	}

	/**
	* Method yang mengembalikan damage yang dihasilkan bola meriam
	*
	* @return double, yaitu besar damage
	*/
	public double getDamage() {
		return getSpeed() * damageCoefficient;
	}

	/**
	* Method yang mengembalikan damageCoefficient yang dimiliki objek
	*
	* @return double, yaitu damageCoefficient
	*/
	public double getDamageCoefficient() {
		return damageCoefficient;
	}
	
	/**
	* Method yang mengembalikan waktu yang sudah berjalan di objek
	*
	* @return double, yaitu waktu yang sudah berjalan di objek
	*/
	public double getTime() {
		return presentTime;
	}
	
	/**
	* Method yang mengembalikan posisi objek di sumbu x
	*
	* @return posisi objek di sumbu x
	*/
	public double getPosX() {
		return posX;
	}
	
	/**
	* Method yang mengembalikan posisi objek di sumbu y
	*
	* @return posisi objek di sumbu y
	*/
	public double getPosY() {
		return posY;
	}
}
