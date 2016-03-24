package teamone.tanfieldrailway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private TreasureHuntManager treasureHuntManager;
    private boolean didScan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey("teamone.tanfieldrailway.TreasureHuntManager")){
            treasureHuntManager = b.getParcelable("teamone.tanfieldrailway.TreasureHuntManager");
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    //go back to home when done
    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        String scanResult = rawResult.getText(); // Prints scan results
        System.out.println(scanResult);
        System.out.println(rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        for(int i = 0; i < treasureHuntManager.getTreasures().size(); i++) {
            System.out.println(treasureHuntManager.getTreasures().get(i).getName());
            if(treasureHuntManager.getTreasures().get(i).getName().equals(scanResult)) {
                if(treasureHuntManager.getTreasures().get(i).isFound()) {
                    Toast.makeText(ScanActivity.this, "You Have Already Found This Treasure!", Toast.LENGTH_LONG).show();
                    didScan = true;
                } else {
                    treasureHuntManager.treasureFound = true;
                    treasureHuntManager.foundTreasureName = scanResult;
                    treasureHuntManager.numTreasuresFound++;
                    treasureHuntManager.getTreasures().get(i).setFound(true);
                }
            }
        }
        if(!treasureHuntManager.treasureFound && !didScan) {
            Toast.makeText(ScanActivity.this, "No Treasure Found", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("teamone.tanfieldrailway.TreasureHuntManager", treasureHuntManager);
        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
        startActivity(i);
    }


}
