package mx.edu.itm.link.dadm_a_u1appescolar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.dadm_a_u1appescolar.R
import org.json.JSONArray
import org.json.JSONObject

class RecyclerReticulaAdapter(val c: Context, val res:Int, val reticula:JSONArray, val semestre:Int) : RecyclerView.Adapter<RecyclerReticulaAdapter.ReticulaVH>(){

    var semestreActual = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReticulaVH {
        return ReticulaVH( LayoutInflater.from(c).inflate(res, null) )
    }

    override fun onBindViewHolder(holder: ReticulaVH, position: Int) {
        val jsonCalificacion = reticula.getJSONObject(position)
        holder.bind(jsonCalificacion)
    }

    override fun getItemCount(): Int {
        return reticula.length()
    }

    inner class ReticulaVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(jsonCalificacion: JSONObject) {
            if( ! semestreActual.equals(jsonCalificacion.getString("semestre"))) {
                semestreActual = jsonCalificacion.getString("semestre")

                val tvSemestre = itemView.findViewById<TextView>(R.id.tvRowReticulaSemestre)
                tvSemestre.text = "Semestre $semestreActual"

                val calificacionesXSemestre = JSONArray()
                for(i in 0..reticula.length()-1) {
                    val json = reticula.getJSONObject(i)
                    if(json.getString("semestre").equals(semestreActual)) {
                        calificacionesXSemestre.put(json)
                    }
                }
                if(calificacionesXSemestre.length() > 0) {
                    val recycler = itemView.findViewById<RecyclerView>(R.id.recyclerRowReticula)
                    recycler.adapter = RecyclerReticulaCalificacionAdapter(
                        itemView.context,
                        R.layout.row_materias_reticula,
                        calificacionesXSemestre
                    )
                    recycler.layoutManager = GridLayoutManager(itemView.context, 3)
                }
            }
        }
    }

}