package gob.hidalgo.curso.components.meddef;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gob.hidalgo.curso.components.MensajesC;
import gob.hidalgo.curso.database.meddef.ContribuyenteEO;
import gob.hidalgo.curso.utils.Modelo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("ContribuyenteC")
public class ContribuyenteC {
	
	private String mensajeError = "Error al Editar - Un Contribuyente con Expedientes no puede"
			+ " Editar su Código Estatal, Primero Elimine los Expedientes";
	
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private MensajesC mensajesC;

	@Autowired
	private BitacoraMovimientoC bitacoraMovimientoC;

	public ContribuyenteC() {
		super();
		log.debug("Se crea componente ContribuyenteC");
	}

	public ContribuyenteEO nuevo() {
		return new ContribuyenteEO();
	}

	public Modelo<ContribuyenteEO> modeloPF() {
		List<ContribuyenteEO> listado;
		listado = sqlSession.selectList("contribuyente.listadoPF");
		return new Modelo<>(listado);
	}

	public Modelo<ContribuyenteEO> modeloPM() {
		List<ContribuyenteEO> listado;
		listado = sqlSession.selectList("contribuyente.listadoPM");
		return new Modelo<>(listado);
	}
	
	public void editar(ContribuyenteEO contribuyenteEO, Integer contribuyenteCe) {
		Integer codigoEstatal = sqlSession.selectOne("contribuyente.comprobarCodigoEstatal", contribuyenteEO);
		int contribuyenteDes = contribuyenteEO.getCodigo_estatal();
		int contribuyenteCE = contribuyenteCe;

		if (contribuyenteDes != contribuyenteCE) {
			if (codigoEstatal != null) {
				mensajesC.mensajeError("Error al Editar - El Código Estatal ya Existe");
			} else {
				editarUno(contribuyenteEO, contribuyenteCe);
			}
		} else {
				editarUno(contribuyenteEO, contribuyenteCe);
		}
	}

	public void agregarPF(ContribuyenteEO contribuyenteEO) {
		try {
			bitacoraMovimientoC.agregar(contribuyenteEO, "Add", "contribuyente_pf", null, null);
			sqlSession.insert("contribuyente.agregarPF", contribuyenteEO);
			mensajesC.mensajeInfo("Contribuyente actualizado exitosamente");
		} catch (Exception e) {
			mensajesC.mensajeError("Error al Agregar - El Código Estatal ya Existe");
		}
	}

	public void agregarPM(ContribuyenteEO contribuyenteEO) {
		try {
			bitacoraMovimientoC.agregar(contribuyenteEO, "Add", "contribuyente_pm", null, null);
			sqlSession.insert("contribuyente.agregarPM", contribuyenteEO);
			mensajesC.mensajeInfo("Contribuyente Agregado");
		} catch (Exception e) {
			mensajesC.mensajeError("Error al Agregar - El Código Estatal ya Existe");
		}
	}

	public boolean validacion(ContribuyenteEO contribuyenteEO) {
		if (contribuyenteEO != null) {
			return true;
		}
		return false;
	}
	
	private void editarUno(ContribuyenteEO contribuyenteEO, Integer contribuyenteCe) {
		try {
			bitacoraMovimientoC.agregar(contribuyenteEO, "Edit", "contribuyente_pf", contribuyenteCe, "Contribuyente");
			HashMap<String, Object> parametros;
			parametros = new HashMap<>();
			parametros.put("contribuyente", contribuyenteEO);
			parametros.put("contribuyenteCe", contribuyenteCe);
			sqlSession.update("contribuyente.editar", parametros);
			mensajesC.mensajeInfo("Contribuyente Actualizado");
		} catch (Exception e) {
			mensajesC.mensajeError(mensajeError);
		}		
	}
}
