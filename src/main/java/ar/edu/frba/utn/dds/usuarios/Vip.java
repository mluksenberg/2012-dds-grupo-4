package ar.edu.frba.utn.dds.usuarios;

public class Vip implements TipoUsuario{

	private float recargo;
	
	public Vip(){
		this.recargo=0;
	}
	@Override
	public float getRecargo() {
		return this.recargo;
	}
	public void setRecargo(float recargo) {
		this.recargo = recargo;
	}

}
