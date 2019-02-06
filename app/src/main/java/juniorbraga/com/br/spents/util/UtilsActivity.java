package juniorbraga.com.br.spents.util;

import android.content.Context;
import android.content.Intent;

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
        context.startActivity(intent);
    }

    public boolean isNullOurEmpity(String params){
        if (params !=null){
            return params.isEmpty();
        }
        return false;
    }
}
