package dao;

import interfaces.ProductoDAO;
import interfaces.ReporteDAO;
import interfaces.UsuarioDAO;
import interfaces.VentaDAO;

public abstract class DAOFactory {
	// constantes que definen las BD que vamos a usar;
	public static final int MYSQL = 1;
	public static final int SQL = 2;
	public static final int ACCESS = 3;
	public static final int SQLite = 5;
	public static final int ORACLE = 6;
	
	// crear los metodos de acceso para las interfaces
	public abstract UsuarioDAO getUsuarioDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract VentaDAO getVentaDAO();
	public abstract ReporteDAO getReporteDAO();

	// metodo para implementar las interfaces segun la BD
	public static DAOFactory getDAOFactory(int qFactory) {
		switch (qFactory) {
		case MYSQL:
			// clase para implementar los procescos con MySQL
			return new MYSQLDAOFactory();
		case SQL:
			return new SQLDAOFactory();
		case ACCESS:
			//return new ACCESSDAOFactory();
		case ORACLE:
			//return new ORACLEDAOFactory();
		default:
			return null;
		}
	}

}
