/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import messages.Source;
import reflect.annotations.ParamName;
import reflect.annotations.Path;
import reflect.annotations.Resource;
import reflect.annotations.UserInfo;
import reflect.annotations.casting.Broadcast;

/**
 *
 * @author Mihael Zamin
 */
@Resource
@Path("helloworld")
public class HelloWorldResource {
    @UserInfo
    Source source;
    
    @Path("hello")
    public String hello(){
        return "Hello mihael";
    }
    @Path("helloparam")
    public String helloparam(@ParamName("nome") String nome, @ParamName("matriz")int[][] matriz){
        
        return "Hello " + nome + source.getId();
    }
}
