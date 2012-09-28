package ar.edu.frba.utn.dds.usuarios;



public class Estandar implements TipoUsuario{

	private float recargo;
	
	public Estandar(){
		this.recargo = 0;
	}
	@Override
	public float getRecargo() {
		return this.recargo;
	}
	public void setRecargo(float recargo) {
		this.recargo = recargo;
	}

}
