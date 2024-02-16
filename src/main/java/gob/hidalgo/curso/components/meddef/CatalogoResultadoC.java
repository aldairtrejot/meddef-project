package gob.hidalgo.curso.components.meddef;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class CatalogoResultadoC {
	
	@Autowired
	private SqlSession sqlSession;
	
	public String catalogoTipoEntrada(Integer integer) {
		String string = "";
		if(integer != null) {
			string = integer + " - " + String.valueOf(sqlSession.selectList("cat_tipoentrada.listadoString",integer));
			string = string.replaceAll("\\[|\\]", "");
		} 
		return string;
	}
	
	public String catalogoArea(Integer integer) {
		String string = "";
		if(integer != null) {
			string = integer + " - " + String.valueOf(sqlSession.selectList("cat_area.listadoString",integer));
			string = string.replaceAll("\\[|\\]", "");
		} 
		return string;
	}
	
	public String catalogoEstatus(Integer integer) {
		String string = "";
		if(integer != null) {
			string = integer + " - " + String.valueOf(sqlSession.selectList("cat_estatus.listadoString",integer));
			string = string.replaceAll("\\[|\\]", "");
		} 
		return string;
	}
	
	public String catalogoSentencia(Integer integer) {
		String string = "";
		if(integer != null) {
			//string = integer + " - " + String.valueOf(sqlSession.selectList("cat_sentencia.listadoStringUno",integer));
			string = String.valueOf(sqlSession.selectList("cat_sentencia.listadoStringUno",integer))
					 + " - " + String.valueOf(sqlSession.selectList("cat_sentencia.listadoStringDos",integer));
			string = string.replaceAll("\\[|\\]", "");
		} 
		return string;
	}
}
