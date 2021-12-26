package com.rizkyhamdana.yorental.detail

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rizkyhamdana.yorental.MainViewModel
import com.rizkyhamdana.yorental.data.DataResponse
import com.rizkyhamdana.yorental.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: MainViewModel
    private var biaya : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val data = intent.getParcelableExtra<DataResponse>(EXTRA_DATA) as DataResponse

        setLayout(data)

        binding.btnCek.setOnClickListener {
            if (binding.etWaktuSewa.text == null){
                Toast.makeText(this, "Masukkan lama waktu sewa terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else{
                val biayaDasar = viewModel.hitungBiaya(biaya, binding.etWaktuSewa.text.toString().toInt())
                showDialogBox(biayaDasar)
            }
        }

    }

    private fun showDialogBox(biayaDasar: Int) {
        val builder: AlertDialog.Builder = let {
            AlertDialog.Builder(it)
        }
        builder.apply {
            setMessage("Rp. $biayaDasar")
            setTitle("Total Biaya Dasar")
            setPositiveButton("Sewa",
                DialogInterface.OnClickListener { _, _ ->
                    // User clicked OK button
                    Toast.makeText(context, "Mulai sewa", Toast.LENGTH_SHORT).show()

                })
            setNegativeButton("Batal",
                DialogInterface.OnClickListener { _, _ ->
                    // User cancelled the dialog
                    Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
                })
        }
        builder.create()
        builder.show()
    }


    private fun setLayout(data: DataResponse) {
        biaya = data.biaya
        with(binding){
            tvNamaKendaraan.text = data.nama
            tvJenisKendaraan.text = data.jenis
            tvDescKendaraan.text = data.desc
            Glide.with(this@DetailsActivity)
                .load(data.gambar)
                .into(imgKendaraan)
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}