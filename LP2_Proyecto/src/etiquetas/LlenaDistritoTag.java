package etiquetas;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import beans.DistritoDTO;
import dao.DAOFactory;
import interfaces.UsuarioDAO;

public class LlenaDistritoTag extends TagSupport{
	
	private int value = -1;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int doStartTag() throws JspException{
		JspWriter out = pageContext.getOut();
		
		try {
			out.println("<option value=\"0\">Seleccione</option>");

			DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			UsuarioDAO dao= factory.getUsuarioDAO();
			ArrayList<DistritoDTO> lista= dao.listadoDistrito();
			
			for (DistritoDTO dis : lista) {
				if (dis.getCodDistrito() == value) 
					out.println("<option value=\""+ dis.getCodDistrito() + "\" selected=\"selected\" >" + dis.getNomDistrito() +"</option>");
				else
					out.println("<option value='" + dis.getCodDistrito() + "'>" + dis.getNomDistrito() + "</option>");
			}
			
		} catch (Exception e) {
			System.out.println("Error en el tag de distrito: " + e.getMessage());
		}
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
