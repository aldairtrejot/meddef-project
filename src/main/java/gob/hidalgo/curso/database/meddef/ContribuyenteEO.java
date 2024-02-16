package gob.hidalgo.curso.database.meddef;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.apache.ibatis.type.Alias;
import gob.hidalgo.curso.utils.EntityObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("ContribuyenteEO")
public class ContribuyenteEO extends EntityObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Ingresar Código Estatal")
	private Integer codigo_estatal;
	private String rfc;
	private String tipo_persona;
	private String razon_social;
	private String rfc_md;
	private String contribuyente;
	private String curp;
	private String nombres;
	private String primer_apellido;
	private String segundo_apellido;
	private String lugar_nacimiento;
	private Date fecha_hora;
	
	public ContribuyenteEO() {
		super();
	}
	
}
