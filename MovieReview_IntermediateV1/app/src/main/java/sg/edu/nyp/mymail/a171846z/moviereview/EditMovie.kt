package sg.edu.nyp.mymail.a171846z.moviereview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_edit_movie.*
import kotlinx.android.synthetic.main.activity_main.*

class EditMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        editMovie_rbEnglish.isChecked = true

        editMovie_chkNotSuitableForAudience.setOnClickListener({
            if (editMovie_chkNotSuitableForAudience.isChecked == true){
                editMovie_chkLanguage.visibility = View.VISIBLE
                editMovie_chkViolence.visibility = View.VISIBLE
            } else {
                editMovie_chkLanguage.visibility = View.INVISIBLE
                editMovie_chkViolence.visibility = View.INVISIBLE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.miSave){
            if ((editTextEmptyValidator(editMovie_etName)==false) && (editTextEmptyValidator(editMovie_etDescription)==false) && (editTextEmptyValidator(editMovie_etReleaseDate)==false)){
                // toast msg stuff
                // Get selected text from radio button
                val radioLanguageGroup = findViewById<View>(R.id.editMovie_radioLanguage) as RadioGroup
                val selectedId = radioLanguageGroup.checkedRadioButtonId // int selectedId
                val radioLanguageBtn = findViewById(selectedId) as RadioButton // find the radiobutton by returned id
                // //Get selected text from radio button
                val suitableForAllAges = (editMovie_chkNotSuitableForAudience.isChecked == false)
                var txt = ""
                var suitabilityTxt = ""
                var suitability = "Yes"
                // Get selected text from chkbox
                if (suitableForAllAges == false) {
                    suitability = "No"
                    txt = "\nReason: "
                    val itms = ArrayList<CheckBox>()
                    itms.add(editMovie_chkViolence)
                    itms.add(editMovie_chkLanguage)
                    for (item in itms) {
                        if (item.isChecked)
                            txt += "\n" + item.text.toString()
                    }

                    if (editMovie_chkViolence.isChecked == true){
                        suitabilityTxt = "(Violence)"
                    }
                    if (editMovie_chkLanguage.isChecked == true) {
                        suitabilityTxt = "(Language)"
                    }
                    if (editMovie_chkLanguage.isChecked == true && editMovie_chkViolence.isChecked == true) {
                        suitabilityTxt = "(Violence & Language)"
                    }
                }
                var toast = Toast.makeText(this, "Title = ${editMovie_etName.text.toString()}\n" +
                        "Overview = ${editMovie_etDescription.text.toString()}\n" +
                        "Release date = ${editMovie_etReleaseDate.text.toString()}\n" +
                        "Language = ${radioLanguageBtn.text}\n" +
                        "Suitable for all ages = ${suitableForAllAges}" +
                        "${txt}"
                    , Toast.LENGTH_LONG)
                toast.show()
                // --toast msg stuff
                var reason = "${suitability}${suitabilityTxt}"
                var myIntent = Intent(this, ViewMovieDetails::class.java)
                myIntent.putExtra("title",editMovie_etName.text.toString())
                myIntent.putExtra("overview",editMovie_etDescription.text.toString())
                myIntent.putExtra("releaseDate",editMovie_etReleaseDate.text.toString())
                myIntent.putExtra("language",radioLanguageBtn.text)
                myIntent.putExtra("suitability",reason)


                startActivity(myIntent)
            } else{
                editTextEmptyValidator(editMovie_etName)
                editTextEmptyValidator(editMovie_etDescription)
                editTextEmptyValidator(editMovie_etReleaseDate)
            }
        } else if(item?.itemId == R.id.miCancel){
            //val activity = MainActivity() as MainActivity
            //activity.onBackPressed()
            super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)
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
        val suitableForAllAges = (editMovie_chkNotSuitableForAudience.isChecked == false)
        var txt = ""
        // Get selected text from chkbox
        if (suitableForAllAges == false) {
            txt = "\nReason: "
            val itms = ArrayList<CheckBox>()
            itms.add(editMovie_chkViolence)
            itms.add(editMovie_chkLanguage)
            for (item in itms) {
                if (item.isChecked)
                    txt += "\n" + item.text.toString()
            }
        }
        var toast = Toast.makeText(this, "Title = ${editMovie_etName.text.toString()}\n" +
                "Overview = ${editMovie_etDescription.text.toString()}\n" +
                "Release date = ${editMovie_etReleaseDate.text.toString()}\n" +
                "Language = ${radioLanguageBtn.text}\n" +
                "Suitable for all ages = ${suitableForAllAges}" +
                "${txt}"
            , Toast.LENGTH_LONG)
        toast.show()
    }
}
