package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.DistritoDTO;
import beans.UsuarioDTO;
import interfaces.UsuarioDAO;
import utils.MySQLConexion;

public class MYSQLUsuarioDAO implements UsuarioDAO {

	@Override
	public UsuarioDTO validar(String usuario, String clave) {
		UsuarioDTO u = null; 
		Connection con = null; 
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con= MySQLConexion.getConexion();
			String sql= "CALL sp_validaUsuario(?,?)";
			pst= con.prepareStatement(sql);
			pst.setString(1, usuario);
			pst.setString(2, clave);
			rs= pst.executeQuery();
			while (rs.next()) { 
				u = new UsuarioDTO(); 
				u.setCodUsuario(rs.getInt(1));
				u.setNombre(rs.getString(2)); 
				u.setApellido(rs.getString(3));
				u.setCodDistrito(rs.getInt(4));
				u.setUsuario(rs.getString(5));
				u.setClave(rs.getString(6));
				u.setIdTipo(rs.getInt(7));
				u.setEstado(rs.getInt(8));
			}
		} catch (Exception e) {
			System.out.println("Error en validar usuario: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return u;
	}

	@Override
	public UsuarioDTO buscar(int cod) {
		UsuarioDTO u = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_usuarios where codUsuario = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, cod);	
			rs = pst.executeQuery();
			if (rs.next()) {
				u = new UsuarioDTO();
				u.setCodUsuario(rs.getInt(1));
				u.setNombre(rs.getString(2)); 
				u.setApellido(rs.getString(3));
				u.setCodDistrito(rs.getInt(4));
				u.setUsuario(rs.getString(5));
				u.setClave(rs.getString(6));
				u.setIdTipo(rs.getInt(7));
				u.setEstado(rs.getInt(8));
			}
		} catch (Exception e) {
			System.out.println("Error en buscar: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return u;
	}

	@Override
	public int actualizar(UsuarioDTO u) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = " update tb_usuarios"
					   + " set  nombre = ?, apellido = ?, codDistrito = ?, usuario = ?, clave = ?, idTipo = ?, estado = ?"
					   + " where codUsuario = ?;";
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getNombre());
			pst.setString(2,u.getApellido());
			pst.setInt(3, u.getCodDistrito());
			pst.setString(4,u.getUsuario());
			pst.setString(5,u.getClave());
			pst.setInt(6,u.getIdTipo());
			pst.setInt(7, u.getEstado());
			pst.setInt(8,u.getCodUsuario());
			
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en actualizar usuario: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return rs;
	}

	@Override
	public int cambiarEstado(int codigo, int est) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = " update tb_usuarios"
					   + " set estado = ?"
					   + " where codUsuario = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, est);
			pst.setInt(2, codigo);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en cambiar estado de usuario: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return rs;
	}

	@Override
	public ArrayList<UsuarioDTO> listado(int idtipo, int est, int idDis) {
		ArrayList<UsuarioDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try {
			con = MySQLConexion.getConexion();
			String sql;
			if (idtipo == -1 && idDis == 0) {
				sql = "call sp_listar_usu (?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,est);
			} else if (idtipo == -1) {
				sql = "call sp_listar_usu_dis (?,?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,est);
				pst.setInt(2,idDis);
			} else if (idDis == 0) {
				sql = "call sp_listar_usu_tip (?,?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,est);
				pst.setInt(2,idtipo);
			} else {
				sql = "call sp_listar_usu_dis_tip (?,?,?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,est);
				pst.setInt(2,idDis);
				pst.setInt(3,idtipo);
			}
			rs = pst.executeQuery();
			lista = new ArrayList<UsuarioDTO>();
			while (rs.next()) {
				UsuarioDTO u = new UsuarioDTO();
				u.setCodUsuario(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setCodDistrito(rs.getInt(4));
				u.setUsuario(rs.getString(5));
				u.setClave(rs.getString(6));
				u.setIdTipo(rs.getInt(7));
				u.setEstado(rs.getInt(8));
				u.setDesTipo(rs.getString(9));
				u.setNomDistrito(rs.getString(10));
				lista.add(u);
			}
		} catch (Exception e) {
			System.out.println("Error en listado de usuarios: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		
		return lista ;
	}

	@Override
	public ArrayList<DistritoDTO> listadoDistrito() {
		ArrayList<DistritoDTO> lista=null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql="CALL sp_listarDistritos()";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista= new ArrayList<DistritoDTO>();
			
			while(rs.next()) {
				DistritoDTO dis = new DistritoDTO();
				dis.setCodDistrito(rs.getInt(1));
				dis.setNomDistrito(rs.getString(2));
				lista.add(dis);
			}
		} catch (Exception e) {
			System.out.println("Error (ListarDistritos): " + e.getMessage());
		}finally {
			MySQLConexion.closeConexion(con);
		}
		return lista;
	}

	@Override
	public int registrar(UsuarioDTO u, int tipo) {
		int rs=0;
		Connection con=null;
		PreparedStatement pst=null;
		
		try {
			con= MySQLConexion.getConexion();
			if (tipo == 0) {
				String sql="insert into tb_usuarios values(null,?,?,?,?,?,0,1)";
				pst=con.prepareStatement(sql);
				pst.setString(1, u.getNombre());
				pst.setString(2, u.getApellido());
				pst.setInt(3, u.getCodDistrito());
				pst.setString(4, u.getUsuario());
				pst.setString(5, u.getClave());
			} else {
				String sql="insert into tb_usuarios values(null,?,?,?,?,?,?,?)";
				pst=con.prepareStatement(sql);
				pst.setString(1, u.getNombre());
				pst.setString(2, u.getApellido());
				pst.setInt(3, u.getCodDistrito());
				pst.setString(4, u.getUsuario());
				pst.setString(5, u.getClave());
				pst.setInt(6, u.getIdTipo());
				pst.setInt(7, u.getEstado());
			}
			rs= pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al registrar usuario" + e.getMessage());
		}finally {
			MySQLConexion.closeConexion(con);
		}
		return rs;
	}

}
