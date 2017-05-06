package com.qsr.graduationpro.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.mvp.model.data.Record;

import java.util.List;

import butterknife.Bind;

/**************************************
 * FileName : com.qsr.graduationpro.mvp.view.adapter
 * Author : qsr
 * Time : 2017/5/6 22:04
 * Description :
 **************************************/
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private List<Record> recordList;
    private Context context;
    private LayoutInflater mInflater;

    public RecordAdapter(Context context, List<Record> recordList) {
        this.recordList = recordList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.record_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.cvTime.setText("时间：" + recordList.get(position).getRecordTime());
        holder.cvAddress.setText("地点：" + recordList.get(position).getRecordPlace());
        holder.cvContent.setText("主要内容："+recordList.get(position).getRecordContent());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cvTime;
        TextView cvAddress;
        TextView cvContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            cvTime = (TextView) itemView.findViewById(R.id.cv_time);
            cvAddress = (TextView) itemView.findViewById(R.id.cv_address);
            cvContent = (TextView) itemView.findViewById(R.id.cv_content);
        }
    }
}
