package com.example.valueconvertor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.valueconvertor.R
import com.example.valueconvertor.data.Valute
import org.json.JSONException
import java.util.*

class Adapter(
    @get:JvmName("getAdapterContext")
    var context: Context,
    var listLayout: Int,
    var valuteList: ArrayList<Map<String, Valute>>
) :
    ArrayAdapter<Map<String, Valute>>(
        context, listLayout,
        valuteList as ArrayList<Map<String, Valute>>
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listViewItem = inflater.inflate(listLayout, null, false)

        val charCode = listViewItem.findViewById<TextView>(R.id.char_code)
        val nominal = listViewItem.findViewById<TextView>(R.id.nominal)
        val name = listViewItem.findViewById<TextView>(R.id.name)
        val value = listViewItem.findViewById<TextView>(R.id.value)

        try {
            charCode.text = valuteList.get(position).values.elementAt(position).charCode.toString()
            nominal.text = valuteList.get(position).values.elementAt(position).nominal.toString()
            name.text = valuteList.get(position).values.elementAt(position).name.toString()
            value.text = valuteList.get(position).values.elementAt(position).value.toString()

//            nominal.text = valuteList.get(position).values.elementAt(position).toString()
//            name.text = valuteList.get(position).values.elementAt(position).name.toString()
//            value.text = valuteList.get(position).values.elementAt(position).toString()


        } catch (je: JSONException) {
            je.printStackTrace()
        }
        return listViewItem
    }



}


