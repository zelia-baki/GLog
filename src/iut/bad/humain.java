package iut.bad;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
	
	public void amis(humain h, int duree) {
		if (h != null && h!= this) {
			this.amis.add(h);
			h.amis.add(this);
		}
	    System.out.println(this.prenom + " est ami avec " + h.prenom + " pour " + duree + " jours.");

	}
	
	public void amis(humain h) {
	    amis(h, 100); // par défaut 100 jours
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(age, nom, prenom);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		humain other = (humain) obj;
		return age == other.age && Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}



	private Set<humain> amis = new HashSet<>();
	
	public Set<humain> getAmis() {
		return amis;
	}


	public void setAmis(Set<humain> amis) {
		this.amis = amis;
	}


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