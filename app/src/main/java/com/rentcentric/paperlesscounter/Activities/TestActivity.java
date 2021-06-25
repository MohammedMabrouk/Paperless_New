package com.rentcentric.paperlesscounter.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.rentcentric.paperlesscounter.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import im.delight.android.webview.AdvancedWebView;

public class TestActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    AdvancedWebView wvMain;
    com.github.barteksc.pdfviewer.PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        pdfView = findViewById(R.id.pdfView);

        wvMain = findViewById(R.id.Main_WV);
//        wvMain.getSettings().setSupportZoom(true);
//        wvMain.getSettings().setBuiltInZoomControls(true);
//        wvMain.getSettings().setLoadWithOverviewMode(true);
//        wvMain.getSettings().setUseWideViewPort(true);
//        wvMain.getSettings().setAllowFileAccess(true);
//        wvMain.getSettings().setJavaScriptEnabled(true);
//        wvMain.getSettings().setAppCacheEnabled(true);

        wvMain.setListener(this, this);
        wvMain.setMixedContentAllowed(false);

        wvMain.loadUrl("https://www6.rentcentric.com/Client7178/agreements/PDFDynamicStandardAgreement.aspx?AgreementFormID=385&AgreementID=6897");
//

//                wvMain.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                showToast(error.getDescription().toString());
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
////                pbMain.setVisibility(View.INVISIBLE);
//                Log.v("", "");
//            }
//
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//                String url = "https://www6.rentcentric.com/Client7178/agreements/PDFDynamicStandardAgreement.aspx?AgreementFormID=385&AgreementID=6897";
//        try {
//            url = URLEncoder.encode("file url", "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
////        wvMain.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=http://www.africau.edu/images/default/sample.pdf");
//        wvMain.loadUrl("http://docs.google.com/gview?embedded=true&url="+url);
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        wvMain.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        wvMain.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        wvMain.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        wvMain.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!wvMain.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        Log.v("","");
    }

    @Override
    public void onPageFinished(String url) {
        Log.v("","");
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Log.v("","");
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        Log.v("","");
        new DownloadFileFromURL().execute(url);
    }

    @Override
    public void onExternalPageRequest(String url) {
        Log.v("","");
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");

        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            String filePath = "";
            try {
                String timeStamp = String.valueOf(System.currentTimeMillis());
                String imageFileName = timeStamp + "_contract";
                File storageDir = TestActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File pdfFile = File.createTempFile(
                        imageFileName,  /* prefix */
                        ".pdf",         /* suffix */
                        storageDir      /* directory */
                );

                filePath = pdfFile.getPath();

//                String root = Environment.getExternalStorageDirectory().toString();

                System.out.println("Downloading");
                URL url = new URL(f_url[0]);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(pdfFile);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return filePath;
        }



        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_path) {
            System.out.println("Downloaded");
            pdfView.fromFile(new File(file_path))
                    .enableSwipe(true)
                    .enableAnnotationRendering(true)
                    .swipeHorizontal(false)
                    .load();
        }

    }

}