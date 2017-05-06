package com.qsr.graduationpro.mvp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.utils.ActivityManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.flowtextview.FlowTextView;

public class QmessActivity extends FragmentActivity implements View.OnClickListener{
    private static final float defaultFontSize = 23.0f;
    @Bind(R.id.ftv)
    FlowTextView ftv;
    @Bind(R.id.upSize)
    Button upSize;
    @Bind(R.id.downSize)
    Button downSize;
    @Bind(R.id.reSize)
    Button reSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmess);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        upSize.setOnClickListener(this);
        downSize.setOnClickListener(this);
        reSize.setOnClickListener(this);
        String content = getStringText();
        Spanned html = Html.fromHtml(content);
        ftv.setText(html);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.upSize:
                increaseFontSize();
                break;
            case R.id.downSize:
                decreaseFontSize();
                break;
            case R.id.reSize:
                reset();
                break;
            default:
                break;
        }
    }


    private void increaseFontSize(){
        float currentFontSize = ftv.getTextsize();
        ftv.setTextSize(currentFontSize+1);
    }

    private void decreaseFontSize(){
        float currentFontSize = ftv.getTextsize();
        ftv.setTextSize(currentFontSize-1);
    }

    private void reset(){
        ftv.setTextSize(defaultFontSize);
    }

    public String getStringText() {
        return "    乔姓源于姬姓，出自远古时期为黄帝守灵之后裔，属于以地名为氏。 据史籍《元和姓纂》、《万姓统谱》记载，相传中原各族的共同祖先黄帝逝世后葬于桥山(今陕西黄陵)， 子孙中有留在桥山守陵看山的，于是这些人就以山名为氏，称为桥氏。\n"
                + "    桥氏改为乔氏，大致是在南北朝的北魏王朝后期。据史籍《新百家姓》记载，东汉时期有太尉桥玄， 他的六世孙桥勤在北魏王朝晚期任平原内史。"
                +"北魏孝武帝元修（公元532～534年在位）因不堪忍受宰相高欢的专权而逃出朝廷，桥勤追随北魏孝武帝一起投奔到宇文泰建立的西魏政权。"
                +"有一天，宇文泰心血来潮，叫桥勤去掉其姓氏桥的“木”偏旁，变成“乔”字，取“乔”之高远之意。面对强权，桥勤不敢不从，从此改桥氏为乔氏，世代相传至今，史称乔氏正宗，是为陕西乔氏。\n"
                +"    该支乔氏与桥氏同宗同源。乔姓从诞生开始，就与北方战乱联在一起，汉晋时期主要活动在山西、陕西、内蒙古、河北、河南等地。"
                +"南北朝时已经越过长江进入湖南、四川等省。其后各地桥姓去木为乔姓，已经遍布黄河南北和长江流域，尤其在河南东部和安徽西北地区形成著名的梁国乔氏望族。"
                +"唐宋时期，乔姓向东部山东、向东南江浙地区发展，形成了以河南为中心，向四周放射的分布状。"
                +"明朝以后基本形成了豫鲁为中心的乔姓聚集区。"
                +"宋朝时，乔姓大约有近5万人，约占全国人口的0.06%，排在第一百八十位以后。\n"
                +"    乔姓第一大省是河南，约占全国乔姓总人口的19%。"
                +"乔姓在全国的分布主要集中于河南、浙江、山东，这三省乔姓大约占全国乔姓总人口的56%，其次分布于山西、甘肃、江苏等省。全国初步形成了以河南为中心，向外逐渐梯度分布状。\n"
                +"明朝时，乔姓大约有11万人，约占全国人口的0.11%，排在第一百三十位以后。"
                +"宋、元、明600年中乔姓人口增长率高于全国人口增长率。"
                +"乔姓在全国的分布主要集中于山西、陕西、河南、河北、江苏，这五省集中了乔姓人口的91%。\n";
    }
}
