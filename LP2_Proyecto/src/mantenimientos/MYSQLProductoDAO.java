package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.CategoriaDTO;
import beans.ProductoDTO;
import interfaces.ProductoDAO;
import utils.MySQLConexion;

public class MYSQLProductoDAO implements ProductoDAO {

	@Override
	public ArrayList<ProductoDTO> listado(int idcat, int est) {
		ArrayList<ProductoDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try {
			con = MySQLConexion.getConexion();
			String sql;
			if (idcat == 0) {
				sql = "call sp_listar_prod (?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,est);
			} else {
				sql = "call sp_listar_prod_cod (?,?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,idcat);
				pst.setInt(2,est);
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

	@Override
	public int registrar(ProductoDTO p) {
		int rs = 0;  
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_productos values (null,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getNomprod());
			pst.setString(2, p.getDescripcion());
			pst.setInt(3, p.getStock());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getIdcategoria());
			pst.setInt(6, p.getEstado());
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar producto : " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return rs;
	}

	@Override
	public ProductoDTO buscar(int cod) {
		ProductoDTO p = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_productos where idprod = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, cod);		
			rs = pst.executeQuery();
			if (rs.next()) {
				p = new ProductoDTO();
				p.setIdprod(rs.getInt(1));
				p.setNomprod(rs.getString(2));
				p.setDescripcion(rs.getString(3));
				p.setStock(rs.getInt(4));
				p.setPrecio(rs.getDouble(5));
				p.setIdcategoria(rs.getInt(6));
				p.setEstado(rs.getInt(7));
			}
		} catch (Exception e) {
			System.out.println("Error en buscar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return p;
	}

	@Override
	public int actualizar(ProductoDTO p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "update tb_productos"
					   + " set  nomprod = ?, descripcion = ?, stock = ?, precio = ?, idcategoria = ?, estado = ?"
					   + " where idprod = ?;";
			pst = con.prepareStatement(sql);
			pst.setString(1,p.getNomprod());
			pst.setString(2,p.getDescripcion());
			pst.setInt(3,p.getStock());
			pst.setDouble(4,p.getPrecio());
			pst.setInt(5,p.getIdcategoria());
			pst.setInt(6,p.getEstado());
			pst.setInt(7,p.getIdprod());
			
			rs = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error en actualizar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return rs;
	}

	@Override
	public ArrayList<CategoriaDTO> listadoCategoria() {
		ArrayList<CategoriaDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_categorias;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<CategoriaDTO>();
			while (rs.next()) {
				CategoriaDTO c = new CategoriaDTO();
				c.setIdcategoria(rs.getInt(1));
				c.setDescripcion(rs.getString(2));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en listado: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista ;
	}

	@Override
	public int cambiarEstado(int cod, int est) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = " update tb_productos"
					   + " set estado = ?"
					   + " where idprod = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, est);
			pst.setInt(2, cod);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en cambiar estado: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return rs;
	}

}
