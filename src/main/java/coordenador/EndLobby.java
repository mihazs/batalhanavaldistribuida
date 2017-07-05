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
    private boolean Done; 
  
    
    public void executar(int seconds) {
        Done = false;
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);    
       
    }

    class RemindTask extends TimerTask {
        
        @Override
        public void run() {
            
            Done = true;
            System.out.println("TimerTask: Fim do Lobby!");
            timer.cancel(); //Terminate the timer thread
        }
    }
    
    public boolean getDone(){
        return Done;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
}