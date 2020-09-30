package etiquetas;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import beans.CategoriaDTO;
import dao.DAOFactory;
import interfaces.ProductoDAO;

public class LlenaComboTag extends TagSupport {
	
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
			out.println("<option value = \"0\">Seleccione</option>");
			
			DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			ProductoDAO dao = fabrica.getProductoDAO();
			ArrayList<CategoriaDTO> listado = dao.listadoCategoria();
			
			for (CategoriaDTO c : listado) {
				if (c.getIdcategoria() == value) 
					out.println("<option value=\""+ c.getIdcategoria() + "\" selected=\"selected\" >" + c.getDescripcion() +"</option>");
				else
					out.println("<option value=\"" + c.getIdcategoria() + "\">" + c.getDescripcion() + "</option>");
			}
			
		} catch (IOException e) {
			System.out.println("Error al realizar el Tag llenaCombo: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
