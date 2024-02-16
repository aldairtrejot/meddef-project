package gob.hidalgo.curso.database.meddef;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import gob.hidalgo.curso.utils.EntityObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("ReporteEO")
public class ReporteEO extends EntityObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String contribuyente;
	private String num_expediente;
	private String num_credfis;
	private Integer id_area;
	private Integer id_estatus;
	private Integer anio;
	
	public ReporteEO() {
		super();
		contribuyente = null;
	}
	
}
