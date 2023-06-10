package com.example.pokedex


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_cards.view.*
import com.bumptech.glide.request.RequestOptions

class PokemonListAdapter(private val pokemonList: List<PokemonModel>, private val onItemClicked : (PokemonModel) -> Unit ) :
    Adapter<PokemonListAdapter.PokemonViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val pokeList =
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_cards, parent, false)
        return PokemonViewHolder(pokeList)

    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.bind(pokemon, onItemClicked)

    }




    inner class PokemonViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val pokemonCard = itemView.pokemon_card_full
        val secondTypeLayout = itemView.second_type_layout
        var name = itemView.pokemon_name
        var id = itemView.poke_number
        var pokeType = itemView.pokemon_type
        var pokeType2 = itemView.second_type

        fun bind(pokemon: PokemonModel, onItemClicked: (PokemonModel) -> Unit) {

            // PEGANDO OS IDS NA ITEMVIEW

            val types = pokemon.types.size
            val types0 = pokemon.types[0]
            name.text = pokemon.name.replaceFirstChar { it.uppercaseChar() }
            id.text = pokemon.id.toString(  ).padStart(3, '0')
            pokeType.text = types0.type.name.replaceFirstChar { it.uppercaseChar() }
            ChangingColors()
            secondTypeLayout.visibility = View.INVISIBLE
            // VERIFICANDO SE EXISTE MAIS DE UM TIPO NO POKEMON
            if (types == 2) {
                secondTypeLayout.visibility = View.VISIBLE
                val types1 = pokemon.types[1]
                pokeType2.text = types1.type.name.replaceFirstChar { it.uppercaseChar() }
            }
            // COLOCANDO AS IMAGENS COM O GLIDE

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${
                        id.text.toString().padStart(3, '0')
                    }.png"
                )
                .into(itemView.pokemon_pic)

            //ALGUMA COISA COM ITEM VIEW CLICK LISTENER PRO CLICK KKKK

            itemView.setOnClickListener {
                onItemClicked(pokemon)
            }
        }

        private fun ChangingColors() {
            // ALTERANDO A COR DO BACKGROUND
            val coresPadrao = intArrayOf(Color.BLACK, Color.BLACK, Color.BLACK)

            val coresGradient = when (pokeType.text) {
                "Normal" -> intArrayOf(
                    Color.parseColor("#ACA974"),
                    Color.parseColor("#CCC9AA"),
                    Color.parseColor("#EAEADE")
                )
                "Flying" -> intArrayOf(
                    Color.parseColor("#085764"),
                    Color.parseColor("#5EB9B2"),
                    Color.parseColor("#FFFFFF")
                )
                "Fire" -> intArrayOf(
                    Color.parseColor("#F67F0B"),
                    Color.parseColor("#F8B80E"),
                    Color.parseColor("#FFEAC3")
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
                    Color.parseColor("#ABE5B9")
                )
                "Poison" -> intArrayOf(
                    Color.parseColor("#A819D7"),
                    Color.parseColor("#CA72EC"),
                    Color.parseColor("#CA72EC")
                )
                "Electric" -> intArrayOf(
                    Color.parseColor("#969101"),
                    Color.parseColor("#FFFA24"),
                    Color.parseColor("#F7FF85")
                )
                "Ground" -> intArrayOf(
                    Color.parseColor("#969101"),
                    Color.parseColor("#E1D158"),
                    Color.parseColor("#EDE293")
                )
                "Fighting" -> intArrayOf(
                    Color.parseColor("#800B11"),
                    Color.parseColor("#E81319"),
                    Color.parseColor("#D36063")
                )
                "Psychic" -> intArrayOf(
                    Color.parseColor("#8A0532"),
                    Color.parseColor("#EC0E63"),
                    Color.parseColor("#F55792")
                )
                "Rock" -> intArrayOf(
                    Color.parseColor("#474026"),
                    Color.parseColor("#776A3E"),
                    Color.parseColor("#94834F")
                )
                "Ice" -> intArrayOf(
                    Color.parseColor("#103D43"),
                    Color.parseColor("#1995A1"),
                    Color.parseColor("#66D1E5")
                )
                "Ghost" -> intArrayOf(
                    Color.parseColor("#472B53"),
                    Color.parseColor("#8E55A4"),
                    Color.parseColor("#BD98CB")
                )
                "Dragon" -> intArrayOf(
                    Color.parseColor("#29036A"),
                    Color.parseColor("#8A55FD"),
                    Color.parseColor("#D6B1FE")
                )
                "Dark" -> intArrayOf(
                    Color.parseColor("#2D221C"),
                    Color.parseColor("#5F4632"),
                    Color.parseColor("#916852")
                )
                "Steel" -> intArrayOf(
                    Color.parseColor("#7B8E8A"),
                    Color.parseColor("#7B8E8A"),
                    Color.parseColor("#BBC5C4")
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
                GradientDrawable.Orientation.LEFT_RIGHT,
                coresGradient
            )

            pokemonCard.background = gradientDrawable
        }
    }


    override fun getItemCount(): Int {
        return pokemonList.size
    }


}