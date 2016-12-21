# Custom Dialogs

Basic useful feature list:

 * Create simple dialog box.
 * Customise colors, buttons, position on screen.
 * Change dim value and transparency of the dialog box.
 * Modify Text, images as if handling a TextView or ImageView.
 * Auto-dismiss 
 * Block clicks outside the dialog box.


## Implementation

 * Download the library [customdialogs](https://github.com/aman23091998/CustomDialogs/tree/master/customdialogs/)   
 * Create an object of class CustomDialogs
 	```java
   	CustomDialogs.OnButtonClickListener positiveButton = new CustomDialogs.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
            //customize what happens on the button click!
                Toast.makeText(getApplicationContext(), "Positive Button Clicked!", Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean dismissOnClick() {
            // return true if the dialog dismisses on button click
            // else false
                return true;
            }
        };
        
    CustomDialogs dialog = CustomDialogs(context, "message", R.drawable.name, "positiveButtonText", positiveButton);
    ```
    
    For creating dialog boxes without an image, use the following constructor:
    
    ```java
    CustomDialogs noImageDialog = new CustomDialog (Context context, String message, String positiveButtonText, OnButtonClickListener onPositiveClickListener);
    ```
 * Add negative and neutral buttons by creating objects negeativeButton and neutralButton of the interface CustomDialogs.OnButtonClickListener, then call
  
 	```java
    dialog.enableNeutralButton("Neutral", neutralButton);
    dialog.enableNegativeButton("-ve", negativeButton);
   ```
   
   Add a negative button before creating a neutral button.
 * To make the dialog box transparent make the following call to the method:
 
    ```java
    dialog.makeDialogTransparent();
    ```
 * Set the dim value (outside the dialog). value is a float from 0 to 1f
   
   ```java
   dialog.setBackgroundDim(value);
   ```
 * Auto dismiss dialog box after after t secs
	
    ```java
    dialog.dismissAfter(t * 1000);
    ```
 * Position dialog box
   
   ```java
  	dialog.positionDialog(DialogGravity.BOTTOM);
  	dialog.positionDialog(DialogGravity.TOP);
  	dialog.positionDialog(DialogGravity.START);  // to align dialog to the left of the screen  		  
    dialog.positionDialog(DialogGravity.END);    // to align dialog to the right of the screen  		  
    dialog.positionDialog(DialogGravity.CENTER);
   ```
 * Change Background
   ```java
	  dialog.setBackground(Drawable drawableBackground);
  	  // to set a speccific color use
      dialog.setBackground(new ColorDrawable(Color.rgb(66, 244, 167))); // plug in the the RGB values of the required color.
      // or: 
      dialog.setBackground(new ColorDrawable(Color.argb(255, 66, 244, 167)));
      // the first parameter is the alpha value [0, 255]
 	 ```
 * Block clicks outside the Dialog
 	
    ```java
 	dialog.blockOutSideClick(true);
 	```
 * Displaying the dialog
    
    ```java
   	dialog.show();
   ```

* To customize the message, image and buttons make the following method calls after displaying the dialog box:

	```java
    
     ImageView dialogImage = dialog.getImageDrawable();

     TextView dialogMessage = dialog.getMessage();

     TextView positiveButtonTV = dialog.getPositiveButton();
		
     TextView neutralButtonTV = dialog.getNeutralButton();
     
     TextView negativeButtonTV = dialog.getNegativeButton();
    ```
