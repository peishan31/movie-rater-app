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
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //var venom = MovieEntity(intent.getStringExtra("title"), intent.getStringExtra("language"),
        //    intent.getStringExtra("overview"),intent.getStringExtra("releaseDate"),intent.getStringExtra("suitability"))
        //======================================================================================
        //======================================================================================
        /*var venom = MovieEntity();
        venom.mvTitle = intent.getStringExtra("title")
        venom.mvLanguage =  intent.getStringExtra("language")
        venom.mvOverview = intent.getStringExtra("overview")
        venom.mvReleaseDate = intent.getStringExtra("releaseDate")
        venom.mvSuitableForChild = intent.getStringExtra("suitability")
        //venom.mvReason = intent.getStringExtra("reason")*/
        viewMovie_tvTitle.text = LandingPage.listMovies[LandingPage.currentMovie].mvTitle
        viewMovie_tvLanguage.text = LandingPage.listMovies[LandingPage.currentMovie].mvLanguage
        viewMovie_tvOverview.text = LandingPage.listMovies[LandingPage.currentMovie].mvOverview
        viewMovie_tvReleaseDate.text = LandingPage.listMovies[LandingPage.currentMovie].mvReleaseDate
        viewMovie_tvSuitableForChildrenBelow13.text = LandingPage.listMovies[LandingPage.currentMovie].mvSuitableForChild

        if (LandingPage.listMovies[LandingPage.currentMovie].review == ""&&
            LandingPage.listMovies[LandingPage.currentMovie].star == 0.toFloat()){
            viewMovie_tvNoReviews.text="No Reviews yet.\nLong press here to add your review"
            viewMovie_rating_bar.visibility = View.GONE
        } else{
            viewMovie_tvReview.text = LandingPage.listMovies[LandingPage.currentMovie].review
            viewMovie_rating_bar.rating = LandingPage.listMovies[LandingPage.currentMovie].star
            viewMovie_tvReview.visibility = View.VISIBLE
            viewMovie_rating_bar.visibility = View.VISIBLE
            viewMovie_tvNoReviews.visibility = View.GONE
        }
        /*viewMovie_tvTitle.text = venom.mvTitle.toString()
        viewMovie_tvLanguage.text = venom.mvLanguage.toString()
        viewMovie_tvOverview.text = venom.mvOverview.toString()
        viewMovie_tvReleaseDate.text = venom.mvReleaseDate.toString()
        viewMovie_tvSuitableForChildrenBelow13.text = venom.mvSuitableForChild.toString()*/

        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        registerForContextMenu(viewMovie_tvNoReviews)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }*/
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.viewMovie_tvNoReviews){
            menu?.add(1,1001,1,"Add Review")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == 1001){
            // next page
            var myIntent = Intent(this, RateMovie::class.java)

            myIntent.putExtra("name",viewMovie_tvTitle.text.toString());

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

                viewMovie_tvNoReviews.visibility = View.GONE
                viewMovie_rating_bar.visibility = View.VISIBLE
                viewMovie_rating_bar.rating = numRating!!.toFloat()
                // Toast.makeText(this,"${review}",Toast.LENGTH_LONG).toString()
                viewMovie_tvReview.visibility = View.VISIBLE
                viewMovie_tvReview.text = review
            }
        }
    }
}
