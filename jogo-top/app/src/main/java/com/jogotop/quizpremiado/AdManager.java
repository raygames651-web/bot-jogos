package com.jogotop.quizpremiado;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.content.Context;

public class AdManager {

    private Context context;
    private WebView webViewTop, webViewBottom;
    private String adUrl = "https://www.effectivecpmnetwork.com/xgjdtyf55?key=977d1897c90350f839eb241632489eab";

    public AdManager(Context ctx) {
        this.context = ctx;
    }

    public void loadBannerAd(LinearLayout container) {
        webViewTop = createWebView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            50
        );
        webViewTop.setLayoutParams(params);
        container.addView(webViewTop);
    }

    private WebView createWebView() {
        WebView webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        webView.loadUrl(adUrl);
        return webView;
    }

    public void loadAdTop(LinearLayout containerTop) {
        webViewTop = createWebView();
        containerTop.addView(webViewTop);
    }

    public void loadAdBottom(LinearLayout containerBottom) {
        webViewBottom = createWebView();
        containerBottom.addView(webViewBottom);
    }

    public void showInterstitial() {
        WebView interstitial = createWebView();
        interstitial.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        ));
    }

    public void destroy() {
        if (webViewTop != null) {
            webViewTop.destroy();
        }
        if (webViewBottom != null) {
            webViewBottom.destroy();
        }
    }
}