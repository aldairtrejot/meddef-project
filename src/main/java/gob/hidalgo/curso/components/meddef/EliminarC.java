package gob.hidalgo.curso.components.meddef;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gob.hidalgo.curso.components.MensajesC;
import gob.hidalgo.curso.database.meddef.ContribuyenteEO;
import gob.hidalgo.curso.database.meddef.ExpedienteMeddefEO;
import gob.hidalgo.curso.database.meddef.SeguimientoMeddefEO;


@Component("EliminarC")
@Named
@RequestScoped
public class EliminarC {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MensajesC mensajesC;
	
	@Autowired
	private BitacoraMovimientoC bitacoraMovimientoC;
	
	
    public void deleteCont(ContribuyenteEO contribuyenteEO) {
    	try {
    		sqlSession.delete("contribuyente.delete", contribuyenteEO);
    		bitacoraMovimientoC.agregar(contribuyenteEO, "Delete", "contribuyente", null, null);
    		mensajesC.mensajeInfo("Contribuyente Eliminado");
        } catch (Exception ex) {
        	mensajesC.mensajeError("Error al Eliminar - Contribuyente con Expedientes");
        }
    }
    
    public void deleteExp(ExpedienteMeddefEO expedienteMeddefEO) {
    	try {
    		sqlSession.delete("expediente_meddef.delete",expedienteMeddefEO);
    		bitacoraMovimientoC.agregar(expedienteMeddefEO, "Delete", "expedediente_meddef", null, null);
    		mensajesC.mensajeInfo("Expediente Eliminado");
        } catch (Exception ex) {
        	mensajesC.mensajeError("Error al Eliminar - Expediente con Seguimientos");
        }
    }
    
    public void deleteSeg(SeguimientoMeddefEO seguimientoMeddefEO) {
    	try {
    		sqlSession.delete("seguimiento_meddef.delete", seguimientoMeddefEO);
    		bitacoraMovimientoC.agregar(seguimientoMeddefEO, "Delete", "seguimiento_meddef", null, null);
    		mensajesC.mensajeInfo("Seguimiento Eliminado");
        } catch (Exception ex) {
        	mensajesC.mensajeFatal("Error al Eliminar");
        }
    }
}