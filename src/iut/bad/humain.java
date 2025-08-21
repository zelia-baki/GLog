package iut.bad;

public class humain implements Consommation {

	@Override
	public String toString() {
		return "humain [nom=" + nom + ", prenom=" + prenom + ", age=" + age + "]";
	}
	
	
	public void details() {
	    System.out.println(this);
	}
	
	/* (non-Javadoc)
	 * @see iut.bad.Consommation#manger()
	 */
	
	@Override
	public void manger() {
	    System.out.println(prenom + " mange.");
	}

	/* (non-Javadoc)
	 * @see iut.bad.Consommation#boire()
	 */
	
	@Override
	public void boire() {
	    System.out.println(prenom + " boit.");
	}



	protected String nom;
	protected String prenom;
	protected int age;
	
	public humain(String nom, String prenom, int age) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	

	public humain() {
		super();
	}

}