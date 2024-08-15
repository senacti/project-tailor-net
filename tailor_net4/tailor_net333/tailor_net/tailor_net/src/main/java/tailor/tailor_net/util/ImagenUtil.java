package tailor.tailor_net.util;

import java.lang.reflect.Array;
import java.util.Base64;

public class ImagenUtil {
   public  static String byteAString64(byte[] bytes){
      return Base64.getEncoder().encodeToString(bytes);

   }


}
