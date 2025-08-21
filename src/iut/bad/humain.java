package iut.bad;

public class humain {

	@Override
	public String toString() {
		return "humain [nom=" + nom + ", prenom=" + prenom + ", age=" + age + "]";
	}
	
	
	public void details() {
	    System.out.println(nom + " " + prenom + " (" + age + " ans)");
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