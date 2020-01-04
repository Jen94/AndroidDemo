package com.my.demo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.my.demo.R;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);

        initWebView();
        Button btn = findViewById(R.id.buttonOpenVideoUrl);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView view = findViewById(R.id.webView);
                EditText et = findViewById(R.id.editTextWebUrl);
                String url = et.getText().toString();
                view.loadUrl(url);
            }
        });
    }

    public void initWebView() {
        WebView view = findViewById(R.id.webView);
        view.setWebViewClient(new WebViewClient());
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }
}
