package monk.com.mx.misalleandroid.view.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.model.dataModels.SistemaObject;

/**
 * Created by edago on 8/6/17.
 */

public class LoginSpinnerAdapter extends ArrayAdapter<SistemaObject> {

    private Context context;
    private SistemaObject[] values;

    public LoginSpinnerAdapter(Context context, int textViewResourceId, SistemaObject[] values) {
        super(context, textViewResourceId, values);

        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.length;
    }

    public SistemaObject getItem(int position){
        return values[position];
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label.setLayoutParams(layoutParams);
        label.setTextSize(20);
        label.setGravity(Gravity.CENTER);
        label.setTextColor(context.getResources().getColor(R.color.colorDarkGray));
        label.setText(values[position].getTexto());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label.setLayoutParams(layoutParams);
        label.setTextSize(20);
        label.setGravity(Gravity.CENTER);
        label.setTextColor(context.getResources().getColor(R.color.colorDarkGray));
        label.setText(values[position].getTexto());
        label.setPadding(5,5,5,5);

        return label;
    }
}
