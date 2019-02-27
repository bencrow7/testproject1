package com.project.ttec.testproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.api.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class multipartTest extends AppCompatActivity {


    private ImageView imgview_pic1;
    final int PICK_FROM_GALLERY = 100;
    final int PICK_FROM_CAMERA = 200;
    final int CROP_FROM_iMAGE = 300;
    Uri mCurrentPhotoPath;
    String imageFilePath;
    Bitmap selectedImageBitmap1;
    int imagetype=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiparttest);



        imgview_pic1 = (ImageView)findViewById(R.id.imgview_pic1);
        Button btn = (Button)findViewById(R.id.btn_multipart_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraGallary();
            }
        });


        Button uploadbtn = (Button)findViewById(R.id.btn_multipart_upload_btn);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new UploadAsync(multipartTest.this, paramMap, filesMap);
                new Thread() {
                    public void run() {
                        boolean useCSRF = true;
                        try {
                            MultipartLargeUtility multipart = new MultipartLargeUtility("http://www.luckiss.kr/contents/app/board_app_act.php", "UTF-8",useCSRF);
                            multipart.addFormField("page","submit");
                            multipart.addFormField("flag","af");
                            multipart.addFormField("email","ttec5333@gmail.com");
                            multipart.addFormField("subject","test write");
                            multipart.addFormField("content","test write test write test write test write");
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                                if(imagetype == 1) {
                                    //사진찍었을때..
                                    multipart.addFilePart("File_Nm1",new File(mCurrentPhotoPath.getPath()));
                                } else if(imagetype == 2) {
                                    //갤러리였을때..
                                    multipart.addFilePart("File_Nm1",new File(getPath(mCurrentPhotoPath)));
                                }
                            } else {
                                File f_image = null;
                                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                                File[] files = storageDir.listFiles();

                                if(files != null) {
                                    for (int i=0; i<files.length ; i++) {
                                        String tempstr = files[i].getAbsolutePath();
                                        if(tempstr.contains("luckiss_sns_pic")) {
                                            System.out.println("filename ====" + files[i].getName());
                                            f_image = files[i];
                                            break;
                                        }

                                    }
                                }
                                if(f_image == null) {
                                    if(mCurrentPhotoPath != null) {
                                        f_image = new File(getPath(mCurrentPhotoPath));
                                    }
                                }
                                multipart.addFilePart("File_Nm1", f_image);
                            }

                            List<String> response = multipart.finish();
                            Log.w("multipart","SERVER REPLIED:");
                            for(String line : response) {
                                Log.w("multipart", "Upload Files Response:::" + line);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

        Button upload_user_info_btn = (Button)findViewById(R.id.btn_multipart_info_btn);
        upload_user_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new UploadAsync(multipartTest.this, paramMap, filesMap);
                new Thread() {
                    public void run() {
                        boolean useCSRF = true;
                        try {
                            MultipartLargeUtility multipart = new MultipartLargeUtility("http://www.luckiss.kr/contents/member/register_act.php", "UTF-8",useCSRF);
                            multipart.addFormField("page","update");
                            multipart.addFormField("email","ttec5333@gmail.com");
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                                if(imagetype == 1) {
                                    //사진찍었을때..
                                    multipart.addFilePart("File_Nm1",new File(mCurrentPhotoPath.getPath()));
                                } else if(imagetype == 2) {
                                    //갤러리였을때..
                                    multipart.addFilePart("File_Nm1",new File(getPath(mCurrentPhotoPath)));
                                }
                            } else {
                                File f_image = null;
                                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                                File[] files = storageDir.listFiles();

                                if(files != null) {
                                    for (int i=0; i<files.length ; i++) {
                                        String tempstr = files[i].getAbsolutePath();
                                        if(tempstr.contains("luckiss_sns_pic")) {
                                            System.out.println("filename ====" + files[i].getName());
                                            f_image = files[i];
                                            break;
                                        }

                                    }
                                }
                                if(f_image == null) {
                                    if(mCurrentPhotoPath != null) {
                                        f_image = new File(getPath(mCurrentPhotoPath));
                                    }
                                }
                                multipart.addFilePart("File_Nm1", f_image);
                            }
                            List<String> response = multipart.finish();
                            Log.w("multipart","SERVER REPLIED:");
                            for(String line : response) {
                                Log.w("multipart", "Upload Files Response:::" + line);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

    }

//    class UploadAsync extends AsyncTask<Object, Integer, JSONObject> implements ProgressListener {
//
//        private ProgressDialog progressDialog;
//        private Context mContext;
//        private HashMap<String, String> param;
//        private HashMap<String, String> files;
//
//        public UploadAsync(Context context, HashMap<String, String> param, HashMap<String, String> files) {
//            mContext = context;
//            this.param = param;
//            this.files = files;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(mContext);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            progressDialog.setMessage("wating...");
//            progressDialog.setMax(100);
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        }
//
//        @Override
//        protected JSONObject doInBackground(Object... params) {
//            JSONObject json = null;
//            try {
//
//                String url = "http://www.luckiss.kr/contents/app/board_app_act.php";
//                MultipartUpload multipartUpload = new MultipartUpload(url, "UTF-8");
//                multipartUpload.setProgressListener(this);
//                json = multipartUpload.upload(param, files);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return json;
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            if (progressDialog != null && progressDialog.isShowing()) {
//                if (values[1] == 1) {
//                    progressDialog.setProgress(values[0]);
//                } else {
//                    progressDialog.setProgress(values[0]);
//                }
//            }
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject result) {
//            super.onPostExecute(result);
//            if (progressDialog.isShowing()) {
//                progressDialog.dismiss();
//            }
//
//            if (result != null) {
//                try {
//                    if (result.getInt("success") == 1) {
//                        Toast.makeText(mContext, "전송 성공", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(mContext, "전송 에러", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onProgressUpdate(int progress) {
//            publishProgress(progress, 0);
//        }
//    }










    private void openCameraGallary() {
        // 이미지 선택 다이얼로그
        final CharSequence[] items = {"카메라", "갤러리"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선택");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        openCamera();
                        break;
                    case 1:
                        openPhotoLibrary();
                        break;
                }
            }
        });
        builder.setPositiveButton("취소", null);
        builder.show();
    }

    private void openCamera() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mCurrentPhotoPath = createImageFile_uri();  //촬영한 이미지를 저장할 path 생성
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoPath);

            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
            }
        } else {
            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (pictureIntent.resolveActivity(getPackageManager()) != null) {

                File photoFile = null;
                try {
                    photoFile = createImageFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                if (photoFile != null) {
                    mCurrentPhotoPath = FileProvider.getUriForFile(this,
                            "com.project.ttec.testproject.fileprovider",
                            photoFile);
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoPath);
                    startActivityForResult(pictureIntent, PICK_FROM_CAMERA);
                }
            }
        }

    }

    private void openPhotoLibrary() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_FROM_GALLERY);

    }

    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String timeStamp = "luckiss_sns_pic_0_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Constants.IMAGE_FILE_FOLDER;
        File image = File.createTempFile(
                timeStamp,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private Uri createImageFile_uri(){
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String timeStamp = "luckiss_sns_pic_0_";
        String imageFileName = timeStamp + ".jpg";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        Uri uri = Uri.fromFile(new File(storageDir, imageFileName));
        return uri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {

            if(requestCode == PICK_FROM_CAMERA) {
                imagetype = 1;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    String photoPath = mCurrentPhotoPath.getPath();
                    //이미지를 불러올때 고용량의 경우 OutOfMemory가 발생할 수 있으므로
                    //이미지 크기를 줄여서 호출함
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap imageBitmap = BitmapFactory.decodeFile(photoPath, options);

                    try{
                        // 기본 카메라 모듈을 이용해 촬영할 경우 가끔씩 이미지가
                        // 회전되어 출력되는 경우가 존재하여
                        // 이미지를 상황에 맞게 회전시킨다
                        ExifInterface exif = new ExifInterface(photoPath);
                        int exifOrientation = exif.getAttributeInt(
                                ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        int exifDegree = exifOrientationToDegrees(exifOrientation);

                        //회전된 이미지를 다시 회전시켜 정상 출력
                        imageBitmap = rotate(imageBitmap, exifDegree);

                        //회전시킨 이미지를 저장
                        saveExifFile(imageBitmap, photoPath);

                        //비트맵 메모리 반환
                        imageBitmap.recycle();
                    }catch (IOException e){
                        e.getStackTrace();
                    }

                    //이미지 편집 호출
                    cropImage();
                } else {
                    Bitmap photoBitmap = BitmapFactory.decodeFile(imageFilePath);
                    try{
                        photoBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mCurrentPhotoPath);

                        ExifInterface exif = new ExifInterface(imageFilePath);
                        int exifOrientation = exif.getAttributeInt(
                                ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        int exifDegree = exifOrientationToDegrees(exifOrientation);

                        photoBitmap = rotate(photoBitmap, exifDegree);

                        saveExifFile(photoBitmap, imageFilePath);

                        photoBitmap.recycle();
                    }catch (IOException e){
                        e.getStackTrace();
                    }

//                    filesMap.put("File_Nm1", imageFilePath);

                    Glide.with(this).load(imageFilePath)
                            .apply(new RequestOptions()
                                    .fitCenter()
                                    .override(1200, 1800)
                            )
                            .into(imgview_pic1);


                }


            } else if(requestCode == CROP_FROM_iMAGE) {
                //편집된 이미지의 경로 취득
                String cropImagePath = mCurrentPhotoPath.getPath();

                //이미지 정보 취득
                setImageInfo(mCurrentPhotoPath);


//                imageView.setImageBitmap(photo);

                    selectedImageBitmap1 = BitmapFactory.decodeFile(cropImagePath);
                    imgview_pic1.setImageBitmap(selectedImageBitmap1);



            } else {
                imagetype = 2;

                mCurrentPhotoPath = data.getData();
                    try {
                        selectedImageBitmap1= MediaStore.Images.Media.getBitmap(getContentResolver(), mCurrentPhotoPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Glide.with(this).load(mCurrentPhotoPath)
                            .apply(new RequestOptions()
                                    .fitCenter()
                                    .override(1200, 1800)
                            )
                            .into(imgview_pic1);
//                filesMap.put("File_Nm1", selectedImage1.getPath().toString());

            }



        }
    }

    public int exifOrientationToDegrees(int exifOrientation)
    {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }
        return 0;
    }


    public Bitmap rotate(Bitmap bitmap, int degrees){
        Bitmap retBitmap = bitmap;

        if(degrees != 0 && bitmap != null){
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

            try {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted) {
                    retBitmap = converted;
                    bitmap.recycle();
                    bitmap = null;
                }
            }
            catch(OutOfMemoryError ex) {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return retBitmap;
    }


    public void saveExifFile(Bitmap imageBitmap, String savePath){
        FileOutputStream fos = null;
        File saveFile = null;

        try{
            saveFile = new File(savePath);
            fos = new FileOutputStream(saveFile);
            //원본형태를 유지해서 이미지 저장
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

        }catch(IOException e){
            //("IOException", e.getMessage());
        }finally {
            try {
                if(fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
            }
        }
    }


    private void cropImage(){
        Intent cropPictureIntent = new Intent("com.android.camera.action.CROP");
        cropPictureIntent.setDataAndType(mCurrentPhotoPath, "image/*");

        // Crop한 이미지를 저장할 Path
        cropPictureIntent.putExtra("output", mCurrentPhotoPath);

        //이미지 편집 크기 제한
        //crop box X and Y rate
        cropPictureIntent.putExtra("aspectX", 1);
        cropPictureIntent.putExtra("aspectY", 1);
        //indicate output X and Y
        cropPictureIntent.putExtra("outputX", 1200);
        cropPictureIntent.putExtra("outputY", 1200);

        // Return Data를 사용하면 번들 용량 제한으로 크기가 큰 이미지는 넘겨 줄 수 없다.
        startActivityForResult(cropPictureIntent, CROP_FROM_iMAGE);
    }

    private void setImageInfo(Uri getIamgePath){
        try {
            ExifInterface exif = new ExifInterface(getIamgePath.getPath());

            String imageSize = exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) + " x " + exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);

            File file = new File(mCurrentPhotoPath.getPath());
            String imageLength = String.valueOf(file.length()) + " Bytes";


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }










}
