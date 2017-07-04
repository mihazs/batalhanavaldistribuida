/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import reflect.annotations.ParamName;
import reflect.annotations.Path;
import reflect.annotations.Resource;

/**
 *
 * @author Mihael Zamin
 */
@Resource
@Path("leoloch")
public class LeoLochResource {
    @Path("olaleo")
    public String helloleo(@ParamName("mensagem") String mensagem){
        return "O leonardinho comunica que ele é um: " + mensagem;
    }
}
