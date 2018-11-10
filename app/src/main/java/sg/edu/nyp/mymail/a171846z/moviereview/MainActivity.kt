package sg.edu.nyp.mymail.a171846z.moviereview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextUtils
import android.widget.EditText




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chkNotSuitableForAudience.setOnClickListener({
            if (chkNotSuitableForAudience.isChecked == true){
                chkLanguage.visibility = View.VISIBLE
                chkViolence.visibility = View.VISIBLE
            } else {
                chkLanguage.visibility = View.INVISIBLE
                chkViolence.visibility = View.INVISIBLE
            }
        })
    }

    fun btnAddMovie(v: View){
        editTextEmptyValidator(name)
        editTextEmptyValidator(description)
        editTextEmptyValidator(releaseDate)
    }

    fun editTextEmptyValidator(etText: EditText) {
        val strName = etText.text.toString()
        if (TextUtils.isEmpty(strName)) {
            etText.error = "Field empty"
        }
    }

}
