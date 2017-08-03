package zsq.com.mybutterknife.have_a_test;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Shaoqiang.Zhang on 2017/8/3.
 */

public class ButterKnife {

    public static final void bindView(final Activity activity){

        traversalMethod(activity);
        traversalField(activity);

    }

    private static void traversalField(Activity activity) {

        Field[] fieldArray=getObjectFieldArray(activity);
        for (final Field field:fieldArray){
            if(isAnnotationPresent(field)){

                int viewID = getViewID(field);

                findViewByID(activity,field,viewID);

            }
        }
    }

    private static void findViewByID(Activity activity, Field field, int viewID) {

        try {
            field.set(activity,activity.findViewById(viewID));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    private static int getViewID(Field field) {
        return field.getAnnotation(FindViewByID.class).value();
    }

    private static Field[] getObjectFieldArray(Activity activity) {
        return activity.getClass().getFields();
    }

    private static void traversalMethod(final Activity activity) {
        Method[] methodArray = getObjectMethodArray(activity);
        for (final Method method:methodArray){
            if(isAnnotationPresent(method)){

                int viewID = getViewID(method);

                setOnClickListenerForControl(activity, method, viewID);

            }
        }
    }

    private static void setOnClickListenerForControl(final Activity activity, final Method method, int viewID) {
        activity.findViewById(viewID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    method.invoke(activity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private static int getViewID(Method method) {
        return method.getAnnotation(OnClick.class).value();
    }

    private static boolean isAnnotationPresent(Method method) {
        return method.isAnnotationPresent(OnClick.class);
    }

    private static boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(FindViewByID.class);
    }

    private static Method[] getObjectMethodArray(Activity activity) {
        return activity.getClass().getMethods();
    }
}
