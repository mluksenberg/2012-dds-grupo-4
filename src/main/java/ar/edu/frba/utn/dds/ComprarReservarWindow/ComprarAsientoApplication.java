package ar.edu.frba.utn.dds.ComprarReservarWindow;

import org.uqbar.commons.utils.Observable;

import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.usuarios.Usuario;

/*
 * APPLICATION MODEL DE LA PANTALLA COMPRAS
 */
@Observable
public class ComprarAsientoApplication extends ComprarReservarApplication{

	public ComprarAsientoApplication(Asiento unAsiento, Usuario unUsuario) {
		super(unAsiento, unUsuario);
		comprarAsiento();
	}
}
