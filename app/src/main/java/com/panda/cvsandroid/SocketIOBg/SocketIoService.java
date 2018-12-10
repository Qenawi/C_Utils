package com.panda.cvsandroid.SocketIOBg;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.panda.cvsandroid.Cservice.C_NotificationHandeler;

import java.net.URISyntaxException;

import io.reactivex.disposables.CompositeDisposable;

public class SocketIoService extends JobService
{
    CompositeDisposable compositeDisposable;
    C_NotificationHandeler c_notificationHandeler=new C_NotificationHandeler(this);
    @Override
    public boolean onStartJob(JobParameters jobParameters)
    {
        compositeDisposable=new CompositeDisposable();
        mSocket.on("new message", new Emitter.Listener()
        {
            @Override
            public void call(Object... args)
            {
                  /*
               JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }
             */
                c_notificationHandeler.ShowNotifaction("NEW MSG HerE");
            }
        });
        mSocket.connect();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://socket-io-chat.now.sh/");
        } catch (URISyntaxException e) {}
    }

}
