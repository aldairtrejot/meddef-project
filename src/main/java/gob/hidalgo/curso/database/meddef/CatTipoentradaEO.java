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
@Alias("CatTipoentradaEO")
public class CatTipoentradaEO extends EntityObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//PRIMARY KEY
	private Integer id;
	
	private String desc_tipoentrada;

	public CatTipoentradaEO() {
		super();
	}
	
}
