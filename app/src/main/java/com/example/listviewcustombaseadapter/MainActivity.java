package com.example.listviewcustombaseadapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        this.customListViewViaBaseAdapter();
    }

    // This method shows how to use BaseAdapter to customize ListView widget.
    private void customListViewViaBaseAdapter()
    {
        setTitle("dev2qa.com - Custom SimpleAdapter List View Example");

        final String[] titleArr = { "Name", "Sex", "Age", "Location","Email"};
        final String[] descArr = { "Jerry", "Male", "43", "Singapore", "webmaster@dev2qa.com" };

        ArrayList<Map<String,Object>> itemDataList = new ArrayList<>();

        int titleLen = titleArr.length;
        for(int i =0; i < titleLen; i++) {
            Map<String,Object> listItemMap = new HashMap<>();
            listItemMap.put("imageId", R.mipmap.ic_launcher);
            listItemMap.put("title", titleArr[i]);
            listItemMap.put("description", descArr[i]);
            itemDataList.add(listItemMap);
        }

        // Create a BaseAdapter instance.
        BaseAdapter customBaseAdapter = new BaseAdapter() {
            // Return list view item count.
            @Override
            public int getCount() {
                return titleArr.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @SuppressLint("InflateParams")
            @Override
            public View getView(int itemIndex, View itemView, ViewGroup viewGroup) {

                if(itemView == null)
                {   // First inflate the RelativeView object.
                    itemView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
                }

                // Find related view object inside the itemView.
                ImageView imageView = (ImageView)itemView.findViewById(R.id.baseUserImage);
                TextView titleView = (TextView)itemView.findViewById(R.id.baseUserTitle);
                TextView descView = (TextView)itemView.findViewById(R.id.baseUserDesc);

                // Set background color by row number.
                int colorPos = itemIndex % 2;
                if(colorPos==0) {
                    itemView.setBackgroundColor(Color.YELLOW);
                }else
                {
                    itemView.setBackgroundColor(Color.GREEN);
                }
                // Set resources.
                imageView.setImageResource(R.mipmap.ic_launcher);

                final String title = titleArr[itemIndex];
                final String desc = descArr[itemIndex];
                titleView.setText(title);
                descView.setText(desc);

                // Find the button in list view row.
                Button itemButton = (Button)itemView.findViewById(R.id.baseUserButton);
                itemButton.setOnClickListener(view -> Toast.makeText(MainActivity.this, "You click " + title + " , " + desc, Toast.LENGTH_SHORT).show());

                return itemView;
            }
        };

        // Get the ListView object.
        ListView listView = (ListView)findViewById(R.id.listViewExample);
        // Set the custom base adapter to it.
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener((adapterView, view, index, l) -> {
            Object clickItemObj = adapterView.getAdapter().getItem(index);
            HashMap clickItemMap = (HashMap)clickItemObj;
            String itemTitle = (String)clickItemMap.get("title");
            String itemDescription = (String)clickItemMap.get("description");

            Toast.makeText(MainActivity.this, "You select item is  " + itemTitle + " , " + itemDescription, Toast.LENGTH_SHORT).show();
        });

    }
}