package com.example.pokedex



import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.android.car.ui.toolbar.TabLayout
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    private var i = 1


    private var pokemonList = mutableListOf<PokemonModel>()
    private lateinit var pokemonAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initRecyclerView()
        sendGet()
        addingMorePokemon()
    }

     fun initRecyclerView() {

             pokemonAdapter = PokemonListAdapter(pokemonList) { pokemonModel ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(
                "PokemonName",
                pokemonModel.name.replaceFirstChar { it.uppercaseChar() })
                 intent.putExtra("PokeNumber", pokemonModel.id.toString())
            intent.putExtra("type", pokemonModel.types[0].type.name.replaceFirstChar { it.uppercaseChar() })
            startActivity(intent)
        }
         recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_view.adapter = pokemonAdapter
    }

    private fun addingMorePokemon() {
        recycler_view.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = scrollView.scrollY
            val height = scrollView.height
            val totalHeight = scrollView.getChildAt(0).height

            if (scrollY + height >= totalHeight) {
                i += 20
                sendGet()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
    private fun sendGet() {
        for (j in i..(i + 19)) {
            val queue = Volley.newRequestQueue(this)
            val url = "https://pokeapi.co/api/v2/pokemon/$j"

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val pokemon = Json { ignoreUnknownKeys = true }.decodeFromString<PokemonModel>(response)
                    pokemonList.add(pokemon)
                    pokemonAdapter.notifyItemInserted(pokemonList.size - 1)
                },
                { error ->
                    println(error)
                })
            queue.add(stringRequest)
        }
    }


}
