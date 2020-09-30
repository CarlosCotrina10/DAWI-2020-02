package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.ProductoDTO;
import beans.VentasMesDTO;
import beans.VentasProductosDTO;
import interfaces.ReporteDAO;
import utils.MySQLConexion;

public class MYSQLReporteDAO implements ReporteDAO {

	@Override
	public ArrayList<ProductoDTO> listaStock(int stock) {
		ArrayList<ProductoDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try {
			con = MySQLConexion.getConexion();
			String sql;
			sql = "call sp_listarStock (?);";
			pst = con.prepareStatement(sql);
			pst.setInt(1,stock);
			rs = pst.executeQuery();
			lista = new ArrayList<ProductoDTO>();
			while (rs.next()) {
				ProductoDTO p = new ProductoDTO();
				p.setIdprod(rs.getInt(1));
				p.setNomprod(rs.getString(2));
				p.setDescripcion(rs.getString(3));
				p.setStock(rs.getInt(4));
				p.setPrecio(rs.getDouble(5));
				p.setIdcategoria(rs.getInt(6));
				p.setEstado(rs.getInt(7));
				p.setNomcat(rs.getString(8));
				if(p.getEstado()==0) {
					p.setDesEstado("Desactivado");
				} else {
					p.setDesEstado("Activo");
				}
				lista.add(p);
			}
		} catch (Exception e) {
			System.out.println("Error en reporte de stock: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista ;
	}

	@Override
	public ArrayList<VentasMesDTO> listadoVentasMes(int year) {
		ArrayList<VentasMesDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql1 = "SET lc_time_names = 'es_MX'";
			String sql2 = "call sp_listarVentas(?);";
			pst = con.prepareStatement(sql1);
			pst.executeUpdate();
			pst = con.prepareStatement(sql2);
			pst.setInt(1,year);
			rs = pst.executeQuery();
			lista = new ArrayList<VentasMesDTO>();
			while (rs.next()) {
				VentasMesDTO v = new VentasMesDTO();
				v.setMes(rs.getString(1));
				v.setYear(rs.getInt(2));
				v.setSuma(rs.getDouble(3));
				v.setCandidadPro(rs.getInt(4));
				v.setClientesActivos(rs.getInt(5));
				v.setClientesTotales(rs.getInt(6));
				lista.add(v);
			}
		} catch (Exception e) {
			System.out.println("Error en reporte de ventas por mes: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista ;
	}

	@Override
	public ArrayList<VentasProductosDTO> listadoVentaProductos(String date1, String date2) {
		ArrayList<VentasProductosDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try {
			con = MySQLConexion.getConexion();
			String sql;
			sql = "call sp_listarProductosVentas(?,?);";
			pst = con.prepareStatement(sql);
			pst.setString(1,date1);
			pst.setString(2,date2);
			rs = pst.executeQuery();
			lista = new ArrayList<VentasProductosDTO>();
			while (rs.next()) {
				VentasProductosDTO v = new VentasProductosDTO();
				v.setIdprod(rs.getInt(1));
				v.setNombre(rs.getString(2));
				v.setStock(rs.getInt(3));
				v.setCategoria(rs.getString(4));
				v.setCantidad(rs.getInt(5));
				v.setTotal(rs.getDouble(6));
				if (rs.getInt(7) == 1){
					v.setEstado("Activo");
				} else {
					v.setEstado("Desactivado");
				}
				lista.add(v);
			}
		} catch (Exception e) {
			System.out.println("Error en reporte de venta de productos: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista ;
	}

}
