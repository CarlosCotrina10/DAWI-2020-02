package dao;

import interfaces.ProductoDAO;
import interfaces.ReporteDAO;
import interfaces.UsuarioDAO;
import interfaces.VentaDAO;
import mantenimientos.MYSQLProductoDAO;
import mantenimientos.MYSQLReporteDAO;
import mantenimientos.MYSQLUsuarioDAO;
import mantenimientos.MYSQLVentaDAO;

public class MYSQLDAOFactory extends DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new MYSQLUsuarioDAO();
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new MYSQLProductoDAO();
	}

	@Override
	public VentaDAO getVentaDAO() {
		return new MYSQLVentaDAO();
	}

	@Override
	public ReporteDAO getReporteDAO() {
		return new MYSQLReporteDAO();
	}


}
