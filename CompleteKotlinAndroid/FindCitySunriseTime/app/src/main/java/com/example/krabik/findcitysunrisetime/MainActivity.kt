package com.example.krabik.findcitysunrisetime

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    protected fun GetSunrise(view:View){

        var city = etCityName.text.toString()
        val url= "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22kazan%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
        //val url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city +"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
        MyAsyncTask(this).execute(url)
    }

    inner class MyAsyncTask: AsyncTask<String, String, String>{

        var context:Context? = null
        constructor(context: Context):super(){
            this.context = context
        }
        override fun onPreExecute() {

        }

        override fun doInBackground(vararg p0: String?): String {
            try{



                val url=URL(p0[0])

                val urlConnect=url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout=3000


                var inString= ConvertStreamToString(urlConnect.inputStream)
                //Cannot access to ui
                publishProgress(inString)
            }catch(ex:Exception){}


            return " "

        }

        override fun onProgressUpdate(vararg values: String?) {
            try{
                var json = JSONObject(values[0])
                var query = json.getJSONObject("query")
                var result = query.getJSONObject("result")
                var channel = result.getJSONObject("channel")
                var astronomy = channel.getJSONObject("astronomy")
                var sunrise = astronomy.getString("sunrise")
                tvSunrise.text = "Sunrise time is " + sunrise


            }catch (ex:Exception){}
        }

        override fun onPostExecute(result: String?) {


        }


    }


    fun ConvertStreamToString(inputStream:InputStream):String{

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line:String
        var AllStrings:String = ""

        try{
            do{
                line = bufferReader.readLine()
                if(line != null){
                    AllStrings += line
                }
            }while (line != null)
            inputStream.close()
        }catch (ex:Exception){}



        return AllStrings
    }


}
