package com.aman_arora.customdialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.VolumeProviderCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomDialogs extends Dialog {

    public interface OnButtonClickListener {
        void onButtonClick();
        boolean dismissOnClick();
    }

    private OnButtonClickListener mPositiveClickListener, mNegativeClickListener, mNeutralClickListener;

    private ImageView mImageDrawable;
    private TextView message, positiveButton, negativeButton, neutralButton;
    private String mMessage;
    private String mPositiveButtonText, mNegativeButtonText, mNeutralButtonText;
    private int mDrawableResourceID;
    private Drawable background;
    private float dimAmount = -1;
    private boolean noImage = false;


    private WindowManager.LayoutParams dialogParams = new WindowManager.LayoutParams();


    public CustomDialogs(Context context, String message, int drawableResourceID, String positiveButtonText, OnButtonClickListener onPositiveClickListener) {
        super(context);

        mMessage = message;
        mDrawableResourceID = drawableResourceID;
        mPositiveButtonText = positiveButtonText;
        mPositiveClickListener = onPositiveClickListener;
        dimAmount = -1 ;
    }


    public CustomDialogs(Context context, String message, String positiveButtonText, OnButtonClickListener onPositiveClickListener) {
        super(context);
        mMessage = message;
        noImage = true;
        mPositiveButtonText = positiveButtonText;
        mPositiveClickListener = onPositiveClickListener;
        dimAmount = -1 ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog);

        LinearLayout mRootView = (LinearLayout) findViewById(R.id.dialogRootView);

        if(background != null) mRootView.setBackground(background);

        mImageDrawable = (ImageView) findViewById(R.id.dialogDrawable);
        if(!noImage)mImageDrawable.setImageResource(mDrawableResourceID);

        LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) mImageDrawable.getLayoutParams();
        imageParams.gravity = Gravity.CENTER;
        mImageDrawable.setLayoutParams(imageParams);

        message = (TextView) findViewById(R.id.dialogMessage);
        message.setText(mMessage);

        positiveButton = (TextView) findViewById(R.id.positiveBtn);
        negativeButton = (TextView) findViewById(R.id.negativeBtn);
        neutralButton = (TextView) findViewById(R.id.neutralButton);


        positiveButton.setText(mPositiveButtonText);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPositiveClickListener.onButtonClick();
                if(mPositiveClickListener.dismissOnClick())dismiss();
            }
        });

        if (mNeutralButtonText != null) {
            neutralButton.setVisibility(View.VISIBLE);
            neutralButton.setText(mNeutralButtonText);
            neutralButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNeutralClickListener.onButtonClick();
                    if(mNeutralClickListener.dismissOnClick()) dismiss();
                }
            });
        }

        if (mNegativeButtonText != null) {
            negativeButton.setVisibility(View.VISIBLE);
            negativeButton.setText(mNegativeButtonText);
            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNegativeClickListener.onButtonClick();
                    if(mNegativeClickListener.dismissOnClick()) dismiss();
                }
            });
        }

    }


    public void enableNegativeButton(String negativeButtonText, OnButtonClickListener negativeClickListener) {
        mNegativeButtonText = negativeButtonText;
        mNegativeClickListener = negativeClickListener;
    }

    public void enableNeutralButton(String neutralButtonText, OnButtonClickListener neutralClickListener) {
        mNeutralButtonText = neutralButtonText;
        mNeutralClickListener = neutralClickListener;
    }

    public void setBackgroundDim(float dimValue) {
        dimAmount = dimValue;
        getWindow().setDimAmount(dimValue);
        dialogParams.dimAmount = dimValue ;
    }

    public void makeDialogTransparent() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void positionDialog(DialogGravity dialogGravity) {

        switch (dialogGravity) {
            case TOP:
                dialogParams.gravity = Gravity.TOP;
                break;
            case BOTTOM:
                dialogParams.gravity = Gravity.BOTTOM;
                break;
            case START:
                dialogParams.gravity = Gravity.START;
                break;
            case END:
                dialogParams.gravity = Gravity.END;
                break;
            case CENTER:
                dialogParams.gravity = Gravity.CENTER;
                break;
        }

        getWindow().setAttributes(dialogParams);
        if(dimAmount != -1)getWindow().setDimAmount(dimAmount);
    }

    public void setBackground(Drawable drawableBackground) {
        background = drawableBackground ;
    }

    public ImageView getImageDrawable() {
        return mImageDrawable;
    }

    public TextView getMessage() {
        return message;
    }

    public TextView getPositiveButton() {
        return positiveButton;
    }

    public TextView getNegativeButton() {
        return negativeButton;
    }

    public TextView getNeutralButton() {
        return neutralButton;
    }

    public void blockOutSideClick(boolean cancel) {
        super.setCanceledOnTouchOutside(!cancel);
    }

    public void dismissAfter(int millSeconds){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, millSeconds);

    }

}
