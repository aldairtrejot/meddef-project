package gob.hidalgo.curso.components.meddef;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gob.hidalgo.curso.database.meddef.ContribuyenteEO;
import gob.hidalgo.curso.database.meddef.ExpedienteMeddefEO;
import gob.hidalgo.curso.database.meddef.ReporteEO;
import gob.hidalgo.curso.database.meddef.SeguimientoMeddefEO;
import gob.hidalgo.curso.utils.Modelo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("ReporteC")
public class ReporteC {
	@Autowired
	private SqlSession sqlSession;
	
	public ReporteC() {
		super();
		log.debug("Se crea componente ReporteC");
	}
	
	public ReporteEO nuevo() {
		return new ReporteEO();
	}
	
	public Modelo<ContribuyenteEO> modeloContribuyente(ReporteEO reporteEO) {
		contribullenteNull(reporteEO);
		List<ContribuyenteEO> listado;
		if (reporteNull(reporteEO)) {
			listado = null;
		} else {
			listado = sqlSession.selectList("contribuyente.reporteContribuyente",reporteEO);
		}
		return new Modelo<>(listado);
	}
	
	
	public Modelo<ExpedienteMeddefEO> modeloExpediente(ReporteEO reporteEO) {
		contribullenteNull(reporteEO);
		List<ExpedienteMeddefEO> listado;
		if (reporteNull(reporteEO)) {
			listado = null;
		} else {
			listado = sqlSession.selectList("expediente_meddef.reporteExpediente",reporteEO);
		}
		return new Modelo<>(listado);
	}

	public Modelo<SeguimientoMeddefEO> modeloSeguimiento(ReporteEO reporteEO) {
		contribullenteNull(reporteEO);
		List<SeguimientoMeddefEO> listado;
		if (reporteNull(reporteEO)) {
			listado = null;
		} else {
			listado = sqlSession.selectList("seguimiento_meddef.reporteSeguimiento",reporteEO);
		}
		return new Modelo<>(listado);
	}

	private boolean reporteNull (ReporteEO reporteEO) {
		contribullenteNull(reporteEO);
		boolean bool = false;
		if(Vexpediente(reporteEO) == false &&
		   reporteEO.getAnio() == null &&	
		   reporteEO.getContribuyente() == null) {
			bool = true;
		}
		return bool;
	}
	
	private void contribullenteNull (ReporteEO reporteEO) {
		if (reporteEO.getContribuyente() == "") {
			reporteEO.setContribuyente(null);
		}
		if (reporteEO.getNum_expediente() == "") {
			reporteEO.setNum_expediente(null);
		}
		if (reporteEO.getNum_credfis() == "") {
			reporteEO.setNum_credfis(null);
		}
	}

	private boolean Vexpediente(ReporteEO reporteEO) {
		boolean bool = false;
		if (reporteEO.getNum_expediente() != null ||
			reporteEO.getId_estatus() != null	  ||
			reporteEO.getId_area() != null		  ||
			reporteEO.getNum_credfis() != null    ) {
			bool = true; 
		} 
		return bool;
	}
}
