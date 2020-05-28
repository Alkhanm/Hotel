package br.com.faculdadedelta.hotel.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.hotel.dao.QuartoDAO;
import br.com.faculdadedelta.hotel.modelo.Quarto;

@FacesConverter(value = "quartoConverter")
public class QuartoConverter implements Converter {
	private QuartoDAO qd = new QuartoDAO();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {
			try {
				return qd.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((Quarto)valor).getId());
		}
		return null;
	}

	
	
}
