/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.converter;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.service.KwetterService;

/**
 *
 * @author Bart
 */
@Named
@Stateless
public class UserConverter implements Converter {
    
    @Inject
    private KwetterService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return service.findUserByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((User)value).getScreenName();
    }
    
}
