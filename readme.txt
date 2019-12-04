Android 自定义相机，实现了拍照，录制视频功能！

使用简单 仅仅6行代码即可完成复杂的逻辑

	mCameraController = CameraController.getmInstance(this);
        mCameraController.setFolderPath("/sdcard/iScopePro");
        mTextureview = new AutoFitTextureView(this);
        mTextureview.setLayoutParams(params);
        mCameraController.InitCamera(mTextureview);


API For CameraController
设置存储路劲
 setFolderPath("/sdcard/iScopePro");         

初始化
 InitCamera(mTextureview);

拍照 默认会在路劲下面创建一个image文件夹 照片存储在这里
  takePicture();


录像
  startRecordingVideo();

停止录像
  stopRecordingVideo()