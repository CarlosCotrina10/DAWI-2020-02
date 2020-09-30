package etiquetas;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import beans.CategoriaDTO;
import dao.DAOFactory;
import interfaces.ProductoDAO;

public class LlenaTiendaTag extends TagSupport {
	private int value = -1;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if (value == 0) {
				out.println("<a href = \"tiendaSer?btnes=l&cat=0\" class=\"list-group-item active\">Todo</a>");
			} else {
				out.println("<a href = \"tiendaSer?btnes=l&cat=0\" class=\"list-group-item\">Todo</a>");
			}
			
			DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			ProductoDAO dao = fabrica.getProductoDAO();
			ArrayList<CategoriaDTO> listado = dao.listadoCategoria();
			
			for (CategoriaDTO c : listado) {
				if (c.getIdcategoria() == value) 
					out.println("<a href=\"tiendaSer?btnes=l&cat="+ c.getIdcategoria() + "\" class=\"list-group-item active\" >" + c.getDescripcion() +"</a>");
				else
					out.println("<a href=\"tiendaSer?btnes=l&cat=" + c.getIdcategoria() + "\" class=\"list-group-item\" >" + c.getDescripcion() + "</a>");
			}
			
		} catch (IOException e) {
			System.out.println("Error al realizar el Tag llenaTiendaTag: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}


