package etiquetas;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LlenaEstadoTag extends TagSupport {
	private int value = -1;
	
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
				String aEstados[] = {"Desactivado","Activo"};
				if (value == -1) {
					out.println("<option value=\"-1\">Seleccione</option>");
				}
					
				for (int i = 0; i < aEstados.length; i++) {
					if (value == i)
						out.println("<option value=\""+ i + "\" selected=\"selected\" >" + aEstados[i] +"</option>");
					else
						out.println("<option value=\""+ i + "\" >" + aEstados[i] +"</option>");
				}
				
			} catch (IOException e) {
				System.out.println("Error al realizar el Tag llenaEstado: " + e.getMessage());
			}
			return SKIP_BODY;
		}
	
		public int doEndTag() throws JspException {
			return EVAL_PAGE;
		}
}
