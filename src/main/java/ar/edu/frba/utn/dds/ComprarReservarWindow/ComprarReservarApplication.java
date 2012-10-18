package ar.edu.frba.utn.dds.ComprarReservarWindow;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.commons.utils.Observable;

import ar.edu.frba.utn.dds.exeptions.LaReservaNoCorrespondeAlUsuarioExeption;
import ar.edu.frba.utn.dds.exeptions.NoAdmiteReservaExeption;
import ar.edu.frba.utn.dds.exeptions.NoSePudoComprarExeption;
import ar.edu.frba.utn.dds.exeptions.UsuarioInvalidoParaReservaExeption;
import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.usuarios.Usuario;

/*
 * APPLICATION MODEL DE LA PANTALLA COMPRAS
 */
@Observable
public class ComprarReservarApplication {
	
	private String mensaje = "";
	private Asiento asientoSeleccionado;
	private Usuario user;
	private Boolean seSobreReservo;
	private Button prueba;
	
	public ComprarReservarApplication(Asiento unAsiento, Usuario unUsuario){
		this.setAsientoSeleccionado(unAsiento);
		this.setUser(unUsuario);
		this.setSeSobreReservo(false);
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Usuario getUser() {
		return user;
	}

	public void setAsientoSeleccionado(Asiento asientoSeleccionado) {
		this.asientoSeleccionado = asientoSeleccionado;
	}

	public Asiento getAsientoSeleccionado() {
		return asientoSeleccionado;
	}
	
	public void reservar() {
		if (getAsientoSeleccionado() != null){
			try {
				this.getUser().reservarAsiento(getAsientoSeleccionado());
				this.setMensaje("se reservo el asiento con exito");
				this.setSeSobreReservo(true);
			} catch (UsuarioInvalidoParaReservaExeption e) {
				this.setMensaje(e.getMessage());
			} catch (NoAdmiteReservaExeption e) {
				this.setMensaje(e.getMessage());
			}
		}
	}
	
	public void comprarAsiento(){
		try{
			if (getAsientoSeleccionado() != null){
				this.getUser().comprarAsiento(getAsientoSeleccionado());
				this.setMensaje("se compro el asiento con exito");
			}
		}catch(NoSePudoComprarExeption e){
			this.setMensaje(e.getMessage());
		}catch(LaReservaNoCorrespondeAlUsuarioExeption e1){
			this.setMensaje(e1.getMessage());
		}
	}

	public void setSeSobreReservo(Boolean seSobreReservo) {
		this.seSobreReservo = seSobreReservo;
	}

	public Boolean getSeSobreReservo() {
		return seSobreReservo;
	}

	public void setPrueba(Button prueba) {
		this.prueba = prueba;
	}

	public Button getPrueba() {
		return prueba;
	}
	
	public Button botonSobreReservar(ReservarAsientoWindow reservarWindow,Panel panel){
		if (this.getAsientoSeleccionado().getEstaReservado()){
			this.setMensaje("El asiento ya se encuentra reservado, Que desea hacer?");
			return new Button(panel).setCaption("sobre reservar").onClick(new MessageSend(reservarWindow, "overBook"));
		}
		return null;
	}
}
