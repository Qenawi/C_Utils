package com.panda.cvsandroid.WorkManger;

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.panda.cvsandroid.Cservice.CService
import com.panda.cvsandroid.network.models.MovieResponse
import com.panda.cvsandroid.utils.Contstants
import java.util.*

class CompressWorker(Context: Context, params: WorkerParameters)
    : Worker(Context, params)
{
    override fun doWork(): Result
    {

        // Do the work here--in this case, compress the stored images.
        // In this example no parameters are passed; the task is
        // assumed to be "compress the whole library."
        myCompress()
        // Indicate success or failure with your return value:

        // (Returning RETRY tells WorkManager to try this task again
        // later; FAILURE says not to try again.)
        return Result.SUCCESS
    }

    private fun myCompress() {
        val cService = CService(applicationContext)
        val Headers = HashMap<String, String>()
        val Params = HashMap<String, String>()
        Params["api_key"] = Contstants.TMDP_API_KEY
        val Url = Contstants.Movies_BASE_URL + Contstants.PopularURL
        cService.FetchData(MovieResponse(), Headers, Url, Params, object : CService.CsCallBack {

            override fun <T> Sucess(Resposne: T)
            {
                val e = Resposne as MovieResponse
                Log.v("Qean", e.totalResults.toString())
                Result.SUCCESS

            }

            override fun Faild(t: Throwable?) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.v("Qean", "Faild")
                Result.FAILURE
            }
        })
    }
}
/***
 * Worker: This is where you put the code
 * for the actual work you want to perform in
 * the background. You'll extend this class and
 * override the doWork() method.
 *
 * WorkRequest: This represents a request to do some work.
 * You'll pass in your Worker as part of creating your WorkRequest.
 * When making the WorkRequest you can also specify
 * things like Constraints on when the Worker should run.
 *
 *
 * WorkManager: This class actually schedules your WorkRequest and makes it run.
 * It schedules WorkRequests in a way that spreads out the load on system
 * resources, while honoring the constraints you specify.
 *
 **/