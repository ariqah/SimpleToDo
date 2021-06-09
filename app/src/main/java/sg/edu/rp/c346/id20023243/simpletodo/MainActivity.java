package sg.edu.rp.c346.id20023243.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnTaskType;
    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    ListView lvTasks;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnTaskType = findViewById(R.id.spinnerTaskType);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.listViewTasks);

        //create array list
        alTasks = new ArrayList<>();

        //create array adapter and link listView to arrayList
        aaTask = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, alTasks);

        lvTasks.setAdapter(aaTask);

        spnTaskType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 0: //for add new task
                        etTask.setHint("Type a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1: //for delete task
                        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                etTask.setText(""+position);
                            }
                        });
                        etTask.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addTask = etTask.getText().toString();
                alTasks.add(addTask);
                aaTask.notifyDataSetChanged();

                etTask.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.size()==0) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                    return;
                }
                int removeTask = Integer.parseInt(etTask.getText().toString());
                if(removeTask > alTasks.size()-1) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                    return;
                }

                alTasks.remove(removeTask);
                aaTask.notifyDataSetChanged();

                etTask.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                aaTask.notifyDataSetChanged();
            }
        });
    }
}