package kisiselgelisim.moonturns.com.kisiselgelisim.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSettings {

    private static final String PREFERENCES_NAME = "userSettings";
    private static final String RCYCLERVIEW_STYLE = "style"; //data for rcyclerview style

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    //sharedprefences state
    public static void statementPreferences(Context context) {

        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (!sharedPreferences.contains("style")) {

            editor.putInt("style", 0);
            editor.commit();

        }

    }

    public static SharedPreferences getSharedPreferences() {

        return sharedPreferences;

    }

    public static SharedPreferences.Editor getEditor(){

        return editor;

    }

}
