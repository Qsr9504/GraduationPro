package com.qsr.graduationpro.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a.a.V;
import com.qsr.graduationpro.R;

/**************************************
 * FileName : com.qsr.graduationpro.ui
 * Author : qsr
 * Time : 2017/5/5 0:08
 * Description :
 **************************************/
public class InfoLine extends LinearLayout {
    private TextView tv_key;
    private TextView tv_value;
    private View line;
    public InfoLine(Context context) {
        super(context,null);
        init(context);
    }

    public InfoLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InfoLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.info_line,null);
        tv_key = (TextView) view.findViewById(R.id.info_key);
        tv_value = (TextView) view.findViewById(R.id.info_value);
        line = view.findViewById(R.id.info_line);
    }

    public void setContent(String key,String value){
        tv_key.setText(key);
        tv_value.setText(value);
    }
    public void setDisLine(){
        line.setVisibility(INVISIBLE);
    }
}
