package com.example.cwh.mypermission.downloadTask;

/**
 * Created by hui on 2019/3/23.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
