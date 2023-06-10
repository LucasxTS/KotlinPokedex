package com.example.pokedex


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.viewpager2.widget.ViewPager2
import com.android.car.ui.toolbar.TabLayout


class SecondActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private var bundle = Bundle()

    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        tabLayout = findViewById(R.id.tabLayout)
//        viewPager = findViewById(R.id.view_pager)



        //NAO SEI PQ TA SOZINHO POREM TROCANDO O NOME DO POKEMON
        title_name.text = intent.getStringExtra("PokemonName")



        changingImage()
        changingColors()
        getData()
    }

    private fun changingImage() {
        val id = intent.getStringExtra("PokeNumber")
        // COLOCANDO IMAGEM COM O GLIDE
        val requestOptions = RequestOptions().override(1238, 1130)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        if (id != null) {
            Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${
                        id.toString().padStart(3, '0')
                    }.png"
                )
                .into(poke_photo)
        }
    }

    //RETROFIT INFO
    private fun getData() {

        val id = intent.getStringExtra("PokeNumber")
        val retrofitClient = RetrofitService.getRetrofitInstance("https://pokeapi.co/api/v2/")

        val endpoint = retrofitClient.create(RetrofitInterface::class.java)
        val callback = endpoint.getNumbers(id)


        callback.enqueue(object : Callback<DetailModel> {

            override fun onResponse(
                call: Call<DetailModel>,
                response: Response<DetailModel>
            ) { response.body()
                val numbers = response.body()
                if (numbers != null) {
                    hpProgressBar.progress =  numbers.stats[0].base_stat
                    AttackProgressBar.progress =  numbers.stats[1].base_stat
                    DefenceProgressBar.progress =  numbers.stats[2].base_stat
                    SpAttackProgressBar.progress =  numbers.stats[3].base_stat
                    SpDefenceProgressBar.progress =  numbers.stats[4].base_stat
                    SpeedProgressBar.progress =  numbers.stats[5].base_stat
//                    bundle.putInt("HpBar", numbers.stats[0].base_stat)
//                    bundle.putInt("AttackBar", numbers.stats[1].base_stat)
//                    bundle.putInt("DefenceBar", numbers.stats[2].base_stat)
//                    bundle.putInt("SpAttackBar", numbers.stats[3].base_stat)
//                    bundle.putInt("SpDefenceBar", numbers.stats[4].base_stat)
//                    bundle.putInt("SpeedBar", numbers.stats[5].base_stat)



                }



            }

            override fun onFailure(call: Call<DetailModel>, t: Throwable) {
                println("deu ruim cuzao")
            }
        })


    }


    //TENTEI USAR FODASE TA FUNCIONANDO
    override fun onResume() {
        super.onResume()

        backButton.setOnClickListener {
            finish()
        }
    }
    //TROCANDO A COR DO FUNDO
    private fun changingColors() {
        val type = intent.getStringExtra("type")
        val layoutContainer = findViewById<LinearLayout>(R.id.backgroundColor)


        val coresPadrao = intArrayOf(Color.BLACK, Color.BLACK, Color.BLACK)

        val coresGradient = when (type) {
            "Normal" -> intArrayOf(
                Color.parseColor("#ACA974"),
                Color.parseColor("#CCC9AA"),
                Color.parseColor("#CCC9AA")
            )
            "Flying" -> intArrayOf(
                Color.parseColor("#085764"),
                Color.parseColor("#5EB9B2"),
                Color.parseColor("#5EB9B2")
            )
            "Fire" -> intArrayOf(
                Color.parseColor("#F67F0B"),
                Color.parseColor("#F8B80E"),
                Color.parseColor("#F8B80E")
            )
            "Bug" -> intArrayOf(
                Color.parseColor("#91BA2E"),
                Color.parseColor("#D9FE9E"),
                Color.parseColor("#D9FE9E")
            )
            "Water" -> intArrayOf(
                Color.parseColor("#0A7ABC"),
                Color.parseColor("#36AFF6"),
                Color.parseColor("#36AFF6")
            )
            "Grass" -> intArrayOf(
                Color.parseColor("#12C1AC"),
                Color.parseColor("#52D0B1"),
                Color.parseColor("#52D0B1")
            )
            "Poison" -> intArrayOf(
                Color.parseColor("#A819D7"),
                Color.parseColor("#CA72EC"),
                Color.parseColor("#CA72EC")
            )
            "Electric" -> intArrayOf(
                Color.parseColor("#969101"),
                Color.parseColor("#FFFA24"),
                Color.parseColor("#FFFA24")
            )
            "Ground" -> intArrayOf(
                Color.parseColor("#969101"),
                Color.parseColor("#E1D158"),
                Color.parseColor("#E1D158")
            )
            "Fighting" -> intArrayOf(
                Color.parseColor("#800B11"),
                Color.parseColor("#E81319"),
                Color.parseColor("#E81319")
            )
            "Psychic" -> intArrayOf(
                Color.parseColor("#8A0532"),
                Color.parseColor("#EC0E63"),
                Color.parseColor("#EC0E63")
            )
            "Rock" -> intArrayOf(
                Color.parseColor("#474026"),
                Color.parseColor("#776A3E"),
                Color.parseColor("#776A3E")
            )
            "Ice" -> intArrayOf(
                Color.parseColor("#103D43"),
                Color.parseColor("#1995A1"),
                Color.parseColor("#1995A1")
            )
            "Ghost" -> intArrayOf(
                Color.parseColor("#472B53"),
                Color.parseColor("#8E55A4"),
                Color.parseColor("#8E55A4")
            )
            "Dragon" -> intArrayOf(
                Color.parseColor("#29036A"),
                Color.parseColor("#8A55FD"),
                Color.parseColor("#8A55FD")
            )
            "Dark" -> intArrayOf(
                Color.parseColor("#2D221C"),
                Color.parseColor("#5F4632"),
                Color.parseColor("#5F4632")
            )
            "Steel" -> intArrayOf(
                Color.parseColor("#7B8E8A"),
                Color.parseColor("#7B8E8A"),
                Color.parseColor("#7B8E8A")
            )
            "Fairy" -> intArrayOf(
                Color.parseColor("#F87EA7"),
                Color.parseColor("#FFA0C2"),
                Color.parseColor("#FFA0C2")
            )

            else -> {
                coresPadrao
            }
        }

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            coresGradient
        )

        layoutContainer.background = gradientDrawable
    }
}

