package database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MiBaseDeDatosHelper (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Define la estructura de la tabla "Horario"
        db.execSQL(
            "CREATE TABLE $TABLE_HORARIO (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_SEMANA INTEGER)"
        )

        // Define la estructura de la tabla "Empleado" con la clave foránea "semana"
        db.execSQL(
            "CREATE TABLE $TABLE_EMPLEADO (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NOMBRE TEXT, " +
                    "$COLUMN_SEMANA INTEGER, " +
                    "$COLUMN_MIERCOLES REAL, " +
                    "$COLUMN_JUEVES REAL, " +
                    "$COLUMN_VIERNES REAL, " +
                    "$COLUMN_SABADO REAL, " +
                    "$COLUMN_DOMINGO REAL, " +
                    "$COLUMN_LUNES REAL, " +
                    "$COLUMN_HORA_EXTRA REAL, " +
                    "$COLUMN_ROSTRO BLOB, " +
                    "FOREIGN KEY($COLUMN_SEMANA) REFERENCES $TABLE_HORARIO($COLUMN_ID))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Si cambias la estructura de la base de datos, actualiza la versión aquí
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HORARIO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EMPLEADO")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "mi_base_de_datos.db"
        const val DATABASE_VERSION = 1

        const val TABLE_HORARIO = "horario"
        const val TABLE_EMPLEADO = "empleado"

        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_SEMANA = "semana"
        const val COLUMN_MIERCOLES = "miercoles"
        const val COLUMN_JUEVES = "jueves"
        const val COLUMN_VIERNES = "viernes"
        const val COLUMN_SABADO = "sabado"
        const val COLUMN_DOMINGO = "domingo"
        const val COLUMN_LUNES = "lunes"
        const val COLUMN_HORA_EXTRA = "hora_extra"
        const val COLUMN_ROSTRO = "rostro"
    }
}




