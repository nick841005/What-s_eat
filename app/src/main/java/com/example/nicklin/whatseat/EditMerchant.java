package com.example.nicklin.whatseat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditMerchant extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.tvMerchant)
    TextView tvMerchant;
    @BindView(R.id.etMerchantList)
    EditText etMerchantList;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_merchant);
        ButterKnife.bind(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File mSDPath = null;
                    if (Environment.getExternalStorageState().equals( Environment.MEDIA_REMOVED)) {
                        Toast.makeText(EditMerchant.this , "No SD Card!" , Toast.LENGTH_SHORT ).show();
                        return ;
                    } else {
                        mSDPath = Environment.getExternalStorageDirectory();
                    }

                    File mFile = new File(mSDPath.getParent() + "/" + mSDPath.getName() + "/What's_eat/", "Merchant.txt");
                    if (!mFile.exists()) {
                        mFile.mkdirs();
                    }
                    Log.d(TAG, "File exist:" + mFile.exists() + "  File path: " + mFile.getPath() + "  File name:" + mFile.getName());

//                    FileWriter mFileWriter = new FileWriter(mSDPath.getParent() + "/" + mSDPath.getName() + "/What's_eat/Merchant.txt");
//                    mFileWriter.write(etMerchantList.getText().toString());
//                    mFileWriter.close();
                    FileOutputStream  output = new FileOutputStream(mFile, false);
                    output.write(etMerchantList.getText().toString().getBytes());
                    output.close();

                    setResult(RESULT_OK, getIntent());
                    finish();
                } catch (FileNotFoundException e) {
                    Log.d(TAG, e.getMessage());
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditMerchant.this, MainActivity.class));
                finish();
            }
        });
    }
}
