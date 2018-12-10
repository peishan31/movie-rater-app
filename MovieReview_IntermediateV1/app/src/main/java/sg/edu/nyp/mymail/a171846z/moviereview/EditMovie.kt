package sg.edu.nyp.mymail.a171846z.moviereview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class EditMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        main_chkNotSuitableForAudience.setOnClickListener({
            if (main_chkNotSuitableForAudience.isChecked == true){
                main_chkLanguage.visibility = View.VISIBLE
                main_chkViolence.visibility = View.VISIBLE
            } else {
                main_chkLanguage.visibility = View.INVISIBLE
                main_chkViolence.visibility = View.INVISIBLE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun btnAddMovie(v: View){
        if ((editTextEmptyValidator(main_tbName)==false) && (editTextEmptyValidator(main_tbDescription)==false) && (editTextEmptyValidator(main_tbReleaseDate)==false)){
            toastMsg()
        } else{
            editTextEmptyValidator(main_tbName)
            editTextEmptyValidator(main_tbDescription)
            editTextEmptyValidator(main_tbReleaseDate)
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

    fun toastMsg(){
        // Get selected text from radio button
        val radioLanguageGroup = findViewById<View>(R.id.radioLanguage) as RadioGroup
        val selectedId = radioLanguageGroup.checkedRadioButtonId // int selectedId
        val radioLanguageBtn = findViewById(selectedId) as RadioButton // find the radiobutton by returned id
        // //Get selected text from radio button
        val suitableForAllAges = (main_chkNotSuitableForAudience.isChecked == false)
        var txt = ""
        // Get selected text from chkbox
        if (suitableForAllAges == false) {
            txt = "\nReason: "
            val itms = ArrayList<CheckBox>()
            itms.add(main_chkViolence)
            itms.add(main_chkLanguage)
            for (item in itms) {
                if (item.isChecked)
                    txt += "\n" + item.text.toString()
            }
        }
        var toast = Toast.makeText(this, "Title = ${main_tbName.text.toString()}\n" +
                "Overview = ${main_tbDescription.text.toString()}\n" +
                "Release date = ${main_tbReleaseDate.text.toString()}\n" +
                "Language = ${radioLanguageBtn.text}\n" +
                "Suitable for all ages = ${suitableForAllAges}" +
                "${txt}"
            , Toast.LENGTH_LONG)
        toast.show()
    }
}
