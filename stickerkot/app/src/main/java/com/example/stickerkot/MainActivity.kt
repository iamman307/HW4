package com.example.stickerkot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.stickerkot.R


class MainActivity : AppCompatActivity() {
    inner class Data {
        var photo = 0
        var name: String? = null
    }

    inner class MyAdapter(private val data: Array<Data?>,
                          private val view: Int) : BaseAdapter() {
        override fun getCount(): Int { return data.size }

        override fun getItem(position: Int): Data? { return data[position] }

        override fun getItemId(position: Int): Long { return 0 }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
        {
            var convertView = convertView
            convertView = LayoutInflater.from(parent!!.context).inflate(view,parent,false)
            val name = convertView.findViewById<TextView>(R.id.name)
            name.text = data[position]!!.name
            val imageView = convertView.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(data[position]!!.photo)
            return convertView
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士")
        val transPhotoIdArray = intArrayOf(R.drawable.trans1, R.drawable.trans2, R.drawable.trans3, R.drawable.trans4)
        val transData = arrayOfNulls<Data>(transNameArray.size)
        for (i in transData.indices) {
            transData[i] = Data()
            transData[i]!!.name = transNameArray[i]
            transData[i]!!.photo = transPhotoIdArray[i]
        }
        val transAdapter: MyAdapter = MyAdapter(transData, R.layout.trans_list)
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = transAdapter

        val cubeeNameArray = arrayOf("哭哭", "發抖", "再見",
            "生氣", "昏倒", "竊笑",
            "很棒", "你好", "驚嚇", "大笑")
        val cubeePhotoIdArray = intArrayOf(R.drawable.cubee1, R.drawable.cubee2, R.drawable.cubee3,
            R.drawable.cubee4, R.drawable.cubee5, R.drawable.cubee6,
            R.drawable.cubee7, R.drawable.cubee8, R.drawable.cubee9, R.drawable.cubee10)
        val cubeeData = arrayOfNulls<Data>(cubeeNameArray.size)
        for (i in cubeeData.indices) {
            cubeeData[i] = Data()
            cubeeData[i]!!.name = cubeeNameArray[i]
            cubeeData[i]!!.photo = cubeePhotoIdArray[i]
        }
        val cubeeAdapter: MyAdapter = MyAdapter(cubeeData, R.layout.cubee_list)
        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = cubeeAdapter
        gridView.numColumns = 3

        val messageArray = arrayOf("訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6")
        val messageAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, messageArray)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = messageAdapter
    }
}