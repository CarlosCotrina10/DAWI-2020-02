package dao;

import interfaces.ProductoDAO;
import interfaces.ReporteDAO;
import interfaces.UsuarioDAO;
import interfaces.VentaDAO;
import mantenimientos.SQLProductoDAO;
import mantenimientos.SQLReporteDAO;
import mantenimientos.SQLUsuarioDAO;
import mantenimientos.SQLVentaDAO;

public class SQLDAOFactory extends DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new SQLUsuarioDAO();
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new SQLProductoDAO();
	}

	@Override
	public VentaDAO getVentaDAO() {
		return new SQLVentaDAO();
	}

	@Override
	public ReporteDAO getReporteDAO() {
		return new SQLReporteDAO();
	}


}
