package user.trainqrgen;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.widget.ImageView;
import java.io.FileOutputStream;
import android.os.Environment;

import android.view.View;
import android.widget.TextView;

import java.lang.*;

import android.widget.Toast;

import android.widget.Button;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import android.os.Handler;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.WriterException;
import java.io.File;

import java.text.DateFormat;
import java.util.Calendar;


public class QRCodeActivity extends AppCompatActivity   {

    public String both;
    public String mEncodeString;
    public TextView mTextDesc;
    public ImageView mImageQR;
    public ProgressBar mProgress;
    public String imageInSD = "/sdcard/bobo.jpg/";
    public Bitmap mBitmapQR;

    LocationManager locationManager;
    Handler handler;
    Location location;
String currentlat,currentlon;
    double latitude;
    double longitude;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);


        Intent intent = getIntent();
        String startlocation = intent.getExtras().getString("startlocation");
        String endlocation = intent.getExtras().getString("endlocation");
        both = startlocation + " " + endlocation;





        new AsyncGenerateQRCode().execute(GenerateQR.MARGIN_NONE);


        mImageQR = (ImageView) findViewById(R.id.mImageQR);






        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImageQR.setVisibility(View.VISIBLE);
                } else {
                    mImageQR.setVisibility(View.GONE
                    );
                }
            }
        });




        Button yourButton = (Button) findViewById(R.id.button11);
        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), PostLatLonService.class));
            }
        });


        Button yourButton22 = (Button) findViewById(R.id.button22);
        yourButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), PostLatLonService.class));
            }
        });


        // GPS start


    }


//GPS end here


    // GPS post





//            double d = latitude;
//           trimcurrentlat = ( (double) ( (int) (d * 100.0) ) ) / 100.0 ;
//
//
//            double d1 = longitude;
//            trimcurrentlon = ( (double) ( (int) (d1 * 100.0) ) ) / 100.0 ;






// posting code strt here






//end here






    /**
     * AsyncTask to generate QR Code image
     */
    private class AsyncGenerateQRCode extends AsyncTask<Integer, Void, Integer> {


        @Override
        protected Integer doInBackground(Integer... params) {
            if (params.length != 1) {
                throw new IllegalArgumentException("Must pass QR Code margin value as argument");
            }

            try {
                final int colorQR = Color.BLACK;
                final int colorBackQR = Color.WHITE;
                final int marginSize = params[0];
                final int width = 400;
                final int height = 400;


                String mydate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                final String data = "FOR GIVE USER \n" + both + "\n" + " " + "\n" + mydate + "\nTICKET IS: VALID";
                mBitmapQR = GenerateQR.generateBitmap(data, width, height,
                        marginSize, colorQR, colorBackQR);
            } catch (IllegalArgumentException iae) {

                iae.printStackTrace();
                return 0;
            } catch (WriterException we) {

                we.printStackTrace();
                return 0;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (result != 0) {
                mImageQR.setImageBitmap(mBitmapQR);
                mImageQR.setVisibility(View.GONE);
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/bobo.jpg");
                try {
                    file.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(file);
                    mBitmapQR.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "file path" + getFileStreamPath("bobo.jpg"), Toast.LENGTH_LONG).show();

            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }










}
