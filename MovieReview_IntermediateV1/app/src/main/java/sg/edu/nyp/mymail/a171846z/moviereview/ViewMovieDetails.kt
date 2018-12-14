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

        var venom = MovieEntity(intent.getStringExtra("title"), intent.getStringExtra("language"),
            intent.getStringExtra("overview"),intent.getStringExtra("releaseDate"),intent.getStringExtra("suitability"))
        viewMovie_tvTitle.text = venom.mvTitle.toString()
        viewMovie_tvLanguage.text = venom.mvLanguage.toString()
        viewMovie_tvOverview.text = venom.mvOverview.toString()
        viewMovie_tvReleaseDate.text = venom.mvReleaseDate.toString()
        viewMovie_tvSuitableForChildrenBelow13.text = venom.mvSuitableForChild.toString()

        registerForContextMenu(viewMovie_tvNoReviews)
    }

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
