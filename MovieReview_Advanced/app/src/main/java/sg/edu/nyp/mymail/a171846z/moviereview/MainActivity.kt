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

        main_rbEnglish.isChecked = true

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
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.miAddMovie){
            if ((editTextEmptyValidator(main_etName)==false) && (editTextEmptyValidator(main_etDescription)==false) && (editTextEmptyValidator(main_etReleaseDate)==false)){
                // toast msg stuffs
                // Get selected text from radio button
                val radioLanguageGroup = findViewById<View>(R.id.radioLanguage) as RadioGroup
                val selectedId = radioLanguageGroup.checkedRadioButtonId // int selectedId
                val radioLanguageBtn = findViewById(selectedId) as RadioButton // find the radiobutton by returned id
                // //Get selected text from radio button
                val suitableForAllAges = (main_chkNotSuitableForAudience.isChecked == false)
                var txt = ""
                var suitabilityTxt = ""
                var suitability = "Yes"
                // Get selected text from chkbox
                if (suitableForAllAges == false) {
                    suitability = "No"
                    txt = "\nReason: "
                    val itms = ArrayList<CheckBox>()
                    itms.add(main_chkViolence)
                    itms.add(main_chkLanguage)
                    for (item in itms) {
                        if (item.isChecked)
                            txt += "\n" + item.text.toString()
                    }

                    if (main_chkViolence.isChecked == true){
                        suitabilityTxt = "(Violence)"
                    }
                    if (main_chkLanguage.isChecked == true) {
                        suitabilityTxt = "(Language)"
                    }
                    if (main_chkLanguage.isChecked == true && main_chkViolence.isChecked == true) {
                        suitabilityTxt = "(Violence & Language)"
                    }
                }
                var toast = Toast.makeText(this, "Title = ${main_etName.text.toString()}\n" +
                        "Overview = ${main_etDescription.text.toString()}\n" +
                        "Release date = ${main_etReleaseDate.text.toString()}\n" +
                        "Language = ${radioLanguageBtn.text}\n" +
                        "Suitable for all ages = ${suitableForAllAges}" +
                        "${txt}"
                    , Toast.LENGTH_LONG)
                toast.show()
                // -- toast msg stuffs
                var reason = "${suitability}${suitabilityTxt}"
                var myIntent = Intent(this, ViewMovieDetails::class.java)
                myIntent.putExtra("title",main_etName.text.toString())
                myIntent.putExtra("overview",main_etDescription.text.toString())
                myIntent.putExtra("releaseDate",main_etReleaseDate.text.toString())
                myIntent.putExtra("language",radioLanguageBtn.text)
                myIntent.putExtra("suitability",reason)
                var aClass = MovieEntity(main_etName.text.toString(),main_etDescription.text.toString(),
                    radioLanguageBtn.text.toString(),main_etReleaseDate.text.toString(),reason)
                LandingPage.listMovies.add(aClass)
                LandingPage.moviePosition = LandingPage.listMovies.indexOf(aClass)
                startActivity(myIntent)
            } else{
                editTextEmptyValidator(main_etName)
                editTextEmptyValidator(main_etDescription)
                editTextEmptyValidator(main_etReleaseDate)
            }
        } else if(item?.itemId == R.id.miClearEntries){
            main_etName.text.clear()
            main_etDescription.text.clear()
            main_rbEnglish.isChecked = true
            main_rbChinese.isChecked = false
            main_rbMalay.isChecked = false
            main_rbTamil.isChecked = false
            main_etReleaseDate.text.clear()
            main_chkNotSuitableForAudience.isChecked = false
            main_chkViolence.isChecked = false
            main_chkLanguage.isChecked = false
            main_chkLanguage.visibility = View.INVISIBLE
            main_chkViolence.visibility = View.INVISIBLE
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
