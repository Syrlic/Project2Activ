package com.example.project2activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2activity.utils.RequestCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView result;
    private TextView confirmPassword;
    private EditText logIn;
    private EditText password;
    private EditText password2;
    private String reallogin;
    private String realpassword;
    private CheckBox checkBox;
    private Button button;
    private FrameLayout addTextview;
    private FrameLayout addEditField;
    private Button timer;
    private List<User> users = User.getAllUsers();
    private TextView phone;
    private TextView messege;
    private final String TAG = "Project2Activ";
    private final int NOTIFICATION_ID = 23;
    private final String CHANNEL_ID = "TIPS";
    private NotificationManager manager;
    private Button push;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        reallogin = "admin";
        realpassword = "000999";

        result = (TextView) findViewById(R.id.responce);
        logIn = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.pass1);
        addTextview = (FrameLayout) findViewById(R.id.addtext);
        addEditField = (FrameLayout) findViewById(R.id.addfield);
        checkBox = (CheckBox) findViewById(R.id.checkbox1);
        button = (Button) findViewById(R.id.btn_logreg);
        phone = (TextView) findViewById(R.id.txt_phone);
        messege = (TextView) findViewById(R.id.txt_msg);

        confirmPassword = new TextView(this);
        confirmPassword.setText(R.string.txt_confirm);
        password2 = new EditText(this);
        password2.setEms(15);
        password2.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        checkBox.setOnClickListener(this);
        timer = (Button) findViewById(R.id.btn_contacts);

        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        push = (Button) findViewById(R.id.btn_push);

        Log.i(TAG, "LoginActivity: onCreate");
        Toast.makeText(this, "LoginActivity: onCreate()", Toast.LENGTH_SHORT).show();
        createNotificationChannel();
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "LoginActivity: onClick");
        result.setText(R.string.empty_txt);
        if(checkBox.isChecked()){
            addTextview.addView(confirmPassword);
            addEditField.addView(password2);
            button.setText(R.string.txt_singup);
        }else{
            clearChechBox();
//            addTextview.removeView(confirmPassword);
//            addEditField.removeView(password2);
//            button.setText(R.string.btn_login);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "LoginActivity: onActivityResult()");
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case RequestCode.PHONENUMBER:
                    String phoneNum = data.getStringExtra("phone");
                    phone.setText("Send to: "+phoneNum);
                    break;
                case RequestCode.MESSEGE:
                    String msg = data.getStringExtra("sms");
                    messege.setText(msg);
                    break;
            }
        }else {
            Toast.makeText(this, "Error get data", Toast.LENGTH_SHORT).show();
        }
    }

    public void click(View view){
        result.setText(R.string.empty_txt);
        Log.i(TAG, "LoginActivity: click()");
        if(checkBox.isChecked()){
            String logintxt = logIn.getText().toString();
            String password1txt = password.getText().toString();
            String password2txt = password2.getText().toString();
            signup(logintxt, password1txt, password2txt);
            clearChechBox();
        }else {
            String logintxt = logIn.getText().toString();
            String password1txt = password.getText().toString();
            singin(logintxt, password1txt);
            clearChechBox();
            goToTimerActivityWithLogin(view, logintxt);
        }
    }

    private void goToTimerActivityWithLogin(View view, String logintxt) {
        Log.i(TAG, "LoginActivity: goToTimerActivityWithLogin()");
        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra("login", logintxt);
        startActivity(intent);
    }

    private void singin(String login, String password) {
        Log.i(TAG, "LoginActivity: signin()");
        result.setText(R.string.empty_txt);
        int check = 0;
        for(User u:users) {
            System.out.println(users.size());
            if (u.getLoginUser().equals(login)) {
                check = 1;
                if(u.getPassword1User().equals(password)){
                    check = 2;
                    break;
                }
            }
        }
        switch (check){
            case 2: result.setText("Welcome, " + login);
            break;
            case 1: result.setText("Incorrect password");
            break;
            case 0: result.setText("User "+login+ " don't sign up");
            break;
        }
    }

    private void signup(String login, String password, String password2) {
        Log.i(TAG, "LoginActivity: signup()");
        result.setText(R.string.empty_txt);
        User user = new User(login, password, password2);

        if(!users.contains(user)){
            users.add(user);
            result.setText("Registrations succsessful! Welcome "+ login);
//            this.logIn.setText("");
//            this.password.setText("");
        }else {
            result.setText("This user allready exists!");
        }

    }

    private void clearChechBox(){
        Log.i(TAG, "LoginActivity: clearChechBox()");
        checkBox.setChecked(false);
        password2.setText("");
        addTextview.removeView(confirmPassword);
        addEditField.removeView(password2);
        button.setText(R.string.btn_login);
    }

    public void goToSendSMSActivity(View view) {
        Log.i(TAG, "LoginActivity: goToSendSMSActivity()");
        boolean logic = view.getId() == R.id.btn_contacts;
        Intent intent = (logic)?  chooseContact(view):
                        new Intent(this, InitialOnClickButton.class);
        int result = (logic) ? RequestCode.PHONENUMBER : RequestCode.MESSEGE;
        startActivityForResult(intent, result);
    }

    private Intent chooseContact(View view){
        Log.i(TAG, "LoginActivity: chooseContact()");
        Intent intent = new  Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        String s = ContactsContract.CommonDataKinds.Phone.NUMBER;
        intent.putExtra("phone", s);
        setResult(RESULT_OK, intent);
        return intent;
    }

    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Project2Aktiv")
                .setCancelable(true)
                .setIcon(R.drawable.face_dog)
                .setMessage("Exit from application?")
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LoginActivity.this, "Continue working", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showNotification(View view) {

        Log.i(TAG, "LoginActivity: showNotification()");
        Toast.makeText(this, "showNotification", Toast.LENGTH_SHORT).show();

        Notification.Builder builder = new Notification.Builder(getApplicationContext());

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.dog)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.buu))
                .setTicker("New notification")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Notification")
                .setContentText("Press that pass to main screen");

        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_VIBRATE;
       // Notification.DEFAULT_SOUND;
                //| Notification.DEFAULT_LIGHTS;
        notification.sound = Uri.parse("android.resourse://"+getPackageName()+"/"+R.raw.what);

//        long[] vibrate = {1500,1000,1500,1000};
//        notification.vibrate = vibrate;
       notification.flags = notification.flags | Notification.FLAG_INSISTENT;


  //      manager.notify(NOTIFICATION_ID, notification);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.str_notename);
            String description = getString(R.string.str_notename);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}

class User {

    private String loginUser;
    private String password1User;
    private String passwordTwo;

    private static List<User> allUsers = new ArrayList<>();

    public User(String login, String password1, String password2) {
        this.loginUser = login;
        this.password1User = password1;
        this.passwordTwo = password2;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public String getPassword1User() {
        return password1User;
    }

    public String getPassword2() {
        return passwordTwo;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(loginUser, user.loginUser) &&
                Objects.equals(password1User, user.password1User);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginUser, password1User, passwordTwo);
    }
}
