package gob.hidalgo.curso.components.meddef;

import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import gob.hidalgo.curso.components.MensajesC;


@ManagedBean
public class ValidacionC {

	@Autowired
	private MensajesC mensajesC;

	public void rfcM(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		if (((String) arg2) != "") {
			if (((String) arg2).length() != 12) {
				mensajesC.messageError("RFC debede contener 12 caracteres");
			}
		}
	}
	
	public void rfcF(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		if (((String) arg2) != "") {
			if (((String) arg2).length() != 13) {
				mensajesC.messageError("RFC debede contener 13 caracteres");
			}
		}
	}
	
	public void curp(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		if (((String) arg2) != "") {
			if (((String) arg2).length() != 18) {
				mensajesC.messageError("CURP debe contener 18 caracteres");
			}
		}
	}
}