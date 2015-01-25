////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                        //
//  OnePage.java - MyOnePageApp                                                           //
//  (Source file containing OnePage class, the main and only page of the app              //
//                                                                                        //
//  Language:        Java                                                                 //
//  Platform:        Android SDK                                                          //
//  Author:          Rupan Talwar, Email:rupantalwar@gmail.com, Phone: 315 751-2860       //
//  Created On:      1/23/2015                                                            //
////////////////////////////////////////////////////////////////////////////////////////////

package com.rupantalwar.myonepageapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ShareActionProvider;



public class OnePage extends Activity {

    private ShareActionProvider provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_page);

        //Declaring Imagebuttons or tiles that display on the main page
        final ImageButton imgButton;
        final ImageButton lnButton;
        final ImageButton gitButton;
        final ImageButton fbButton;
        final ImageButton leftButton;
        final ImageButton rightButton;


        imgButton = (ImageButton) findViewById(R.id.imageButton1);
        lnButton = (ImageButton) findViewById(R.id.lnImage);
        gitButton = (ImageButton) findViewById(R.id.gitImage);
        fbButton = (ImageButton) findViewById(R.id.fbImage);
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);


        //Setting up Swipe Detector class, used to instantiate custom swipe gestures on a ListView item
        final SwipeDetector swipeDetector = new SwipeDetector();
        imgButton.setOnTouchListener(swipeDetector);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (swipeDetector.swipeDetected())
                {
                    //do the onSwipe Right-to-Left action
                    Display display = getWindowManager().getDefaultDisplay();

                    //Setting up custom swipe animation
                    TranslateAnimation translateAnim = new TranslateAnimation(0, -display.getWidth(), 0, 0);
                    translateAnim.setDuration(300);
                    translateAnim.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imgButton.setImageResource(R.drawable.about);
                        }
                    });
                    imgButton.startAnimation(translateAnim);
                }

                else
                {
                    imgButton.setImageResource(R.drawable.rupan);
                }
            }

        });

        //Setting up on click event for the FB button
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.facebook.com/rupantalwar");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }

        });

        //Setting up on click event for the Github button
        gitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/rupantalwar");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }

        });

        //Setting up on click event for the LinkedIn button
        lnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/pub/rupan-talwar/4b/900/326/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }

        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OnePage.this);
                alertDialogBuilder.setTitle("About this App");
                alertDialogBuilder
                        .setMessage("* Dynamic Tiles UI" + "\n" +
                                "* Swipe left on my image to know more about me" + "\n" +
                                "* Click on the Zappos Tile to uncover more images"  + "\n" +
                                "* Click on ? Tile to launch a timed app info box")
                        .setCancelable(false);
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                //Making the Alert dialog box appear for 3000ms or 3 seconds
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                    }
                };

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        handler.removeCallbacks(runnable);
                    }
                });

                handler.postDelayed(runnable, 6000);
            }

        });

        //Creating an array of images
        final int[] image = { R.drawable.zappos2, R.drawable.zappos3,
                R.drawable.zappos1};


        //Displaying array images on button-click
        rightButton.setOnClickListener(new View.OnClickListener() {
            int i;
            @Override
            public void onClick(View view) {

                if(i>=image.length) {
                    i = 0;
                    rightButton.setImageResource(image[i]);
                    i++;
                }
                else {
                    rightButton.setImageResource(image[i]);
                    i++;
                }
            }

        });


}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_one_page, menu);


        //Setting up the Share Action Provider
        provider = (ShareActionProvider) menu.findItem(R.id.menu_share).getActionProvider();
        provider.setShareIntent(getDefaultShareIntent());
        return super.onCreateOptionsMenu(menu);
    }


    //Returns a share intent
    private Intent getDefaultShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT");
        intent.putExtra(Intent.EXTRA_TEXT,"Extra Text");
        return intent;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }
}
