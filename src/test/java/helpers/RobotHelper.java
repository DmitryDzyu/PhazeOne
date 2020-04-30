package helpers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

/**
 * Класс для работы c Hotkey
 */
public class RobotHelper {

public static void sendKeysCombo(String keys[]) {

 try{
     Robot robot = new Robot();

     Class<?> cl = KeyEvent.class;

     int [] intKeys = new int [keys.length];

     for (int i = 0; i < keys.length; i++){
         Field field = cl.getDeclaredField(keys[i]);
         intKeys[i] = field.getInt(field);
         robot.keyPress(intKeys[i]);
     }

     for (int i = keys.length - 1; i >= 0; i--)
         robot.keyRelease(intKeys[i]);
 }
 catch (Throwable e){
     System.err.println(e);
 }

}

}
