package com.panda.cvsandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity2 extends AppCompatActivity
{
    @BindView(R.id.wep_view)
    WebView wb;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tst2);
        ButterKnife.bind(this);
        initWb();
    }
    private void initWb()
    {
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setSupportZoom(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.setWebViewClient(yourWebClient);
        wb.loadUrl("http://el-abda3.online/projects/apps/job-order/employees?skill_id=1&job_id=4&country_id=2&nationality_id=2&job_type=3");
        wb.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");

    }
    WebViewClient yourWebClient = new WebViewClient()
       {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            wb.loadUrl("javascript:HtmlViewer.showHTML" +
                    "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
        }
        };

}
