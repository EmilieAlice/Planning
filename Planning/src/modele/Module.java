package modele;

public class Module {
	
	private int id_module;
	private String nom;
	private String objectif;
	private String contenu;
	private int nb_heures_annuelles;
	private String prerequis;
	
	public Module(){
		
	}
	
	public Module(int id_module, String nom, String objectif, String contenu,
			int nb_heures_annuelles, String prerequis) {
		super();
		this.id_module = id_module;
		this.nom = nom;
		this.objectif = objectif;
		this.contenu = contenu;
		this.nb_heures_annuelles = nb_heures_annuelles;
		this.prerequis = prerequis;
	}
	
	public int getId_module() {
		return id_module;
	}
	public void setId_module(int id_module) {
		this.id_module = id_module;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getObjectif() {
		return objectif;
	}
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public int getNb_heures_annuelles() {
		return nb_heures_annuelles;
	}
	public void setNb_heures_annuelles(int nb_heures_annuelles) {
		this.nb_heures_annuelles = nb_heures_annuelles;
	}
	public String getPrerequis() {
		return prerequis;
	}
	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}
	
	@Override
	public String toString() {
		return "Module [id_module=" + id_module + ", nom=" + nom
				+ ", objectif=" + objectif + ", contenu=" + contenu
				+ ", nb_heures_annuelles=" + nb_heures_annuelles
				+ ", prerequis=" + prerequis + "]";
	}
}
