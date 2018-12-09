package sg.edu.nyp.mymail.a171846z.moviereview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.RadioButton
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {

    val View_Movie_Result_Code = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar!!.title = "Go Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.miAddMovie){
            if ((editTextEmptyValidator(tbName)==false) && (editTextEmptyValidator(tbDescription)==false) && (editTextEmptyValidator(tbReleaseDate)==false)){
                // toast msg stuffs
                // Get selected text from radio button
                val radioLanguageGroup = findViewById<View>(R.id.radioLanguage) as RadioGroup
                val selectedId = radioLanguageGroup.checkedRadioButtonId // int selectedId
                val radioLanguageBtn = findViewById(selectedId) as RadioButton // find the radiobutton by returned id
                // //Get selected text from radio button
                val suitableForAllAges = (chkNotSuitableForAudience.isChecked == false)
                var txt = ""
                var suitabilityTxt = ""
                var suitability = "Yes"
                // Get selected text from chkbox
                if (suitableForAllAges == false) {
                    suitability = "No"
                    txt = "\nReason: "
                    val itms = ArrayList<CheckBox>()
                    itms.add(chkViolence)
                    itms.add(chkLanguage)
                    for (item in itms) {
                        if (item.isChecked)
                            txt += "\n" + item.text.toString()
                    }

                    if (chkViolence.isChecked == true){
                        suitabilityTxt += "(Violence)"
                    }
                    if (chkLanguage.isChecked == true) {
                        suitabilityTxt += "(Language)"
                    }
                }
                var toast = Toast.makeText(this, "Title = ${tbName.text.toString()}\n" +
                        "Overview = ${tbDescription.text.toString()}\n" +
                        "Release date = ${tbReleaseDate.text.toString()}\n" +
                        "Language = ${radioLanguageBtn.text}\n" +
                        "Suitable for all ages = ${suitableForAllAges}" +
                        "${txt}"
                    , Toast.LENGTH_LONG)
                toast.show()
                // -- toast msg stuffs
                var reason = "${suitability}${suitabilityTxt}"
                var myIntent = Intent(this, ViewMovieDetails::class.java)
                myIntent.putExtra("title",tbName.text.toString())
                myIntent.putExtra("overview",tbDescription.text.toString())
                myIntent.putExtra("releaseDate",tbReleaseDate.text.toString())
                myIntent.putExtra("language",radioLanguageBtn.text)
                myIntent.putExtra("suitability",reason)


                startActivity(myIntent)
            } else{
                editTextEmptyValidator(tbName)
                editTextEmptyValidator(tbDescription)
                editTextEmptyValidator(tbReleaseDate)
            }
        } else if(item?.itemId == R.id.miClearEntries){
            tbName.text.clear()
            tbDescription.text.clear()
            rbEnglish.isChecked = true
            rbChinese.isChecked = false
            rbMalay.isChecked = false
            rbTamil.isChecked = false
            tbReleaseDate.text.clear()
            chkNotSuitableForAudience.isChecked = false
            chkViolence.isChecked = false
            chkLanguage.isChecked = false
            chkLanguage.visibility = View.INVISIBLE
            chkViolence.visibility = View.INVISIBLE
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
}
