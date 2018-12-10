package sg.edu.nyp.mymail.a171846z.moviereview

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_landing_page.*
import kotlinx.android.synthetic.main.activity_view_movie_details.*
import kotlinx.android.synthetic.main.activity_main.*

class ViewMovieDetails : AppCompatActivity() {

    val EDIT_MOVIE_RESULT_CODE = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie_details)

        var venom = MovieEntity();
        venom.mvTitle = intent.getStringExtra("title")
        venom.mvLanguage =  intent.getStringExtra("language")
        venom.mvOverview = intent.getStringExtra("overview")
        venom.mvReleaseDate = intent.getStringExtra("releaseDate")
        venom.mvSuitableForChild = intent.getStringExtra("suitability")
        //venom.mvReason = intent.getStringExtra("reason")
        tvTitle.text = venom.mvTitle.toString()
        tvLanguage.text = venom.mvLanguage.toString()
        tvOverview.text = venom.mvOverview.toString()
        tvReleaseDate.text = venom.mvReleaseDate.toString()
        tvSuitableForChildrenBelow13.text = venom.mvSuitableForChild.toString()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        registerForContextMenu(tvNoReviews)
    }

    class MovieEntity() {
        var mvTitle: String = ""
        var mvOverview: String = ""
        var mvLanguage: String = ""
        var mvReleaseDate: String = ""
        var mvSuitableForChild: String = ""
        //var mvReason: String = ""
        init {
            this.mvTitle
            this.mvOverview
            this.mvLanguage
            this.mvReleaseDate
            this.mvSuitableForChild
            //this.mvReason
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.tvNoReviews){
            menu?.add(1,1001,1,"Add Review")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == 1001){
            // next page
            var myIntent = Intent(this, RateMovie::class.java)

            myIntent.putExtra("name",tvTitle.text.toString());

            startActivityForResult(myIntent,1)
        }
        return super.onContextItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                var review = data?.getStringExtra(RateMovie.REVIEW)
                var numRating = data?.getStringExtra(RateMovie.NUMRATING)

                tvNoReviews.visibility = View.GONE
                rating_rating_bar.visibility = View.VISIBLE
                rating_rating_bar.rating = numRating!!.toFloat()
                // Toast.makeText(this,"${review}",Toast.LENGTH_LONG).toString()
                tvReview.visibility = View.VISIBLE
                tvReview.text = review
            }
        }
    }
}
