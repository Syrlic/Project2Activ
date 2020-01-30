package com.example.project2activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class AddDataFromDialogWindow extends Activity implements DialogInterface.OnClickListener {

    private final String TAG = "Project2Activ";
    private final int DIALOG = 1;
    private ListView list;
    private EditText nameEdit;
    private EditText ageEdit;
    private List<String> amountContacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_data);

        amountContacts = new ArrayList<>();
        list = (ListView) findViewById(R.id.list_contacts);

        Log.i(TAG, "AddDataFromDialogWindow: onCreate");
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        builder.setTitle("Create new contact")
                .setView(view)
                .setPositiveButton("Create", this)
                .setNegativeButton("Cancel", null);
        return builder.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if(id == DIALOG){
            TextView count = (TextView) dialog.getWindow().findViewById(R.id.txt_count);
            nameEdit = (EditText) dialog.getWindow().findViewById(R.id.edit_name);
            ageEdit = (EditText) dialog.getWindow().findViewById(R.id.edit_age);
            count.setText("Created "+amountContacts.size()+ " contacts");
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String nameD = nameEdit.getText().toString();
        String ageD = ageEdit.getText().toString();
        amountContacts.add(nameD+"\n"+ageD);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,amountContacts);
        list.setAdapter(adapter);
    }

    public void addContact(View view) {
        showDialog(DIALOG);
    }

    public void exit(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }
}
