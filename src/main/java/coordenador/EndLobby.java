/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenador;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author PedroHenrique
 */
public class EndLobby {
    private Timer timer;
    private boolean done = false; 
    private boolean triggered = false;
    
    public void executar(int seconds) {
       if(!triggered){
           done = false;
           triggered = true;
           timer = new Timer();
           timer.schedule(new RemindTask(), seconds*1000);    
        }
    }

    class RemindTask extends TimerTask {
        
        @Override
        public void run() {
            
            done = true;
            System.out.println("TimerTask: Fim do Lobby!");
            timer.cancel(); //Terminate the timer thread
        }
    }
    
    public boolean getDone(){
        return done;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
}