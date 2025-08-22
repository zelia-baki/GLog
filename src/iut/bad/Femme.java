package iut.bad;

public class Femme extends humain  {

	public Femme() {
		super();
		//TODO Auto-generated constructor stub
	}

	public Femme(String nom, String prenom, int age) {
		super(nom, prenom, age);
		//TODO Auto-generated constructor stub
	} 
	
	 public static void main(String[] args) {
	        Homme h = new Homme("TSIMIJALY", "Nomena", 18);
	        Femme f = new Femme("Gaby", "Montez", 22);

	        h.details();
	        f.details();
	        
//	        System.out.println("\nDéclaration d'amitié :");
//	        h.amis(f);   // TSIMIJALY est ami avec Gaby
//	        f.amis(h);   // Gaby est amie avec TSIMIJALY
//	        
	        System.out.println("\nDéclaration d'amitié :");
	        h.amis(f);   // TSIMIJALY est ami avec Gaby
	        f.amis(h, 50);
	 }
	
}
