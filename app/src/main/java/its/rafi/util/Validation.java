package its.rafi.util;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.xwray.passwordview.PasswordView;

import static android.widget.Toast.LENGTH_SHORT;
import static java.security.AccessController.getContext;

/**
 * Created by ASUS on 6/9/2017.
 */

public class Validation {
    public static Boolean checkEmpty(EditText editText){
        if(editText.getText().length() == 0){
            editText.setError("Wajib diisi");
            return false;
        }
        return true;
    }

    public static Boolean checkPassword(EditText editText){
        if(editText.getText().length() == 0){
            Toast.makeText(editText.getContext(),"Password wajib diisi", LENGTH_SHORT).show();
            //editText.setError("Harap Diisi");
            return false;
        }
        return true;
    }
}
