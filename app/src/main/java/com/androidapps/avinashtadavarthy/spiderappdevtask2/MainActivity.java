package com.androidapps.avinashtadavarthy.spiderappdevtask2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button info = (Button) findViewById(R.id.info);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Pop.class));
            }
        });

        final Button btnadd = (Button) findViewById(R.id.btnadd);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final Button btndel = (Button) findViewById(R.id.btndel);
        final Button btndelall = (Button) findViewById(R.id.btndelall);
        ListView listView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, new ArrayList<String>());
        listView.setAdapter(adapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                String blank = "";

                if(text.equals(blank))
                    showToastMessage("No text found",500);
                else
                {
                    adapter.add(text);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });


        btndelall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (adapter.getCount()==0)
                    showToastMessage("List is empty",700);
                    else
                {
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });


        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                String blank = "";

                try{
                    Integer.parseInt(editText.getText().toString());

                    {
                        if(text.equals(blank)) {
                            editText.setText("");
                            showToastMessage("No text found", 800);
                        }
                        else if(adapter.getCount() < Integer.parseInt(editText.getText().toString())) {
                            editText.setText("");
                            showToastMessage("No item in that position", 700);
                        }
                        else if(Integer.parseInt(editText.getText().toString()) == 0){
                            editText.setText("");
                            showToastMessage("Items are numbered starting from 1",1500);
                        }
                        else
                        {
                            String sid = editText.getText().toString();
                            int iid = Integer.parseInt(sid)-1;

                            adapter.remove(adapter.getItem(iid));
                            adapter.notifyDataSetChanged();
                            editText.setText("");

                            showToastMessage("Item Deleted",700);
                        } }

                }catch(Exception e ){
                    editText.setText("");
                    showToastMessage("Not a valid position", 700);
                }

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.remove(adapter.getItem((int)id));
                adapter.notifyDataSetChanged();

               showToastMessage("Item Deleted",700);
                return true;
            }
        });


        nextpage();
    }

    public void nextpage()
    {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.txt);
                String text = textView.getText().toString();

                Intent next = new Intent(getApplicationContext(),Main2Activity.class);
                Bundle bundle = new Bundle();

                bundle.putString("pos",text);
                next.putExtras(bundle);
                startActivity(next);
            }
        });
    }


    public void showToastMessage(String text, int duration){
        final Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
    }
}
