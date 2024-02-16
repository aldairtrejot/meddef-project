package gob.hidalgo.curso.components.meddef;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("BitacoraMovimientoC")
public class BitacoraMovimientoC {

	@Autowired
	private SqlSession sqlSession;

	public void agregar(Object object,String accion,String desc_tabla,Integer id_fk,String table_fk) {
		String desc_accion = accion + ", VALUES: " + String.valueOf(object.toString()) + ", FK_Table name: " + 
							 table_fk + ", Foreign key id: " + id_fk;
		HashMap<String, Object> parametros;
		parametros = new HashMap<>();
		parametros.put("id_usuario", usuarioActual());
		parametros.put("desc_tabla", desc_tabla);
		parametros.put("desc_accion", desc_accion);
		sqlSession.insert("bitacora_movimiento.agregar", parametros);
	}
	
	private Integer usuarioActual() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String nick = authentication.getName();
		String string = String.valueOf(sqlSession.selectList("usuarios.resultadoId",nick));
		string = string.replaceAll("\\[|\\]", "");
		Integer id = Integer.parseInt(string);
		return id;
	}
}
