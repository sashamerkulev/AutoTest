package ru.merkulyevsasha.presentation.list;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import ru.merkulyevsasha.model.TitleItem;
import ru.merkulyevsasha.R;

class TitlesViewAdapter extends ArrayAdapter<TitleItem> {

    private final List<TitleItem> mItems;
    private final LayoutInflater mInflater;

    public TitlesViewAdapter(Context context, List<TitleItem> items) {
        super(context, R.layout.activity_main_listview_item, items);

        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void addAll(@NonNull Collection<? extends TitleItem> collection) {
        super.addAll(collection);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = mInflater.inflate(R.layout.activity_main_listview_item, parent, false);
            convertView.setTag(convertView.findViewById(R.id.textview_title));
        }
        TextView textViewTopic = (TextView) convertView.getTag();

        TitleItem item = mItems.get(position);

        textViewTopic.setText(item.getTitle());

        return convertView;
    }


}
