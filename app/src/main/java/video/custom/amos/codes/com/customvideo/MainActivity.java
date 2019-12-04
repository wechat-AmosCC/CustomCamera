package video.custom.amos.codes.com.customvideo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_RecStart, btnPhoto, btnRecStop;
    private AutoFitTextureView mTextureview;
    private CameraController mCameraController;
    private RelativeLayout relativeVideo;

    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }
        return true;
    }

    public void onByPressed() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                /**
                 * 这里权限模式
                 */
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    // 解释为什么需要定位权限之类的
                } else {
                    // 请求权限处理
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_main);
        isGrantExternalRW(this);
        onByPressed();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //默认打开UVC
                initTextureViewSurface();
            }
        }, 1000);//1秒后执行Runnable中的run方法


        btnPhoto = (Button) findViewById(R.id.btn_Photo);
        btnPhoto.setOnClickListener(this);
        btn_RecStart = findViewById(R.id.btn_Rec);
        btn_RecStart.setOnClickListener(this);
        btnRecStop = findViewById(R.id.btn_rotation);
        btnRecStop.setOnClickListener(this);
    }

    public void initTextureViewSurface() {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        int Screen_W = outMetrics.widthPixels;
        int Screen_H = outMetrics.heightPixels;

        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        relativeVideo = (RelativeLayout) findViewById(R.id.surface_layout);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        float topY = Screen_W * 1.25f;
        int ph = (int) topY;
        params.height = ph;
        params.setMargins(0, 0, 0, 0);
        params.width = Screen_W;
        relativeVideo.setLayoutParams(params);


        mCameraController = CameraController.getmInstance(this);
        mCameraController.setFolderPath("/sdcard/iScopePro");
        mTextureview = new AutoFitTextureView(this);
        mTextureview.setLayoutParams(params);
        relativeVideo.addView(mTextureview);
        mCameraController.InitCamera(mTextureview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_Photo:
                mCameraController.takePicture();
                break;
            case R.id.btn_Rec:
                mCameraController.startRecordingVideo();
                Toast.makeText(this, "录像开始", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_rotation:
                mCameraController.stopRecordingVideo();
                Toast.makeText(this, "停止录像..", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
