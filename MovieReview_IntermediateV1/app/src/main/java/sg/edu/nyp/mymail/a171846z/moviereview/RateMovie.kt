package sg.edu.nyp.mymail.a171846z.moviereview

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DialogTitle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_landing_page.*
import kotlinx.android.synthetic.main.activity_rate_movie.*
import org.intellij.lang.annotations.Language
import java.util.*

class RateMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_movie)

        var title = intent.getStringExtra("name")
        tvTitle.text = title.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.ratemenu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        val REVIEW = "review"
        val NUMRATING = "numRating"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.miSubmit){
            var output = Intent()

            var review = etRatingView.text.toString()
            var numRating = rating_rating_bar.rating.toString()

            Toast.makeText(this,"review: ${review} numRating: ${numRating}",Toast.LENGTH_LONG).show()
            output.putExtra(REVIEW, review)
            output.putExtra(NUMRATING,numRating)

            setResult(Activity.RESULT_OK, output)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
