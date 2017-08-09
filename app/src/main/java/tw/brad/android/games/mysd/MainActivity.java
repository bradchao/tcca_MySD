package tw.brad.android.games.mysd;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private boolean isPermissionOK;
    private File sdroot, approot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            // no
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
        }else {
            isPermissionOK = true;
            init();
        }

    }

    private void init(){
        if (!isPermissionOK) {
            finish();
        }else{
            go();
        }
    }

    private void go(){
        sdroot = Environment.getExternalStorageDirectory();
        approot = new File(sdroot, "Android/data/" + getPackageName() + "/");
        if (!approot.exists()){
            approot.mkdirs();
        }

    }

    public void test1(View view){
        File file1 = new File(sdroot, "file1");
        File file2 = new File(approot, "file2");
        try {
            FileOutputStream fout1 = new FileOutputStream(file1);
            FileOutputStream fout2 = new FileOutputStream(file2);

            fout1.write("I am File1".getBytes());
            fout2.write("I am File2".getBytes());

            fout1.flush();fout2.flush();
            fout1.close();fout2.close();
            Toast.makeText(this,"OK", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show();
        }


    }
    public void test2(View view){
        File file1 = new File(sdroot, "file1");
        File file2 = new File(approot, "file2");

        try {
            FileInputStream fin1 = new FileInputStream(file1);
            FileInputStream fin2 = new FileInputStream(file2);

            int temp1; StringBuffer sb = new StringBuffer();
            while ( (temp1 = fin1.read()) != -1){
                sb.append((char)temp1);
            }

            fin1.close();
            fin2.close();

            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                isPermissionOK = true;

            }
            init();
        }
    }
}
