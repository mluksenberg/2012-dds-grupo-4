package ar.edu.frba.utn.dds.entrega_4;

public class NoPaga implements TipoUsuario{

	private float recargo;

	public NoPaga(){
		this.recargo = 20;
	}
	@Override
	public float getRecargo() {
		return recargo;
	}

	public void setRecargo(float recargo) {
		this.recargo = recargo;
	}

}