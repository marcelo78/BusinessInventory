package com.ma.businessinventory.ui.export

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entities.ProductEntity
import kotlinx.android.synthetic.main.fragment_export.*


/**
 *
 */
class ExportFragment : Fragment(), IExport.View {

    companion object {
        private val TAG = ExportFragment::class.java.simpleName
    }

    private lateinit var presenter: IExport.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ExportPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_export, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                activity!!.applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                activity!!, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
            )

            return
        }


        btnExportExl.setOnClickListener {
            Log.d(TAG, "click button")

            presenter.getItems(activity!!)


//            WordBook

        }
    }

    override fun showResult(items: MutableList<ProductEntity>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
