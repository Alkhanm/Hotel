package br.com.faculdadedelta.hotel.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.hotel.dao.ClienteDAO;
import br.com.faculdadedelta.hotel.modelo.Cliente;

@FacesConverter(value="clienteConverter")
public class ClienteConverter implements Converter {

	private ClienteDAO dao = new ClienteDAO();

	@Override
	public Object getAsObject (FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {
			try {
				return dao.pesquisarCliente(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((Cliente)valor).getId());
		}
		return null;
	}
	
	
}







