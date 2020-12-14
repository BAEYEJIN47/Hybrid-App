package com.example.dit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ReportListAdapter extends BaseAdapter {
    final String TAG = getClass().getSimpleName() + "::";
    private Context context;
    private List<Report> reportList;

    public ReportListAdapter(Context context, List<Report> reportList) {
        this.context = context;
        this.reportList = reportList;
    }

    @Override
    public int getCount() {
        return reportList.size();
    }

    @Override
    public Object getItem(int i) {
        return reportList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View converView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.report, null);
        TextView reportText = (TextView) v.findViewById(R.id.reportText);
        TextView nameText = (TextView) v.findViewById(R.id.nameText);
        TextView dateText = (TextView) v.findViewById(R.id.dateText);

        reportText.setText(reportList.get(i).getReport());
        nameText.setText(reportList.get(i).getName());
        dateText.setText(reportList.get(i).getDate());

        v.setTag(reportList.get(i).getReport());
        return v;
    }
}
