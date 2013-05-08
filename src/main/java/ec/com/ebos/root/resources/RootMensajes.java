package ec.com.ebos.root.resources;

import ec.com.ebos.util.Constantes;
import ec.com.ebos.util.MessageUtils;

import java.util.ResourceBundle;

/**
 * Permite obtener claves y valores del archivo de recursos del modulo generic
 * 
 * @author Eduardo Plua Alay
 */
public class RootMensajes {

	/**
	 * Path del archivo de propiedades por defecto. Este es el &uacute;nico que se debe especificar.
	 */
	private static final String BUNDLE_NAME = Constantes.DOMAIN_NAME+".root.resources.root";

	/**
	 * ResourceBundle que representa el archivo de propiedades seleccionado para obtener los mensajes.
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(String key, Object... params ){
		return MessageUtils.buildMessage(key, RESOURCE_BUNDLE, params);
	}

}