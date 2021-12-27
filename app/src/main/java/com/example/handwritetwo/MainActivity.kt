package com.example.handwritetwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

const val TAG = "sisterofdc"

class MainActivity : AppCompatActivity() {
    lateinit var interpreter: Interpreter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            interpreter = Interpreter(loadModelFile(),null)
        }catch (e: IOException){
            Log.d(TAG,"$e")
        }

    }


    private fun loadModelFile(): MappedByteBuffer {
        val assetsProvider = assets
        val assetFileDescriptor = assetsProvider.openFd("")
        val fileInputStream: FileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel: FileChannel = fileInputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,assetFileDescriptor.startOffset,assetFileDescriptor.length)
    }

}