package gob.hidalgo.curso.components.meddef;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import gob.hidalgo.curso.database.meddef.CatAreaEO;
import gob.hidalgo.curso.database.meddef.CatEstatusEO;
import gob.hidalgo.curso.database.meddef.CatSentenciaEO;
import gob.hidalgo.curso.database.meddef.CatTipoentradaEO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@RequestScoped
public class CatalagoMenuC implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SqlSession sqlSession;

	private LinkedHashMap<String, Integer> catAreaList;
	private LinkedHashMap<String, Integer> catSentenciaList;
	private LinkedHashMap<String, Integer> catEstatusList;
	private LinkedHashMap<String, Integer> catTipoEntradaList;

	@PostConstruct
	public void init() {
		catAreaList = new LinkedHashMap<String, Integer>(); catArea(catAreaList);
		catSentenciaList = new LinkedHashMap<String, Integer>(); catSentencia(catSentenciaList);
		catEstatusList = new LinkedHashMap<String, Integer>(); catEstatus(catEstatusList);
		catTipoEntradaList = new LinkedHashMap<String, Integer>(); catTipoEntrada(catTipoEntradaList);
	}
	
	private void catTipoEntrada(LinkedHashMap<String, Integer> catTipoEntradaList) {
		catTipoEntradaList.put("Seleccione", null);
		List<CatTipoentradaEO> listado;
		listado = sqlSession.selectList("cat_tipoentrada.listado");
		String string;
		for (CatTipoentradaEO catTipoentradaEO : listado) {
			string = String.valueOf(catTipoentradaEO.getId()) + " - " + catTipoentradaEO.getDesc_tipoentrada();
			catTipoEntradaList.put(string, catTipoentradaEO.getId());
		}
	}
	
	private void catEstatus(LinkedHashMap<String, Integer> catEstatusList) {
		catEstatusList.put("Seleccione", null);
		List<CatEstatusEO> listado;
		listado = sqlSession.selectList("cat_estatus.listado");
		String string;
		for (CatEstatusEO catEstatusEO : listado) {
			string = String.valueOf(catEstatusEO.getId()) + " - " + catEstatusEO.getDesc_estatus();
			catEstatusList.put(string, catEstatusEO.getId());
		}
	}
	
	private void catArea(LinkedHashMap <String, Integer> catAreaList) {
		catAreaList.put("Seleccione", null);
		List<CatAreaEO> listado;
		listado = sqlSession.selectList("cat_area.listado");
		String string;
		for (CatAreaEO catAreaEO : listado) {
			string = String.valueOf(catAreaEO.getId()) + " - " + catAreaEO.getDesc_area();
			catAreaList.put(string, catAreaEO.getId());
		}
	}
	
	
	private void catSentencia(LinkedHashMap<String, Integer> catSentenciaList) {
		catSentenciaList.put("Seleccione", null);
		List<CatSentenciaEO> listado;
		listado = sqlSession.selectList("cat_sentencia.listado");
		String string;
		for (CatSentenciaEO catSentenciaEO : listado) {
			//string = String.valueOf(catSentenciaEO.getId()) +  " - " + 
			 string = catSentenciaEO.getCve_sentencia() + " - " + catSentenciaEO.getDesc_sentencia();
			 catSentenciaList.put(string, catSentenciaEO.getId());
		}
	}
}