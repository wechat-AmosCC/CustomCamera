Android �Զ��������ʵ�������գ�¼����Ƶ���ܣ�

ʹ�ü� ����6�д��뼴����ɸ��ӵ��߼�

	mCameraController = CameraController.getmInstance(this);
        mCameraController.setFolderPath("/sdcard/iScopePro");
        mTextureview = new AutoFitTextureView(this);
        mTextureview.setLayoutParams(params);
        mCameraController.InitCamera(mTextureview);


API For CameraController
���ô洢·��
 setFolderPath("/sdcard/iScopePro");         

��ʼ��
 InitCamera(mTextureview);

���� Ĭ�ϻ���·�����洴��һ��image�ļ��� ��Ƭ�洢������
  takePicture();


¼��
  startRecordingVideo();

ֹͣ¼��
  stopRecordingVideo()