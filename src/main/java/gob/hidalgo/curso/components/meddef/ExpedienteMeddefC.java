package gob.hidalgo.curso.components.meddef;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gob.hidalgo.curso.components.MensajesC;
import gob.hidalgo.curso.database.meddef.ExpedienteMeddefEO;
import gob.hidalgo.curso.database.meddef.ContribuyenteEO;
import gob.hidalgo.curso.utils.Modelo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("ExpedienteMeddefC")
public class ExpedienteMeddefC {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private MensajesC mensajesC;
	
	@Autowired
	private BitacoraMovimientoC bitacoraMovimientoC;
	
	public ExpedienteMeddefC() {
		super();
		log.debug("Se crea componente ExpedienteMeddefC");
	}

	public ExpedienteMeddefEO nuevo() {
		return new ExpedienteMeddefEO();
	}

	public Modelo<ExpedienteMeddefEO> modelo(ContribuyenteEO contribuyenteEO) {
		List<ExpedienteMeddefEO> listado;
		listado = sqlSession.selectList("expediente_meddef.listado", contribuyenteEO);
		return new Modelo<>(listado);
	}

	public void agregar(ExpedienteMeddefEO expedienteMeddefEO, ContribuyenteEO contribuyenteEO) {
		bitacoraMovimientoC.agregar(expedienteMeddefEO, "Add", "expediente_meddef", contribuyenteEO.getCodigo_estatal(),
				"contribuyente");
		HashMap<String, Object> parametros;
		parametros = new HashMap<>();
		parametros.put("expediente_meddef", expedienteMeddefEO);
		parametros.put("contribuyente", contribuyenteEO);
		sqlSession.insert("expediente_meddef.agregar", parametros);
		mensajesC.mensajeInfo("Expediente Agregado");
	}

	public void editar(ExpedienteMeddefEO expedienteMeddefEO) {
		bitacoraMovimientoC.agregar(expedienteMeddefEO, "Edit", "expediente_meddef", null, null);
		sqlSession.update("expediente_meddef.editar", expedienteMeddefEO);
		mensajesC.mensajeInfo("Expediente Actualizado");
	}

	public boolean validacion(ExpedienteMeddefEO expedienteMeddefEO) {
		if (expedienteMeddefEO != null) {
			return true;
		}
		return false;
	}
}
