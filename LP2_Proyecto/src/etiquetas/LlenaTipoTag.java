package etiquetas;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LlenaTipoTag extends TagSupport {
	
	private int value = 0;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int doStartTag() throws JspException {
			JspWriter out = pageContext.getOut();
			value = value - 1;
			try {
				String aTipos[] = {"Cliente","Administrador"};
				
				out.println("<option value=\"-1\">Seleccione</option>");
					
				for (int i = 0; i < aTipos.length; i++) {
					if (value == i)
						out.println("<option value=\""+ i + "\" selected=\"selected\" >" + aTipos[i] +"</option>");
					else
						out.println("<option value=\""+ i + "\" >" + aTipos[i] +"</option>");
				}
			} catch (IOException e) {
				System.out.println("Error al realizar el Tag llenaTipo: " + e.getMessage());
			}
			return SKIP_BODY;
		}
	
		public int doEndTag() throws JspException {
			return EVAL_PAGE;
		}
}
