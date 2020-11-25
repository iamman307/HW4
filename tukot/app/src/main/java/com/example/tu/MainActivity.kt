package com.example.tu

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity()
{
    private var rabprogress = 0
    private var turprogress = 0

    lateinit var btn_start:Button
    lateinit var seekBar:SeekBar
    lateinit var seekBar2:SeekBar

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener()
        {
            btn_start.isEnabled = false
            rabprogress = 0
            turprogress = 0
            seekBar.progress = 0
            seekBar2.progress = 0
            runThread()
            CoroutineScope(Dispatchers.Default).launch()
            {
                runAsyncTask()
            }
        }
    }

    private fun runThread()
    {
        GlobalScope.launch(Dispatchers.Main)
        {
            while (rabprogress <= 100 && turprogress <= 100)
            {
                try
                {
                    delay(100)
                    rabprogress += (Math.random() * 3).toInt()
                    val msg = Message()
                    msg.what = 1
                    mHandler.sendMessage(msg)
                }
                catch (e: InterruptedException)
                {
                    e.printStackTrace()
                }
            }
        }
    }

    private val mHandler = Handler (Handler.Callback
    { msg ->
        when (msg.what)
        {
            1 -> seekBar.progress = rabprogress
        }
        if (rabprogress >= 100 && turprogress < 100)
        {
            Toast.makeText(this@MainActivity, "兔子勝利",
                Toast.LENGTH_SHORT).show()
            btn_start.isEnabled = true
        }
        false
    })
    private suspend fun runAsyncTask()
    {
        try
        {
            while (turprogress < 100 && rabprogress < 100)
            {
                try
                {
                    delay(100)
                    turprogress += (Math.random() * 3).toInt()
                    withContext(Dispatchers.Main)
                    {
                        turprogress?.let {
                            seekBar2.progress = it
                        }
                    }
                }
                catch (e: InterruptedException)
                {
                    e.printStackTrace()
                }
            }

            withContext(Dispatchers.Main)
            {
                if (turprogress >= 100 && rabprogress < 100)
                {
                    Toast.makeText(this@MainActivity, "烏龜勝利", Toast.LENGTH_SHORT).show()
                    btn_start.isEnabled = true
                }
            }
        }
        catch (e :Exception)
        {}
    }
}