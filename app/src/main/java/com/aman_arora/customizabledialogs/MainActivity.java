package com.aman_arora.customizabledialogs;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aman_arora.customdialogs.CustomDialogs;
import com.aman_arora.customdialogs.DialogGravity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3, button4, button5, button6;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Custom Dialogs Demo");
        button1 = (Button) findViewById(R.id.b1);
        button2 = (Button) findViewById(R.id.b2);
        button3 = (Button) findViewById(R.id.b3);
        button4 = (Button) findViewById(R.id.b4);
        button5 = (Button) findViewById(R.id.b5);
        button6 = (Button) findViewById(R.id.b6);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        CustomDialogs.OnButtonClickListener positiveButton = new CustomDialogs.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                Toast.makeText(getApplicationContext(), "Positive Button Clicked!", Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean dismissOnClick() {
                return true;
            }
        };

        CustomDialogs.OnButtonClickListener negativeButton = new CustomDialogs.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                Toast.makeText(getApplicationContext(), "Negative Button Clicked!", Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean dismissOnClick() {
                return true;
            }
        };

        CustomDialogs.OnButtonClickListener neutralButton = new CustomDialogs.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                Toast.makeText(getApplicationContext(), "Neutral Button Clicked!\nApparently I do nothing else but say my name", Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean dismissOnClick() {
                return false;
            }
        };

        final CustomDialogs dialog = new CustomDialogs(MainActivity.this, "A random dialog box appeared!", R.drawable.emoticon_cool, "Dismiss", positiveButton);

        switch (v.getId()) {
            case R.id.b1:
                //Simple dialog with a dismiss button text and an Image
                break;
            case R.id.b2:
                dialog.makeDialogTransparent();
                dialog.positionDialog(DialogGravity.BOTTOM);
                dialog.enableNegativeButton("-ve", negativeButton);
                dialog.setBackgroundDim(0.8f);
                // Dialog with transparent background, 80% dim, 2 buttons and positioned at the bottom of the screen
                break;
            case R.id.b3:
                dialog.setBackgroundDim(1f);
                dialog.enableNeutralButton("Neutral", neutralButton);
                dialog.enableNegativeButton("-ve", negativeButton);
                dialog.setBackground(new ColorDrawable(Color.rgb(66, 244, 167)));
                break;
                //Dialog with 100% dim, 3 buttons and grass green color
            case R.id.b4:
                dialog.dismissAfter(2000);
                //simple dialog box as in case 1 but dismisses itself after 2000ms(or 2 secs).
                break;

            case R.id.b5:
                dialog.blockOutSideClick(true);
                //simple dialog box as in case 1 but blocks clicks outside the dialog box.
                break;

            case R.id.b6:
                dialog.enableNegativeButton("Custom -ve", new CustomDialogs.OnButtonClickListener() {
                    @Override
                    public void onButtonClick() {
                        // something cool :P
                        dialog.getMessage().setText("Negative Button Clicked! Voila");
                        Toast.makeText(MainActivity.this, "Told ya, you can do anything you wish", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public boolean dismissOnClick() {
                        return false;
                    }
                });
                break;
        }
        dialog.show();


        if (v.getId() == R.id.b6)
            dialog.getPositiveButton().setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        if (v.getId() == R.id.b5){
            dialog.getMessage().setText("Click outside and make anything happen, can you?");
            dialog.getImageDrawable().setImageDrawable(getResources().getDrawable(R.drawable.emoticon_devil));
        }
        if (v.getId() == R.id.b4){
            dialog.getMessage().setText("Auto Destruct sequence initiated!");
            dialog.getImageDrawable().setImageDrawable(getResources().getDrawable(R.drawable.alert_circle));
        }



    }
}