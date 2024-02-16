package gob.hidalgo.curso.database.meddef;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import org.apache.ibatis.type.Alias;
import gob.hidalgo.curso.utils.EntityObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("ExpedienteMeddefEO")
public class ExpedienteMeddefEO extends EntityObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotBlank(message = "Ingresar Número de Expediente")
	private String num_expediente;
	private String num_credfis;
	private String num_control;
	private Integer importe;
	private Integer codigo_estatal;
	private Integer id_sentencia;
	private Integer id_area;
	private Integer id_estatus;
	private Date fecha_hora;
	
	private String contribuyente;
	

	public ExpedienteMeddefEO(){
		super();
	}
}
