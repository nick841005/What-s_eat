package com.example.nicklin.whatseat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.tvShopName)
    TextView tvShopName;
    @BindView(R.id.btnDraw)
    Button btnDraw;
    @BindView(R.id.btnEdit)
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnDraw, R.id.btnEdit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDraw:
                break;
            case R.id.btnEdit:
                Log.d(TAG, "Go to edit activity!");
                startActivityForResult(new Intent(MainActivity.this, EditMerchant.class), RESULT_FIRST_USER);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FIRST_USER && resultCode == RESULT_OK) {
            Toast.makeText(MainActivity.this, "Merchant list is saved!", Toast.LENGTH_SHORT);
        }
    }
}
