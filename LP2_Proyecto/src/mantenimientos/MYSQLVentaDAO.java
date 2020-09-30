package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import beans.CabeceraBoletaDTO;
import beans.DetalleBoletaDTO;
import beans.ProductoDTO;
import interfaces.VentaDAO;
import utils.MySQLConexion;

public class MYSQLVentaDAO implements VentaDAO {

	@Override
	public String generaNumBoleta() {
		String codigo = "B0001";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select substring(numBoleta,2)"
					   + " from tb_boleta order by numBoleta desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				DecimalFormat df = new DecimalFormat("0000");
				codigo = "B" + df.format(Integer.parseInt(rs.getString(1))+1);
			}
		} catch (Exception e) {
			System.out.println("Error en genererar numero de boleta : " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return codigo;
	}

	@Override
	public int realizarVenta(CabeceraBoletaDTO cab, ArrayList<DetalleBoletaDTO> det) {
		int ok = -1;
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql1 = "insert into tb_boleta values(?,?,?,?)";
			pst1 = con.prepareStatement(sql1);
			pst1.setString(1, cab.getNumBoleta());
			pst1.setInt(2, cab.getCodUsuario());
			pst1.setString(3, cab.getFecha());
			pst1.setDouble(4, cab.getTotal());
			ok = pst1.executeUpdate();
			
			String sql2 = "insert into tb_detalle_boleta values (?,?,?,?)";
			for (DetalleBoletaDTO d : det) {
				pst2 = con.prepareStatement(sql2);
				pst2.setString(1, cab.getNumBoleta());
				pst2.setInt(2, d.getIdprod());
				pst2.setInt(3, d.getCantidad());
				pst2.setDouble(4, d.getPreciovta());
				ok = pst2.executeUpdate();
			}
			
			String sql3 = "update tb_productos set stock = stock - ? where idprod = ?";
			for (DetalleBoletaDTO d : det) {
				pst3 = con.prepareStatement(sql3);
				pst3.setInt(1,  d.getCantidad());
				pst3.setInt(2, d.getIdprod());
				ok = pst3.executeUpdate();
			}
			con.commit();
			
		} catch (Exception e) {
			System.out.println("Error al realizar la venta : " + e.getMessage());
			ok = -1;
			try {
				con.rollback();
			} catch (Exception e2) {
				System.out.println("Error al restaurar " + e.getMessage());
			}
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

	@Override
	public ArrayList<ProductoDTO> listado(int idcat) {
		ArrayList<ProductoDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try {
			con = MySQLConexion.getConexion();
			String sql;
			if (idcat == 0) {
				sql = "call sp_listar_tienda;";
				pst = con.prepareStatement(sql);
			} else {
				sql = "call sp_listar_tienda_cod (?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,idcat);
			}
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
				lista.add(p);
			}
		} catch (Exception e) {
			System.out.println("Error en listado de productos: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista ;
	}

}
