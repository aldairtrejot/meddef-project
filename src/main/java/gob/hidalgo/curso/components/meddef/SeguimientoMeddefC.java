package gob.hidalgo.curso.components.meddef;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gob.hidalgo.curso.components.MensajesC;
import gob.hidalgo.curso.database.meddef.ExpedienteMeddefEO;
import gob.hidalgo.curso.database.meddef.SeguimientoMeddefEO;
import gob.hidalgo.curso.utils.Modelo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("SeguimientoMeddefC")
public class SeguimientoMeddefC {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private MensajesC mensajesC;

	@Autowired
	private BitacoraMovimientoC bitacoraMovimientoC;

	public SeguimientoMeddefC() {
		super();
		log.debug("Se crea componente SeguimientoMeddefC");
	}

	public SeguimientoMeddefEO nuevo() {
		return new SeguimientoMeddefEO();
	}

	public Modelo<SeguimientoMeddefEO> modelo(ExpedienteMeddefEO expedienteMeddefEO) {
		List<SeguimientoMeddefEO> listado;
		listado = sqlSession.selectList("seguimiento_meddef.listado", expedienteMeddefEO);
		return new Modelo<>(listado);
	}

	public void agregar(SeguimientoMeddefEO seguimientoMeddefEO, ExpedienteMeddefEO expedienteMeddefEO) {
			bitacoraMovimientoC.agregar(seguimientoMeddefEO, "Add", "seguimiento_meddef", expedienteMeddefEO.getId(),
					"expediente_meddef");
			HashMap<String, Object> parametros;
			parametros = new HashMap<>();
			parametros.put("expediente_meddef", expedienteMeddefEO);
			parametros.put("seguimiento_meddef", seguimientoMeddefEO);
			sqlSession.insert("seguimiento_meddef.insertar", parametros);
			mensajesC.mensajeInfo("Seguimiento Agregado");
	}

	public void editar(SeguimientoMeddefEO seguimientoMeddefEO) {
		bitacoraMovimientoC.agregar(seguimientoMeddefEO, "Edit", "seguimiento_meddef", null, null);
		sqlSession.update("seguimiento_meddef.editar", seguimientoMeddefEO);
		mensajesC.mensajeInfo("Seguimiento Actualizado");
	}

	public boolean validacion(SeguimientoMeddefEO seguimientoMeddefEO) {
		if (seguimientoMeddefEO != null) {
			return true;
		}
		return false;
	}
}
