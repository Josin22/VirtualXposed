package io.virtualapp;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.stub.VASettings;

import io.virtualapp.delegate.MyVirtualInitializer;

/**
 * @author Lody
 */
public class XApp extends Application {

    private static final String TAG = "XApp";

    public static final String XPOSED_INSTALLER_PACKAGE = "de.robv.android.xposed.installer";
    public static final String SELF_XPOSED_INSTALLER_PACKAGE = "de.robv.android.xposed.installer.self";
    public static final String ALIPAY_INSTALLER_PACKAGE = "com.eg.android.AlipayGphone";
    public static final String WECHAT_INSTALLER_PACKAGE = "com.tencent.mm";
    public static final String UNIONPAY_INSTALLER_PACKAGE = "com.unionpay";
    public static final String YTJ_INSTALLER_PACKAGE = "com.dorapradoshell.hookpro";
    public static final String TEST_YTJ_INSTALLER_PACKAGE = "com.dorapradoshell.dyhook.devpro";

    private static XApp gApp;

    public static XApp getApp() {
        return gApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        gApp = this;
        super.attachBaseContext(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NativeEngine.disableJit(Build.VERSION.SDK_INT);
        }
        VASettings.ENABLE_IO_REDIRECT = true;
        VASettings.ENABLE_INNER_SHORTCUT = false;
        try {
            VirtualCore.get().startup(base);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        VirtualCore virtualCore = VirtualCore.get();
        virtualCore.initialize(new MyVirtualInitializer(this, virtualCore));
    }

}
