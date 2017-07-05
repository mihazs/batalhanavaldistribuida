/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principalJogador.resources;

import messages.Source;
import principalJogador.batalha.model.Ataque;
import reflect.annotations.ParamName;
import reflect.annotations.Path;
import reflect.annotations.Resource;
import reflect.annotations.UserInfo;

/**
 *
 * @author Mihael Zamin
 */
@Resource
@Path("jogador")
public class JogadorResources {
    
    @UserInfo
    public Source source;
    
    @Path("atacar")
    public String ataque(@ParamName("ataque") Ataque a){
        String s = "";
        int feedbackTiro = DadosJogador.tp.getBatalhaNaval().recebeTiro(a.getLinha(), a.getColuna());
        switch(feedbackTiro){
            case 0:
            s = "acertou";
                break;
            case 1:
                s = "morri";
                break;
            case 2:
                s = "errou";
                break;
            default:
                s = "badrequest";
                break;
        }
        return s+"-"+source.getId();
    }
    
    @Path("notadefalecimento")
    public void morte(@ParamName("id") int idMorto){
        DadosJogador.tp.getBatalhaNaval().remPlayer(idMorto);
    }
    
    @Path("passarvez")
    public void passarVez(){
        System.out.println("Entrando em passar a vez");
        DadosJogador.tp.getBatalhaNaval().updtJogadorAtual();
        System.out.println("Saindo em passar a vez");
    }
}
