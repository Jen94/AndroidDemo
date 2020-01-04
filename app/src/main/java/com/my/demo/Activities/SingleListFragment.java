package com.my.demo.Activities;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.demo.R;


public class SingleListFragment extends ListFragment {

    final String[] elements = new String[]{"Первый элемент", "Второй элемент", "Третий элемент",
            "Четвертый элемент", "Пятый элемент"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
                R.layout.row_myfragment, elements);
        setListAdapter(myListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listfragment, null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(),
                getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
    }

    public class MyListAdapter extends ArrayAdapter<String> {

        private Context mContext;

        public MyListAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.row_myfragment, parent,
                    false);
            TextView catNameTextView = (TextView) row.findViewById(R.id.textViewName);
            catNameTextView.setText(elements[position]);
            ImageView iconImageView = (ImageView) row.findViewById(R.id.imageViewIcon);

            iconImageView.setImageResource(R.drawable.ic_launcher_foreground);

            return row;
        }
    }
}