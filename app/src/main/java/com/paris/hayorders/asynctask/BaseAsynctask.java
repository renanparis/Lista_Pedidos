package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

public abstract class BaseAsynctask extends AsyncTask<Void, Void, Void> {

    private EndsListener listener;

    public BaseAsynctask(EndsListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.WhenItEnds();
    }

    public interface EndsListener {

        void WhenItEnds();

    }
}


