package com.example.adiagne.tocus;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.graphics.Color;
import android.widget.TextView;


import com.example.adiagne.tocus.R;

import java.util.ArrayList;

public class SearchTocusActivity extends Activity {

    ArrayList<String> list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tocus);
        ListView listview = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
        listview.setAdapter(adapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(Color.WHITE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_tocus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openConfirmation(View view){
        SearchView searchContent = (SearchView) findViewById(R.id.searchView);
        if(searchContent.getQuery().toString().equals("")) {
            Toast.makeText(SearchTocusActivity.this, "You need to type something to add ", Toast.LENGTH_SHORT
            ).show();
        }
        else
        {
            showPopUp();
        }
    }

    private void showPopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        SearchView searchContent = (SearchView) findViewById(R.id.searchView);
        helpBuilder.setTitle("Do you want to add '" + searchContent.getQuery().toString() +"' to your feed?");


        helpBuilder.setPositiveButton("Add Tocus",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        SearchView searchContent = (SearchView) findViewById(R.id.searchView);
                        list.add(searchContent.getQuery().toString());
                        adapter.notifyDataSetChanged();
                        searchContent.setQuery("",true);

                        Toast.makeText(SearchTocusActivity.this, "You have successfully added this Tocus to your feed ", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        helpBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }

    public void deleteTocus(View view) {
        ListView lv = (ListView) findViewById(R.id.listView);

        if (lv.getCheckedItemCount()  < 0 )
        {
            Toast.makeText(this, "Choose at least one Tocus to delete", Toast.LENGTH_LONG).show();
        }
        else
        {
            showPopUpdel();
        }
    }

    private void showPopUpdel() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        ListView lv = (ListView) findViewById(R.id.listView);
        if(lv.getCheckedItemCount()>1)
        {
            helpBuilder.setTitle("Do you want to delete these Tocus ?");
        }
        if(lv.getCheckedItemCount()==1)
        {
            helpBuilder.setTitle("Do you want to delete this Tocus ?");
        }



        helpBuilder.setPositiveButton("Delete Tocus",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                        ListView lv = (ListView) findViewById(R.id.listView);
                        SparseBooleanArray checkedItemPositions = lv.getCheckedItemPositions();
                        int itemCount = lv.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                adapter.remove(list.get(i));
                            }
                        }
                        checkedItemPositions.clear();
                        adapter.notifyDataSetChanged();

                        Toast.makeText(SearchTocusActivity.this, "You have successfully deleted this Tocus ", Toast.LENGTH_LONG).show();

                    }
                }
        );

        helpBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }

}
