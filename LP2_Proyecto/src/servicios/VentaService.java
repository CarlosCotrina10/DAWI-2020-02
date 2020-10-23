package servicios;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import model.Producto;
import mybatis.MyBatisUtil;
import mybatis.mapper.VentaMapper;

public class VentaService implements VentaMapper{

	@Override
	public ArrayList<Producto> listado(Producto p) {
		ArrayList<Producto> listado = null;
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			VentaMapper vm = session.getMapper(VentaMapper.class);
			listado = vm.listado(p);
		} catch (Exception e) {
			System.out.println("Error al listar Productos por Categoria " + e.getMessage());
		}finally {
			session.close();
		}
		return listado;
	}

}
