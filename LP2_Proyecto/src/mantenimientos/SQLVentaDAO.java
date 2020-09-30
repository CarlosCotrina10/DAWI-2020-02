package mantenimientos;

import java.util.ArrayList;

import beans.CabeceraBoletaDTO;
import beans.DetalleBoletaDTO;
import beans.ProductoDTO;
import interfaces.VentaDAO;

public class SQLVentaDAO implements VentaDAO {

	@Override
	public String generaNumBoleta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int realizarVenta(CabeceraBoletaDTO cab, ArrayList<DetalleBoletaDTO> det) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ProductoDTO> listado(int idcat) {
		// TODO Auto-generated method stub
		return null;
	}

}
