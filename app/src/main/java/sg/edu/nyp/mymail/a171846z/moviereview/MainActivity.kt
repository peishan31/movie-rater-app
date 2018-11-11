package sg.edu.nyp.mymail.a171846z.moviereview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextUtils
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.RadioButton
import android.widget.CheckBox

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
        if ((editTextEmptyValidator(tbName)==false) && (editTextEmptyValidator(tbDescription)==false) && (editTextEmptyValidator(tbReleaseDate)==false)){
            // Get selected text from radio button
            val radioLanguageGroup = findViewById<View>(R.id.radioLanguage) as RadioGroup
            val selectedId = radioLanguageGroup.checkedRadioButtonId // int selectedId
            val radioLanguageBtn = findViewById(selectedId) as RadioButton // find the radiobutton by returned id
            // //Get selected text from radio button
            val suitableForAllAges = (chkNotSuitableForAudience.isChecked == false)

            // Get selected text from chkbox
            var txt = ""
            if (suitableForAllAges == false) {
                val itms = ArrayList<CheckBox>()
                itms.add(chkViolence)
                itms.add(chkLanguage)
                for (item in itms) {
                    if (item.isChecked)
                        txt += "\n" + item.text.toString()
                }
                Toast.makeText(this, "Title = ${tbName.text.toString()}\n" +
                        "Overview = ${tbDescription.text.toString()}\n" +
                        "Release date = ${tbReleaseDate.text.toString()}\n" +
                        "Language = ${radioLanguageBtn.text}\n" +
                        "Suitable for all ages = ${suitableForAllAges}\n" +
                        "Reason: ${txt}"
                    , Toast.LENGTH_LONG).show()
            }
            // //Get selected text from chkbox end
            else { // Remove "reason:" if suitable for all ages == false
                Toast.makeText(this, "Title = ${tbName.text.toString()}\n" +
                        "Overview = ${tbDescription.text.toString()}\n" +
                        "Release date = ${tbReleaseDate.text.toString()}\n" +
                        "Language = ${radioLanguageBtn.text}\n" +
                        "Suitable for all ages = ${suitableForAllAges}"
                    , Toast.LENGTH_LONG).show()
            }
        } else{
            editTextEmptyValidator(tbName)
            editTextEmptyValidator(tbDescription)
            editTextEmptyValidator(tbReleaseDate)
        }
    }

    fun editTextEmptyValidator(etText: EditText): Boolean {
        val strName = etText.text.toString()
        var isEmpty = false
        if (TextUtils.isEmpty(strName)) {
            etText.error = "Field empty"
            isEmpty = true
        }
        return isEmpty
    }

}
