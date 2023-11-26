package es.unizar.eina.send;

import android.app.Activity;

/** Concrete implementor utilizando la aplicacion de WhatsApp. No funciona en el emulador si no se ha configurado previamente */
public class WhatsAppImplementor implements SendImplementor{
	
   /** actividad desde la cual se abrira la aplicacion de WhatsApp */
   private Activity sourceActivity;
   
   /** Constructor
    * @param source actividad desde la cual se abrira la aplicacion de Whatsapp
    */
   public WhatsAppImplementor(Activity source){
	   setSourceActivity(source);
   }

   /**  Actualiza la actividad desde la cual se abrira la actividad de gestion de correo */
   public void setSourceActivity(Activity source) {
	   sourceActivity = source;
   }

   /**  Recupera la actividad desde la cual se abrira la aplicacion de Whatsapp */
   public Activity getSourceActivity(){
     return sourceActivity;
   }

   /**
    * Implementacion del metodo send utilizando la aplicacion de WhatsApp
    * @param phone telefono
    * @param message cuerpo del mensaje
    */
   public void send (String phone, String message) {
	// Por implementar
   }

}
