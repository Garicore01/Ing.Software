package es.unizar.eina.send;

import android.app.Activity;

/** Concrete implementor utilizando la actividad de envio de SMS. No funciona en el emulador si no se ha configurado previamente */
public class SMSImplementor implements SendImplementor {

    /** actividad desde la cual se abrira la actividad de envio de SMS */
    private Activity sourceActivity;

    /** Constructor
     * @param source actividad desde la cual se abrira la actividad de envio de SMS
     */
    public SMSImplementor(Activity source){
        setSourceActivity(source);
    }

    /**  Actualiza la actividad desde la cual se abrira la actividad de envio de SMS */
    public void setSourceActivity(Activity source) {
        sourceActivity = source;
    }

    /**  Recupera la actividad desde la cual se abrira la actividad de envio de SMS */
    public Activity getSourceActivity(){
        return sourceActivity;
    }

    /**
     * Implementacion del metodo send utilizando la aplicacion de envio de SMS
     * @param phone telefono
     * @param message cuerpo del mensaje
     */
    public void send (String phone, String message) {
	// Por implementar
   }

}
