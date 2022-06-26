package com.example.whoislookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText ed1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textview);
        ed1=(EditText)findViewById(R.id.edittext);
        button=(Button)findViewById(R.id.btn);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("pythonpgm");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ed1.getText().toString().trim();
                PyObject n = pyobj.callAttr("main",url);
                Toast.makeText(getApplicationContext(),n.toString(),Toast.LENGTH_LONG).show();

                textView.setText(n.toString());
            }
        });
    }
}