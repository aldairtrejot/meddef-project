package gob.hidalgo.curso.database.meddef;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import gob.hidalgo.curso.utils.EntityObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("SeguimientoMeddefEO")
public class SeguimientoMeddefEO extends EntityObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String oficio_entrada;
	private String oficio_salida;
	private Integer anio;
	private Date fecha_recepcion;
	private String oficio_pf;
	private String oficio_sj;
	private String oficio_certificada;
	private Integer id_expediente;
	private Integer id_tipoentrada;
	private Date fecha_hora;
	private String desc_seguimiento;
	
	private String contribuyente;
	private String num_expediente;
	
	public SeguimientoMeddefEO() {
		super();
	}
	
}
