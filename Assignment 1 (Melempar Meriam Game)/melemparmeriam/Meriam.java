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
	
	/**
	* Constructor ini adalah constructor default untuk objek Meriam
	*
	*/
	public Meriam() {
		speed = 100.0;
		angle = 45;
		position = 0.0;
		bola = new BolaMeriam();
		isLoaded = true;
	}
	
	/**
	* Constructor ini adalah constructor objek Manusia dengan parameter speed dan position
	*
	* @param speed kecepatan awal tembak meriam
	* @param position posisi x objek meriam
	*/
	public Meriam(double speed, double position) {
		this.speed = speed;
		this.position = position;
		angle = 45;
		bola = new BolaMeriam();
		isLoaded = true;
	}

	/**
	* Constructor ini adalah constructor objek Manusia dengan parameter speed, position, bola
	*
	* @param speed kecepatan awal tembak meriam
	* @param position posisi x objek meriam
	* @param bola objek BolaMeriam yang dimasukkan pada meriam
	*/
	public Meriam(double speed, double position, BolaMeriam bola) {
		this.speed = speed;
		this.position = position;
		angle = 45;
		this.bola = bola;
		isLoaded = true;
	}
	
	/**
	* Method ini mereload meriam sehingga dapat digunakan (hanya jika meriam sedang tidak memiliki bola)
	*
	* @param bola objek BolaMeriam yang dimasukkan pada meriam
	*
	* @return
	*	- true, jika reload berhasil dilakukan
	*	- false, jika reload tidak berhasil dilakukan
	*/
	public boolean reload(BolaMeriam bola) {
		if (isLoaded) {
			return false;
		}

		this.bola = bola;
		isLoaded = true;

		return true;
	}

	/**
	* Method ini melepas bola meriam dari meriam (hanya jika meriam sedang memiliki bola)
	*
	* @return
	*	- true, jika unload berhasil dilakukan
	*	- false, jika unload tidak berhasil dilakukan
	*/
	public boolean unload() {
		if (!isLoaded) {
			return false;
		}

		this.bola = null;
		isLoaded = false;

		return true;
	}

	/**
	* Method ini mengembalikan kecepatan tembak yang dimiliki meriam
	*
	* @return kecepatan tembak yang dimiliki meriam
	*/
	public double getSpeed() {
		return speed;
	}

	/**
	* Method ini mengeset kecepatan tembak yang dimiliki meriam (harus positif)
	*
	* @param speed kecepatan tembak baru yang dimiliki meriam
	*
	* @return
	* 	- true, jika setSpeed berhasil dilakukan
	*	- false, jika setSpeed gagal dilakukan
	*/
	public boolean setSpeed(double speed) {
		if (speed < 0) {
			return false;
		}
		
		this.speed = speed;
		
		return true;
	}
	
	/**
	* Method ini mengembalikan sudut tembak yang dimiliki meriam (dalam derajat)
	*
	* @return sudut tembak yang dimiliki meriam (dalam derajat)
	*/
	public double getAngle() {
		return angle;
	}

	/**
	* Method ini mengubah sudut tembak yang dimiliki meriam (dalam derajat)
	* Harus 
	*
	* @param angle sudut tembak baru yang dimiliki meriam
	*
	* @return
	*	- true, jika setAngle berhasil dilakukan
	*	- false, jika setAngle gagal dilakukan
	*/
	public boolean setAngle(double angle) {
		if (angle < MeriamConstants.EPS || angle > 180 - MeriamConstants.EPS) {
			return false;
		}
		
		this.angle = angle;
		
		return true;
	}
	
	/**
	* Method ini mengembalikan posisi x objek
	*
	* @return posisi x objek
	*/
	public double getPosition() {
		return position;
	}

	/**
	* Method ini mengubah posisi x objek
	*
	* @param position posisi x objek baru
	*/
	public void setPosition(double position) {
		this.position = position;
	}
	
	/**
	* Method ini digunakan untuk menembakkan meriam 
	*
	* @return 
	*	- mengembalikan bola dalam meriam yang telah ditembakkan jika isLoaded == true (jika ada bola)
	*	- mengembalikan null jika isLoaded == false (tidak ada bola)
	*/
	public BolaMeriam fire() {
		if (isLoaded == false) return null;
		
		this.bola.setInitialValues(angle, speed, position, 0);
		
		BolaMeriam bola = this.bola;

		this.bola = null;
		isLoaded = false;

		return bola;
	}
	
	/**
	* Method ini digunakan untuk mengecek apakah meriam berisi bola
	* 
	* @return
	*	- true, jika meriam berisi bola
	*	- false, jika meriam tidak berisi bola
	*/
	public boolean isLoaded() {
		return this.isLoaded;
	}
}
