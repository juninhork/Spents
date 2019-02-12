package juniorbraga.com.br.spents.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UtilsActivity {

    private static UtilsActivity uniqueInstance;

    public static synchronized UtilsActivity getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new UtilsActivity();

        return uniqueInstance;
    }

    UtilsActivity(){}

    public void showIntent(Context context,Class nextClasse){
        Intent intent = new Intent(context,nextClasse);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void configMask(EditText editText,
                                  TextWatcher add, TextWatcher remove,
                                  String hint) {

        editText.removeTextChangedListener(remove);
        editText.addTextChangedListener(add);
        editText.setHint(hint);
    }

    public boolean isNullOurEmpity(String params){
        if (params !=null){
            return params.isEmpty();
        }
        return false;
    }

    public static boolean verificString(String param){
        if(param != null){
            if (param != "") {
                return true;
            }
        }
        return false;
    }


    public static String stringIsNullOrEmpty(String stringObject){
        if(stringObject != null){
            if(!stringObject.isEmpty())
                return stringObject;
        }
        return "";
    }

    public static void openAppPlayStore(String url , Context context){
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + url)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + url)));
        }catch (Exception e ){
            Toast.makeText(context,"Error ao redirecionar a Play Store",Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isListSize(ArrayList arrayList){
        if(arrayList != null){
            if(arrayList.size() > 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
