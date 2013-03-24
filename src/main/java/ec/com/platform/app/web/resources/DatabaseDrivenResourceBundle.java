package ec.com.platform.app.web.resources;

import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import ec.com.platform.app.core.service.AppS;
import ec.com.platform.app.model.Bundle;
import ec.com.platform.app.spring.utils.SpringUtils;
import ec.com.platform.util.Constantes;


/**
 * Custom ResourceBundle para acceder a propiedades en la BD
 * 
 * @author Eduardo Plua Alay
 * @since 2013/02/15
 */
public class DatabaseDrivenResourceBundle extends ResourceBundle {
 
    private AppS appS;
 
    public DatabaseDrivenResourceBundle() {
    	appS = SpringUtils.getBean("appS", AppS.class);
    }
    
    @Override
    protected Object handleGetObject(String key) {
        final Bundle messageResource = appS
                .obtenerMessageResourcePorCodeYLocale(key, FacesContext.getCurrentInstance()
                        .getViewRoot().getLocale().getLanguage());
        if (messageResource != null) {
            return messageResource.getValor();
        }
        return new StringBuilder(Constantes.PREFIX_NOT_FOUND).append(key).append(Constantes.PREFIX_NOT_FOUND).toString();
    }
 
    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(appS
                .obtenerCodeMessageResourcePorLocale(FacesContext.getCurrentInstance().getViewRoot()
                        .getLocale().getLanguage()));
    }
 
}