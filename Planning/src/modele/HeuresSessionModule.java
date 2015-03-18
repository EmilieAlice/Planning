package modele;

public class HeuresSessionModule {
	
	private int id_session;
	private int id_module;
	private int nbreHeuresDisponibles;
	
	public HeuresSessionModule(){
		
	}
	
	public HeuresSessionModule(int id_session, int id_module,
			int nbreHeuresDisponibles) {
		super();
		this.id_session = id_session;
		this.id_module = id_module;
		this.nbreHeuresDisponibles = nbreHeuresDisponibles;
	}
	
	public int getId_session() {
		return id_session;
	}
	public void setId_session(int id_session) {
		this.id_session = id_session;
	}
	public int getId_module() {
		return id_module;
	}
	public void setId_module(int id_module) {
		this.id_module = id_module;
	}
	public int getNbreHeuresDisponibles() {
		return nbreHeuresDisponibles;
	}
	public void setNbreHeuresDisponibles(int nbreHeuresDisponibles) {
		this.nbreHeuresDisponibles = nbreHeuresDisponibles;
	}

	@Override
	public String toString() {
		return "HeuresSessionModule [id_session=" + id_session + ", id_module="
				+ id_module + ", nbreHeuresDisponibles="
				+ nbreHeuresDisponibles + "]";
	}

	
}
