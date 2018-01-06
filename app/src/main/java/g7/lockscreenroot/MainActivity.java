package g7.lockscreenroot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.io.DataOutputStream;

public class MainActivity extends Activity
{
    final String a = getClass().getSimpleName();

    private void lock()
    {
        try
        {
            Process localProcess = Runtime.getRuntime().exec("su");
            DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
            localDataOutputStream.writeBytes("input keyevent 26\n");
            localDataOutputStream.flush();
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();
            return;
        }
        catch (Exception localException)
        {
            Log.e(this.a, localException.getLocalizedMessage());
        }
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        new Thread()
        {
            public void run()
            {
                lock();
            }
        }.start();
        finish();
    }
}