package mx.edu.itm.link.dadm_a_u1appescolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.itm.link.dadm_a_u1appescolar.utils.AdminBD
import org.json.JSONObject

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        var stringBD = intent.getStringExtra("bd")
        if(stringBD == null) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }

        val stringAlumno = intent.getStringExtra("alumno")

        // De String a JSON
        val bd = JSONObject(stringBD)
        val jsonAlumno = JSONObject(stringAlumno)

        // Se obtiene el semestre
        val strSemestre = jsonAlumno.getString("semestre")

        val admin = AdminBD()
        var jsonAlumnoMaterias = admin.generaHorario(stringBD, strSemestre)
    }

}